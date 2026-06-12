package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import com.lordkittycat.alternate_dimension.init.AlternateDimensionModMobEffects;

public class EntityHasBlindingLightProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		return entity instanceof LivingEntity _livEnt0 && _livEnt0.hasEffect(AlternateDimensionModMobEffects.BLINDING_LIGHT);
	}
}