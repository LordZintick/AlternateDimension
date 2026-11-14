package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

public class ApplyCryonicScimitarEffectsProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof LivingEntity _livEnt0 && _livEnt0.hasEffect(MobEffects.SLOWNESS)) {
			if ((entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(MobEffects.SLOWNESS) ? _livEnt.getEffect(MobEffects.SLOWNESS).getAmplifier() : 0) >= 2) {
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("entity.zombie_villager.cure")), SoundSource.PLAYERS, (float) 0.8, (float) 1.3);
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("entity.zombie_villager.cure")), SoundSource.PLAYERS, (float) 0.8, (float) 1.3, false);
					}
				}
				{
					Entity _ent = entity;
					if (_ent.level() instanceof ServerLevel _serverLevel) {
						_ent.hurtServer(_serverLevel, new DamageSource(world.holderOrThrow(DamageTypes.FREEZE)), 2);
					}
				}
			}
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 200, (entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(MobEffects.SLOWNESS) ? _livEnt.getEffect(MobEffects.SLOWNESS).getAmplifier() : 0) + 1));
			entity.setTicksFrozen(40 * (entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(MobEffects.SLOWNESS) ? _livEnt.getEffect(MobEffects.SLOWNESS).getAmplifier() : 0));
		} else {
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 200, 0));
		}
	}
}