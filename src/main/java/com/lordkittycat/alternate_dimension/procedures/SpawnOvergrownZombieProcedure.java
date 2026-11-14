package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;

import com.lordkittycat.alternate_dimension.init.AlternateDimensionModMobEffects;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModEntities;

public class SpawnOvergrownZombieProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		Entity zombie = null;
		zombie = world instanceof ServerLevel _level2
				? AlternateDimensionModEntities.OVERGROWN_ZOMBIE.get().spawn(_level2, BlockPos.containing(x + Mth.nextInt(RandomSource.create(), -10, 10), y, z + Mth.nextInt(RandomSource.create(), -10, 10)), EntitySpawnReason.MOB_SUMMONED)
				: null;
		if (zombie instanceof LivingEntity _entity && !_entity.level().isClientSide())
			_entity.addEffect(new MobEffectInstance(AlternateDimensionModMobEffects.LIGHT_FOOTED_EFFECT, 200, 0));
	}
}