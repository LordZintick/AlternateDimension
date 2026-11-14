package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.level.LevelAccessor;

import com.lordkittycat.alternate_dimension.network.AlternateDimensionModVariables;

public class CheckForBlueMoonProcedure {
	public static boolean execute(LevelAccessor world) {
		return AlternateDimensionModVariables.WorldVariables.get(world).worldEventTime > 0 && (AlternateDimensionModVariables.WorldVariables.get(world).worldEventType).equals("blue_moon");
	}
}