package com.lordkittycat.alternate_dimension.potion;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

import com.lordkittycat.alternate_dimension.procedures.ApplyInvulnerabilityProcedure;

public class GraceEffectMobEffect extends MobEffect {
	public GraceEffectMobEffect() {
		super(MobEffectCategory.BENEFICIAL, -256);
	}

	@Override
	public void onEffectStarted(LivingEntity entity, int amplifier) {
		ApplyInvulnerabilityProcedure.execute(entity);
	}
}