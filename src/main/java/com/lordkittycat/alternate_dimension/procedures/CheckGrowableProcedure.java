package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.BlockState;

public class CheckGrowableProcedure {
	public static boolean execute(BlockState blockstate) {
		return (getPropertyByName(blockstate, "blockstate") instanceof IntegerProperty _getip1 ? blockstate.getValue(_getip1) : -1) < 4;
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