package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;

import com.lordkittycat.alternate_dimension.init.AlternateDimensionModEntities;

public class SpawnCloudMobsProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (!world.getEntitiesOfClass(Player.class, new AABB(Vec3.ZERO, Vec3.ZERO).move(new Vec3(x, y, z)).inflate(16 / 2d), e -> true).isEmpty()) {
			if (Mth.nextInt(RandomSource.create(), 0, 1) == 0) {
				for (int index0 = 0; index0 < Mth.nextInt(RandomSource.create(), 1, 2); index0++) {
					if (world instanceof ServerLevel _level) {
						Entity entityToSpawn = AlternateDimensionModEntities.CLOUD_KITTY.get().spawn(_level, BlockPos.containing(x + Mth.nextInt(RandomSource.create(), -20, 20) / 10, y + 25, z + Mth.nextInt(RandomSource.create(), -20, 20) / 10),
								MobSpawnType.MOB_SUMMONED);
						if (entityToSpawn != null) {
							entityToSpawn.setYRot(world.getRandom().nextFloat() * 360F);
						}
					}
				}
			} else {
				for (int index1 = 0; index1 < Mth.nextInt(RandomSource.create(), 1, 2); index1++) {
					if (world instanceof ServerLevel _level) {
						Entity entityToSpawn = AlternateDimensionModEntities.CLOUDY_WOLF.get().spawn(_level, BlockPos.containing(x + Mth.nextInt(RandomSource.create(), -20, 20) / 10, y + 25, z + Mth.nextInt(RandomSource.create(), -20, 20) / 10),
								MobSpawnType.MOB_SUMMONED);
						if (entityToSpawn != null) {
							entityToSpawn.setYRot(world.getRandom().nextFloat() * 360F);
						}
					}
				}
			}
		}
	}
}