package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.entity.Entity;

import com.lordkittycat.alternate_dimension.network.AlternateDimensionModVariables;

public class CheckIfCanEnterPortalProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		return entity.getData(AlternateDimensionModVariables.PLAYER_VARIABLES).canEnterPortal;
	}
}