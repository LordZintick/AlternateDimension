package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;

public class RandomBadProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (Mth.nextInt(RandomSource.create(), 0, 100) == 0) {
			if (world instanceof ServerLevel _level) {
				LightningBolt entityToSpawn = EntityType.LIGHTNING_BOLT.create(_level, EntitySpawnReason.TRIGGERED);
				entityToSpawn.snapTo(Vec3.atBottomCenterOf(BlockPos.containing(x, y, z)));;
				_level.addFreshEntity(entityToSpawn);
			}
		}
		if (Mth.nextInt(RandomSource.create(), 0, 125) == 0) {
			if (world instanceof Level _level && !_level.isClientSide())
				_level.explode(null, x, y, z, (float) 0.3, Level.ExplosionInteraction.MOB);
		}
		if (Mth.nextInt(RandomSource.create(), 0, 125) == 0) {
			entity.push((Mth.nextInt(RandomSource.create(), -10, 10) / 10), (Mth.nextInt(RandomSource.create(), -10, 10) / 10), (Mth.nextInt(RandomSource.create(), -10, 10) / 10));
		}
		if (Mth.nextInt(RandomSource.create(), 0, 175) == 0) {
			for (int _i1 = 0; _i1 < Mth.nextInt(RandomSource.create(), 3, 7); _i1++) {
				if (world instanceof ServerLevel _level) {
					Entity entityToSpawn = EntityType.ZOMBIE.spawn(_level, BlockPos.containing(x + Mth.nextInt(RandomSource.create(), -50, 50) / 10, y + 3, z + Mth.nextInt(RandomSource.create(), -50, 50) / 10), EntitySpawnReason.MOB_SUMMONED);
					if (entityToSpawn != null) {
						entityToSpawn.setYRot(world.getRandom().nextFloat() * 360F);
					}
				}
			}
		}
	}
}