package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;

public class RandomJumpsProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (Mth.nextInt(RandomSource.create(), 0, 10) == 0) {
			entity.push(0, 0.5, 0);
		}
	}
}