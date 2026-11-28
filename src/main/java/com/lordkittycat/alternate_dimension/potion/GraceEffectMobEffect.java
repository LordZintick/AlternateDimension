package com.lordkittycat.alternate_dimension.potion;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.server.level.ServerLevel;

import com.lordkittycat.alternate_dimension.procedures.ApplyInvulnerabilityProcedure;

public class GraceEffectMobEffect extends MobEffect {
	public GraceEffectMobEffect() {
		super(MobEffectCategory.BENEFICIAL, -256);
	}

	@Override
	public void onEffectStarted(LivingEntity entity, int amplifier) {
		ApplyInvulnerabilityProcedure.execute(entity);
	}

	@Override
	public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
		return true;
	}

	@Override
	public boolean applyEffectTick(ServerLevel level, LivingEntity entity, int amplifier) {
		ApplyInvulnerabilityProcedure.execute(entity);
		return super.applyEffectTick(level, entity, amplifier);
	}
}