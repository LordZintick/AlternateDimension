package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.GameType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;

import java.util.Comparator;

public class SpawnBeetlesProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (!world.getEntitiesOfClass(Player.class, new AABB(Vec3.ZERO, Vec3.ZERO).move(new Vec3(x, y, z)).inflate(16 / 2d), e -> true).isEmpty()) {
			if ((findEntityInWorldRange(world, Player.class, x, y, z, 16)) instanceof Player _plr2 && _plr2.gameMode() == GameType.SURVIVAL) {
				for (int index63 = 0; index63 < Mth.nextInt(RandomSource.create(), 3, 7); index63++) {
					SpawnRandomBeetleProcedure.execute(world, x + Mth.nextInt(RandomSource.create(), -40, 40) / 10d, y, z + Mth.nextInt(RandomSource.create(), -40, 40) / 10d);
				}
			}
		}
	}

	private static Entity findEntityInWorldRange(LevelAccessor world, Class<? extends Entity> clazz, double x, double y, double z, double range) {
		return (Entity) world.getEntitiesOfClass(clazz, AABB.ofSize(new Vec3(x, y, z), range, range, range), e -> true).stream().sorted(Comparator.comparingDouble(e -> e.distanceToSqr(x, y, z))).findFirst().orElse(null);
	}
}