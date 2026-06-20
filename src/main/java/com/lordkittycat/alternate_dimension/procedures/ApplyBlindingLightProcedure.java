package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;

import com.lordkittycat.alternate_dimension.init.AlternateDimensionModMobEffects;

public class ApplyBlindingLightProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
			_entity.addEffect(new MobEffectInstance(AlternateDimensionModMobEffects.BLINDING_LIGHT, 60, 0, false, false));
	}
}