package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.util.RandomSource;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import javax.annotation.Nullable;

import java.util.function.Function;

import com.lordkittycat.alternate_dimension.procedures.UpdateTorchProcedure;
import com.lordkittycat.alternate_dimension.procedures.UpdateTorchPlacementProcedure;

public class PhosphorusTorchBlock extends Block implements SimpleWaterloggedBlock {
	public static final EnumProperty<Direction> FACING = HorizontalDirectionalBlock.FACING;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	public static final BooleanProperty WALL = BooleanProperty.create("wall");
	private final Function<BlockState, VoxelShape> shapes = this.makeShapes();

	public PhosphorusTorchBlock(BlockBehaviour.Properties properties) {
		super(properties.sound(SoundType.WOOD).instabreak().lightLevel(blockstate -> 15).noCollision().pushReaction(PushReaction.DESTROY).isRedstoneConductor((bs, br, bp) -> false));
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(WALL, false).setValue(WATERLOGGED, false));
	}

	private Function<BlockState, VoxelShape> makeShapes() {
		return this.getShapeForEachState(state -> {
			if (state.getValue(WALL) == true) {
				return switch (state.getValue(FACING)) {
					case NORTH -> Shapes.or(box(7, 4, 13, 9, 12, 15), box(7, 12, 13, 9, 14, 15), box(8.975, 10.975, 11.975, 9.025, 15.025, 16.025), box(6.975, 10.975, 11.975, 7.025, 15.025, 16.025), box(5.975, 10.975, 12.975, 10.025, 15.025, 13.025),
							box(5.975, 10.975, 14.975, 10.025, 15.025, 15.025), box(5.975, 11.975, 11.975, 10.025, 12.025, 16.025), box(5.975, 13.975, 11.975, 10.025, 14.025, 16.025));
					case EAST -> Shapes.or(box(1, 4, 7, 3, 12, 9), box(1, 12, 7, 3, 14, 9), box(-0.025, 10.975, 8.975, 4.025, 15.025, 9.025), box(-0.025, 10.975, 6.975, 4.025, 15.025, 7.025), box(2.975, 10.975, 5.975, 3.025, 15.025, 10.025),
							box(0.975, 10.975, 5.975, 1.025, 15.025, 10.025), box(-0.025, 11.975, 5.975, 4.025, 12.025, 10.025), box(-0.025, 13.975, 5.975, 4.025, 14.025, 10.025));
					case WEST -> Shapes.or(box(13, 4, 7, 15, 12, 9), box(13, 12, 7, 15, 14, 9), box(11.975, 10.975, 6.975, 16.025, 15.025, 7.025), box(11.975, 10.975, 8.975, 16.025, 15.025, 9.025), box(12.975, 10.975, 5.975, 13.025, 15.025, 10.025),
							box(14.975, 10.975, 5.975, 15.025, 15.025, 10.025), box(11.975, 11.975, 5.975, 16.025, 12.025, 10.025), box(11.975, 13.975, 5.975, 16.025, 14.025, 10.025));
					default -> Shapes.or(box(7, 4, 1, 9, 12, 3), box(7, 12, 1, 9, 14, 3), box(6.975, 10.975, -0.025, 7.025, 15.025, 4.025), box(8.975, 10.975, -0.025, 9.025, 15.025, 4.025), box(5.975, 10.975, 2.975, 10.025, 15.025, 3.025),
							box(5.975, 10.975, 0.975, 10.025, 15.025, 1.025), box(5.975, 11.975, -0.025, 10.025, 12.025, 4.025), box(5.975, 13.975, -0.025, 10.025, 14.025, 4.025));
				};
			}
			return switch (state.getValue(FACING)) {
				case NORTH -> box(7, 0, 7, 9, 10, 9);
				case EAST -> box(7, 0, 7, 9, 10, 9);
				case WEST -> box(7, 0, 7, 9, 10, 9);
				default -> box(7, 0, 7, 9, 10, 9);
			};
		}, WATERLOGGED);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return shapes.apply(state);
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state) {
		return state.getFluidState().isEmpty();
	}

	@Override
	public int getLightDampening(BlockState state) {
		return propagatesSkylightDown(state) ? 0 : 1;
	}

	@Override
	public VoxelShape getVisualShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return Shapes.empty();
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FACING, WALL, WATERLOGGED);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		BlockState state = super.getStateForPlacement(context);
		if (state == null)
			return null;
		boolean flag = context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER;
		if (context.getClickedFace().getAxis() == Direction.Axis.Y)
			return state.setValue(FACING, Direction.NORTH).setValue(WALL, false).setValue(WATERLOGGED, flag);
		return state.setValue(FACING, context.getClickedFace()).setValue(WALL, false).setValue(WATERLOGGED, flag);
	}

	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}

	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	@Override
	public BlockState updateShape(BlockState state, LevelReader world, ScheduledTickAccess scheduledTickAccess, BlockPos currentPos, Direction facing, BlockPos facingPos, BlockState facingState, RandomSource random) {
		if (state.getValue(WATERLOGGED)) {
			scheduledTickAccess.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
		}
		return super.updateShape(state, world, scheduledTickAccess, currentPos, facing, facingPos, facingState, random);
	}

	@Override
	public void neighborChanged(BlockState blockstate, Level world, BlockPos pos, Block neighborBlock, @Nullable Orientation orientation, boolean moving) {
		super.neighborChanged(blockstate, world, pos, neighborBlock, orientation, moving);
		UpdateTorchProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ(), blockstate);
	}

	@Override
	public void setPlacedBy(Level world, BlockPos pos, BlockState blockstate, LivingEntity entity, ItemStack itemstack) {
		super.setPlacedBy(world, pos, blockstate, entity, itemstack);
		UpdateTorchPlacementProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ(), entity);
	}
}