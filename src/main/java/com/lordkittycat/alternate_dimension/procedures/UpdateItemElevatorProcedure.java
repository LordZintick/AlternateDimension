package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import com.lordkittycat.alternate_dimension.init.AlternateDimensionModBlocks;

public class UpdateItemElevatorProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		double i = 0;
		if ((getPropertyByName(blockstate, "type") instanceof EnumProperty _getep1 ? blockstate.getValue(_getep1).toString() : "").equals("bottom")
				&& (world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == AlternateDimensionModBlocks.ITEM_ELEVATOR.get()) {
			ImportItemsFromConveyorProcedure.execute(world, x, y, z, getDirectionFromBlockState(blockstate), 0);
			i = 1;
			while ((world.getBlockState(BlockPos.containing(x, y + i, z))).getBlock() == AlternateDimensionModBlocks.ITEM_ELEVATOR.get()) {
				i = i + 1;
			}
			i = i - 1;
			if ((getPropertyByName((world.getBlockState(BlockPos.containing(x, y + i, z))), "type") instanceof EnumProperty _getep9 ? (world.getBlockState(BlockPos.containing(x, y + i, z))).getValue(_getep9).toString() : "").equals("top")) {
				ExportItemsToConveyorWirelessProcedure.execute(world, x, y, z, getDirectionFromBlockState((world.getBlockState(BlockPos.containing(x, y + i, z)))), x, y + i, z, 0);
			}
		}
	}

	private static Property<?> getPropertyByName(BlockState state, String name) {
		for (Property<?> property : state.getProperties()) {
			if (property.getName().equals(name)) {
				return property;
			}
		}
		return null;
	}

	private static Direction getDirectionFromBlockState(BlockState blockState) {
		if (getPropertyByName(blockState, "facing") instanceof EnumProperty ep && ep.getValueClass() == Direction.class)
			return (Direction) blockState.getValue(ep);
		if (getPropertyByName(blockState, "axis") instanceof EnumProperty ep && ep.getValueClass() == Direction.Axis.class)
			return Direction.fromAxisAndDirection((Direction.Axis) blockState.getValue(ep), Direction.AxisDirection.POSITIVE);
		return Direction.NORTH;
	}
}