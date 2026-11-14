package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.entity.Entity;

import com.lordkittycat.alternate_dimension.entity.CalcifiedZombieEntity;

public class IsCalcifiedZombieShakingProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		return entity instanceof CalcifiedZombieEntity _datEntL0 && _datEntL0.getEntityData().get(CalcifiedZombieEntity.DATA_transforming);
	}
}