package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.core.particles.ParticleTypes;

public class ElectricParticleEffectProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		world.addParticle(ParticleTypes.ELECTRIC_SPARK, (x + Mth.nextInt(RandomSource.create(), 0, 160) / 10d), (y + Mth.nextInt(RandomSource.create(), 0, 160) / 10d), (z + Mth.nextInt(RandomSource.create(), 0, 160) / 10d), 0, (-0.05), 0);
	}
}