package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;

import com.lordkittycat.alternate_dimension.init.AlternateDimensionModEntities;

public class SpawnRandomBeetleProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double rand = 0;
		rand = Math.round(Mth.nextDouble(RandomSource.create(), 0, 4));
		if (rand == 0) {
			if (world instanceof ServerLevel _level) {
				Entity entityToSpawn = AlternateDimensionModEntities.BEETLE_BRUTE.get().spawn(_level, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
				if (entityToSpawn != null) {
					entityToSpawn.setYRot(world.getRandom().nextFloat() * 360F);
				}
			}
		} else if (rand == 1) {
			if (world instanceof ServerLevel _level) {
				Entity entityToSpawn = AlternateDimensionModEntities.BEETLE_CHASER.get().spawn(_level, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
				if (entityToSpawn != null) {
					entityToSpawn.setYRot(world.getRandom().nextFloat() * 360F);
				}
			}
		} else {
			if (world instanceof ServerLevel _level) {
				Entity entityToSpawn = AlternateDimensionModEntities.BEETLE.get().spawn(_level, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
				if (entityToSpawn != null) {
					entityToSpawn.setYRot(world.getRandom().nextFloat() * 360F);
				}
			}
		}
	}
}