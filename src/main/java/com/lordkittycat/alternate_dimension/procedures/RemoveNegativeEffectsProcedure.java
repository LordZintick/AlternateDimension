package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;

import com.lordkittycat.alternate_dimension.init.AlternateDimensionModMobEffects;

public class RemoveNegativeEffectsProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if ((entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(AlternateDimensionModMobEffects.IMMUNITY) ? _livEnt.getEffect(AlternateDimensionModMobEffects.IMMUNITY).getAmplifier() : 0) == 0) {
			if (entity instanceof LivingEntity _entity)
				_entity.removeEffect(MobEffects.UNLUCK);
			if (entity instanceof LivingEntity _entity)
				_entity.removeEffect(MobEffects.CONFUSION);
			if (entity instanceof LivingEntity _entity)
				_entity.removeEffect(MobEffects.DIG_SLOWDOWN);
			if (entity instanceof LivingEntity _entity)
				_entity.removeEffect(MobEffects.HUNGER);
		}
		if ((entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(AlternateDimensionModMobEffects.IMMUNITY) ? _livEnt.getEffect(AlternateDimensionModMobEffects.IMMUNITY).getAmplifier() : 0) == 1) {
			if (entity instanceof LivingEntity _entity)
				_entity.removeEffect(AlternateDimensionModMobEffects.OSMIUM_HEART_EFFECT);
			if (entity instanceof LivingEntity _entity)
				_entity.removeEffect(MobEffects.POISON);
			if (entity instanceof LivingEntity _entity)
				_entity.removeEffect(MobEffects.MOVEMENT_SLOWDOWN);
			if (entity instanceof LivingEntity _entity)
				_entity.removeEffect(MobEffects.WEAKNESS);
			if (entity instanceof LivingEntity _entity)
				_entity.removeEffect(MobEffects.BLINDNESS);
		}
		if ((entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(AlternateDimensionModMobEffects.IMMUNITY) ? _livEnt.getEffect(AlternateDimensionModMobEffects.IMMUNITY).getAmplifier() : 0) == 2) {
			if (entity instanceof LivingEntity _entity)
				_entity.removeEffect(AlternateDimensionModMobEffects.CURSED_EFFECT);
			if (entity instanceof LivingEntity _entity)
				_entity.removeEffect(MobEffects.WITHER);
			if (entity instanceof LivingEntity _entity)
				_entity.removeEffect(MobEffects.LEVITATION);
			if (entity instanceof LivingEntity _entity)
				_entity.removeEffect(MobEffects.DARKNESS);
		}
	}
}