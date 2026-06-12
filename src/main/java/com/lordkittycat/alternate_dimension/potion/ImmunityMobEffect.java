package com.lordkittycat.alternate_dimension.potion;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

import com.lordkittycat.alternate_dimension.procedures.RemoveNegativeEffectsProcedure;

public class ImmunityMobEffect extends MobEffect {
	public ImmunityMobEffect() {
		super(MobEffectCategory.BENEFICIAL, -4761769);
	}

	@Override
	public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
		return true;
	}

	@Override
	public boolean applyEffectTick(LivingEntity entity, int amplifier) {
		RemoveNegativeEffectsProcedure.execute(entity);
		return super.applyEffectTick(entity, amplifier);
	}
}