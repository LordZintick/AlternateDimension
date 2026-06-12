package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;

public class SpawnBeetlesProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (!world.getEntitiesOfClass(Player.class, new AABB(Vec3.ZERO, Vec3.ZERO).move(new Vec3(x, y, z)).inflate(16 / 2d), e -> true).isEmpty()) {
			for (int index0 = 0; index0 < Mth.nextInt(RandomSource.create(), 3, 7); index0++) {
				SpawnRandomBeetleProcedure.execute(world, x + Mth.nextInt(RandomSource.create(), -40, 40) / 10d, y, z + Mth.nextInt(RandomSource.create(), -40, 40) / 10d);
			}
		}
	}
}