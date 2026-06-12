package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.entity.Entity;

import com.lordkittycat.alternate_dimension.network.AlternateDimensionModVariables;

public class JetpackEngineOnProviderProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		if (entity.getData(AlternateDimensionModVariables.PLAYER_VARIABLES).jetpackEngine) {
			return "ON";
		}
		return "OFF";
	}
}