package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.Direction;

public class MoveEntityProcedure {
	public static void execute(BlockState blockstate, Entity entity) {
		if (entity == null)
			return;
		if (Math.abs(entity.getDeltaMovement().x()) < Math.abs((getDirectionFromBlockState(blockstate)).getStepX() / (-10d))) {
			entity.push(((getDirectionFromBlockState(blockstate)).getStepX() / (-10d)), 0, 0);
		}
		if (Math.abs(entity.getDeltaMovement().z()) < Math.abs((getDirectionFromBlockState(blockstate)).getStepZ() / (-10d))) {
			entity.push(0, 0, ((getDirectionFromBlockState(blockstate)).getStepZ() / (-10d)));
		}
	}

	private static Direction getDirectionFromBlockState(BlockState blockState) {
		if (getPropertyByName(blockState, "facing") instanceof EnumProperty ep && ep.getValueClass() == Direction.class)
			return (Direction) blockState.getValue(ep);
		if (getPropertyByName(blockState, "axis") instanceof EnumProperty ep && ep.getValueClass() == Direction.Axis.class)
			return Direction.fromAxisAndDirection((Direction.Axis) blockState.getValue(ep), Direction.AxisDirection.POSITIVE);
		return Direction.NORTH;
	}

	private static Property<?> getPropertyByName(BlockState state, String name) {
		for (Property<?> property : state.getProperties()) {
			if (property.getName().equals(name)) {
				return property;
			}
		}
		return null;
	}
}