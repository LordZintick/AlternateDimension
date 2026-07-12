package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.GameType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;

import java.util.Comparator;

import com.lordkittycat.alternate_dimension.init.AlternateDimensionModEntities;

public class SpawnWaitersProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (!world.getEntitiesOfClass(Player.class, new AABB(Vec3.ZERO, Vec3.ZERO).move(new Vec3(x, y, z)).inflate(25 / 2d), e -> true).isEmpty()) {
			if ((findEntityInWorldRange(world, Player.class, x, y, z, 25)) instanceof Player _plr2 && _plr2.gameMode() == GameType.SURVIVAL
					|| (findEntityInWorldRange(world, Player.class, x, y, z, 25)) instanceof Player _plr4 && _plr4.gameMode() == GameType.ADVENTURE) {
				for (int index2 = 0; index2 < Mth.nextInt(RandomSource.create(), 1, 4); index2++) {
					if (world instanceof ServerLevel _level) {
						Entity entityToSpawn = AlternateDimensionModEntities.WAITER.get().spawn(_level, BlockPos.containing(x + Mth.nextInt(RandomSource.create(), -40, 40) / 10, y, z + Mth.nextInt(RandomSource.create(), -40, 40) / 10),
								EntitySpawnReason.MOB_SUMMONED);
						if (entityToSpawn != null) {
							entityToSpawn.setYRot(world.getRandom().nextFloat() * 360F);
						}
					}
				}
			}
		}
	}

	private static Entity findEntityInWorldRange(LevelAccessor world, Class<? extends Entity> clazz, double x, double y, double z, double range) {
		return (Entity) world.getEntitiesOfClass(clazz, AABB.ofSize(new Vec3(x, y, z), range, range, range), e -> true).stream().sorted(Comparator.comparingDouble(e -> e.distanceToSqr(x, y, z))).findFirst().orElse(null);
	}
}