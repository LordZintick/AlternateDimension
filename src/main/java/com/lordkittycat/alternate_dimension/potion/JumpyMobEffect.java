package com.lordkittycat.alternate_dimension.potion;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

import com.lordkittycat.alternate_dimension.procedures.RandomJumpsProcedure;

public class JumpyMobEffect extends MobEffect {
	public JumpyMobEffect() {
		super(MobEffectCategory.NEUTRAL, -14588914);
	}

	@Override
	public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
		return true;
	}

	@Override
	public boolean applyEffectTick(LivingEntity entity, int amplifier) {
		RandomJumpsProcedure.execute(entity);
		return super.applyEffectTick(entity, amplifier);
	}
}