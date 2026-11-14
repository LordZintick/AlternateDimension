package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.entity.Entity;

import com.lordkittycat.alternate_dimension.network.AlternateDimensionModVariables;

public class GetManaBarStateProcedure {
	public static double execute(Entity entity) {
		if (entity == null)
			return 0;
		return Math.floor((entity.getData(AlternateDimensionModVariables.PLAYER_VARIABLES).mana / entity.getData(AlternateDimensionModVariables.PLAYER_VARIABLES).maxMana) * 18);
	}
}