package com.lordkittycat.alternate_dimension.potion;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.server.level.ServerLevel;

import com.lordkittycat.alternate_dimension.procedures.MagnetizeItemsProcedure;

public class MagnetismMobEffect extends MobEffect {
	public MagnetismMobEffect() {
		super(MobEffectCategory.BENEFICIAL, -10354433);
	}

	@Override
	public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
		return true;
	}

	@Override
	public boolean applyEffectTick(ServerLevel level, LivingEntity entity, int amplifier) {
		MagnetizeItemsProcedure.execute(level, entity.getX(), entity.getY(), entity.getZ());
		return super.applyEffectTick(level, entity, amplifier);
	}
}