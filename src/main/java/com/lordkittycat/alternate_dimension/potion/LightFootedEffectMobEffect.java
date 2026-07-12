package com.lordkittycat.alternate_dimension.potion;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.server.level.ServerLevel;

import com.lordkittycat.alternate_dimension.procedures.NegateFallDamageProcedure;

public class LightFootedEffectMobEffect extends MobEffect {
	public LightFootedEffectMobEffect() {
		super(MobEffectCategory.BENEFICIAL, -5000269);
	}

	@Override
	public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
		return true;
	}

	@Override
	public boolean applyEffectTick(ServerLevel level, LivingEntity entity, int amplifier) {
		NegateFallDamageProcedure.execute(entity);
		return super.applyEffectTick(level, entity, amplifier);
	}
}