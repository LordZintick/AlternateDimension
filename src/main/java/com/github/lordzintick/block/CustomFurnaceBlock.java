package com.github.lordzintick.block;
import com.github.lordzintick.block.entity.CustomFurnaceBlockEntity;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.FurnaceMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public abstract class CustomFurnaceBlock extends BaseEntityBlock {
    // Relying on vanilla LIT state to dictate our running status
    public static final EnumProperty<Direction> FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty LIT = BlockStateProperties.LIT;

    public CustomFurnaceBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(LIT, false));
    }

    public abstract int getSmeltSpeed();

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return super.getStateForPlacement(context).setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(LIT, false);
    }

    public BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING, LIT);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL; // Prevents the custom block entity from rendering invisible
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (!level.isClientSide()) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof CustomFurnaceBlockEntity customFurnace) {
                if (player instanceof ServerPlayer serverPlayer) {
                    // Force wrap inside a MenuProvider mapping vanilla titles and menus
                    MenuProvider menuProvider = new MenuProvider() {
                        @Override
                        public Component getDisplayName() {
                            return Component.translatable(CustomFurnaceBlock.this.getDescriptionId());
                        }

                        @Override
                        public AbstractContainerMenu createMenu(int id, Inventory inv, Player p) {
                            return new FurnaceMenu(id, inv, customFurnace, customFurnace.dataAccess);
                        }
                    };
                    serverPlayer.openMenu(menuProvider);
                }
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    @Nullable
    public <T extends BlockEntity> net.minecraft.world.level.block.entity.BlockEntityTicker<T> getTicker(Level level, BlockState state, net.minecraft.world.level.block.entity.BlockEntityType<T> type) {
        // Only run execution loops on the logical server side to prevent container sync issues
        return level.isClientSide() ? null : (lvl, pos, st, be) -> {
            if (be instanceof CustomFurnaceBlockEntity furnace && lvl instanceof ServerLevel serverLevel) {
                CustomFurnaceBlockEntity.serverTick(serverLevel, pos, st, furnace);
            }
        };
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, ItemStack toolStack, boolean willHarvest, FluidState fluid) {
        BlockEntity blockentity = level.getBlockEntity(pos);
        if (blockentity instanceof CustomFurnaceBlockEntity customFurnace) {
            net.minecraft.world.Containers.dropContents(level, pos, customFurnace);
            level.updateNeighbourForOutputSignal(pos, this);
        }
        return super.onDestroyedByPlayer(state, level, pos, player, toolStack, willHarvest, fluid);
    }
}
