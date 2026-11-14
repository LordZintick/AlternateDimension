package com.github.lordzintick.block;

import com.lordkittycat.alternate_dimension.AlternateDimensionMod;
import com.mojang.logging.LogUtils;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.BlockUtil;
import net.minecraft.util.RandomSource;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.gamerules.GameRules;
import net.minecraft.world.level.portal.PortalShape;
import net.minecraft.world.level.portal.TeleportTransition;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

public abstract class CustomPortalBlock extends Block implements Portal {
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final EnumProperty<Direction.Axis> AXIS;
    private static final Map<Direction.Axis, VoxelShape> SHAPES;
    protected static final ResourceKey<Level> ELEROTH_KEY = getDimKey("eleroth");

    public CustomPortalBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(AXIS, Direction.Axis.X));
    }

    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPES.get(state.getValue(AXIS));
    }

    @Override
    public VoxelShape getVisualShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return Shapes.empty();
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state) {
        return true;
    }

    @Override
    public int getLightDampening(BlockState state) {
        return 0;
    }

    @Override
    public ItemStack getCloneItemStack(LevelReader world, BlockPos pos, BlockState state, boolean includeData, Player entity) {
        return ItemStack.EMPTY;
    }

    protected abstract ResourceKey<Level> getDestination();
    protected static ResourceKey<Level> getDimKey(String id) {
        return ResourceKey.create(Registries.DIMENSION, Identifier.fromNamespaceAndPath(AlternateDimensionMod.MODID, id));
    }

    public @Nullable TeleportTransition getPortalDestination(ServerLevel currentLevel, Entity entity, BlockPos portalEntryPos) {
        ResourceKey<Level> dest = getDestination();
        ResourceKey<Level> newDimension = currentLevel.dimension() == dest ? ELEROTH_KEY : dest;
        ServerLevel newLevel = currentLevel.getServer().getLevel(newDimension);
        if (newLevel == null) {
            return null;
        } else {
            boolean toDest = newLevel.dimension() == dest;
            WorldBorder newWorldBorder = newLevel.getWorldBorder();
            double teleportationScale = DimensionType.getTeleportationScale(currentLevel.dimensionType(), newLevel.dimensionType());
            BlockPos approximateExitPos = newWorldBorder.clampToBounds(entity.getX() * teleportationScale, entity.getY(), entity.getZ() * teleportationScale);
            return this.getExitPortal(newLevel, entity, portalEntryPos, approximateExitPos, toDest, newWorldBorder);
        }
    }

    protected @Nullable TeleportTransition getExitPortal(ServerLevel newLevel, Entity entity, BlockPos portalEntryPos, BlockPos approximateExitPos, boolean toDest, WorldBorder worldBorder) {
        Optional<BlockPos> exitPortalPos = newLevel.getPortalForcer().findClosestPortalPosition(approximateExitPos, toDest, worldBorder);
        BlockUtil.FoundRectangle exitPortal;
        TeleportTransition.PostTeleportTransition post;
        if (exitPortalPos.isPresent()) {
            BlockPos pos = exitPortalPos.get();
            BlockState portalState = newLevel.getBlockState(pos);
            exitPortal = BlockUtil.getLargestRectangleAround(pos, portalState.getValue(BlockStateProperties.HORIZONTAL_AXIS), 21, Direction.Axis.Y, 21, (blockPos) -> newLevel.getBlockState(blockPos) == portalState);
            post = TeleportTransition.PLAY_PORTAL_SOUND.then((e) -> e.placePortalTicket(pos));
        } else {
            Direction.Axis sourcePortalAxis = entity.level().getBlockState(portalEntryPos).getOptionalValue(AXIS).orElse(Direction.Axis.X);
            Optional<BlockUtil.FoundRectangle> createdExit = newLevel.getPortalForcer().createPortal(approximateExitPos, sourcePortalAxis);
            if (createdExit.isEmpty()) {
                LOGGER.error("Unable to create a portal, likely target out of worldborder");
                return null;
            }

            exitPortal = createdExit.get();
            post = TeleportTransition.PLAY_PORTAL_SOUND.then(TeleportTransition.PLACE_PORTAL_TICKET);
        }

        return getDimensionTransitionFromExit(entity, portalEntryPos, exitPortal, newLevel, post);
    }

    private static TeleportTransition getDimensionTransitionFromExit(Entity entity, BlockPos portalEntryPos, BlockUtil.FoundRectangle exitPortal, ServerLevel newLevel, TeleportTransition.PostTeleportTransition postTeleportTransition) {
        BlockState blockState = entity.level().getBlockState(portalEntryPos);
        Direction.Axis axis;
        Vec3 offset;
        if (blockState.hasProperty(BlockStateProperties.HORIZONTAL_AXIS)) {
            axis = blockState.getValue(BlockStateProperties.HORIZONTAL_AXIS);
            BlockUtil.FoundRectangle portalArea = BlockUtil.getLargestRectangleAround(portalEntryPos, axis, 21, Direction.Axis.Y, 21, (pos) -> entity.level().getBlockState(pos) == blockState);
            offset = entity.getRelativePortalPosition(axis, portalArea);
        } else {
            axis = Direction.Axis.X;
            offset = new Vec3(0.5F, 0.0F, 0.0F);
        }

        return createDimensionTransition(newLevel, exitPortal, axis, offset, entity, postTeleportTransition);
    }

    private static TeleportTransition createDimensionTransition(ServerLevel newLevel, BlockUtil.FoundRectangle foundRectangle, Direction.Axis portalAxis, Vec3 offset, Entity entity, TeleportTransition.PostTeleportTransition postTeleportTransition) {
        BlockPos bottomLeft = foundRectangle.minCorner;
        BlockState blockState = newLevel.getBlockState(bottomLeft);
        Direction.Axis axis = blockState.getOptionalValue(BlockStateProperties.HORIZONTAL_AXIS).orElse(Direction.Axis.X);
        double width = foundRectangle.axis1Size;
        double height = foundRectangle.axis2Size;
        EntityDimensions dimensions = entity.getDimensions(entity.getPose());
        int outputRotation = portalAxis == axis ? 0 : 90;
        double offsetRight = (double)dimensions.width() / (double)2.0F + (width - (double)dimensions.width()) * offset.x();
        double offsetUp = (height - (double)dimensions.height()) * offset.y();
        double offsetForward = (double)0.5F + offset.z();
        boolean xAligned = axis == Direction.Axis.X;
        Vec3 targetPos = new Vec3((double)bottomLeft.getX() + (xAligned ? offsetRight : offsetForward), (double)bottomLeft.getY() + offsetUp, (double)bottomLeft.getZ() + (xAligned ? offsetForward : offsetRight));
        Vec3 collisionFreePos = PortalShape.findCollisionFreePosition(targetPos, newLevel, entity, dimensions);
        return new TeleportTransition(newLevel, collisionFreePos, Vec3.ZERO, (float)outputRotation, 0.0F, Relative.union(Relative.DELTA, Relative.ROTATION), postTeleportTransition);
    }

    protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess ticks, BlockPos pos, Direction directionToNeighbour, BlockPos neighbourPos, BlockState neighbourState, RandomSource random) {
        Direction.Axis updateAxis = directionToNeighbour.getAxis();
        Direction.Axis axis = state.getValue(AXIS);
        boolean wrongAxis = axis != updateAxis && updateAxis.isHorizontal();
        return !wrongAxis && !neighbourState.is(this) && !PortalShape.findAnyShape(level, pos, axis).isComplete() ? Blocks.AIR.defaultBlockState() : super.updateShape(state, level, ticks, pos, directionToNeighbour, neighbourPos, neighbourState, random);
    }

    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity, InsideBlockEffectApplier effectApplier, boolean isPrecise) {
        if (entity.canUsePortal(false)) {
            entity.setAsInsidePortal(this, pos);
        }

    }

    public int getPortalTransitionTime(ServerLevel level, Entity entity) {
        int time;
        if (entity instanceof Player player) {
            time = Math.max(0, level.getGameRules().get(player.getAbilities().invulnerable ? GameRules.PLAYERS_NETHER_PORTAL_CREATIVE_DELAY : GameRules.PLAYERS_NETHER_PORTAL_DEFAULT_DELAY));
        } else {
            time = 0;
        }

        return time;
    }

    public Portal.Transition getLocalTransition() {
        return Transition.CONFUSION;
    }

    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (random.nextInt(100) == 0) {
            level.playLocalSound((double)pos.getX() + (double)0.5F, (double)pos.getY() + (double)0.5F, (double)pos.getZ() + (double)0.5F, SoundEvents.PORTAL_AMBIENT, SoundSource.BLOCKS, 0.5F, random.nextFloat() * 0.4F + 0.8F, false);
        }

        for(int i = 0; i < 4; ++i) {
            double x = (double)pos.getX() + random.nextDouble();
            double y = (double)pos.getY() + random.nextDouble();
            double z = (double)pos.getZ() + random.nextDouble();
            double xa = ((double)random.nextFloat() - (double)0.5F) * (double)0.5F;
            double ya = ((double)random.nextFloat() - (double)0.5F) * (double)0.5F;
            double za = ((double)random.nextFloat() - (double)0.5F) * (double)0.5F;
            int flip = random.nextInt(2) * 2 - 1;
            if (!level.getBlockState(pos.west()).is(this) && !level.getBlockState(pos.east()).is(this)) {
                x = (double)pos.getX() + (double)0.5F + (double)0.25F * (double)flip;
                xa = (double)(random.nextFloat() * 2.0F * (float)flip);
            } else {
                z = (double)pos.getZ() + (double)0.5F + (double)0.25F * (double)flip;
                za = (double)(random.nextFloat() * 2.0F * (float)flip);
            }

            level.addParticle(ParticleTypes.PORTAL, x, y, z, xa, ya, za);
        }

    }

    protected ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state, boolean includeData) {
        return ItemStack.EMPTY;
    }

    protected BlockState rotate(BlockState state, Rotation rotation) {
        switch (rotation) {
            case COUNTERCLOCKWISE_90:
            case CLOCKWISE_90:
                switch ((Direction.Axis)state.getValue(AXIS)) {
                    case X -> {
                        return (BlockState)state.setValue(AXIS, Direction.Axis.Z);
                    }
                    case Z -> {
                        return (BlockState)state.setValue(AXIS, Direction.Axis.X);
                    }
                    default -> {
                        return state;
                    }
                }
            default:
                return state;
        }
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AXIS);
    }

    static {
        AXIS = BlockStateProperties.HORIZONTAL_AXIS;
        SHAPES = Shapes.rotateHorizontalAxis(Block.column(4.0F, 16.0F, 0.0F, 16.0F));
    }
}
