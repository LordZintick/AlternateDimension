package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;

public class ChanceBurnProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (Mth.nextInt(RandomSource.create(), 1, 5) == 2) {
			entity.igniteForSeconds(1);
		}
	}
}