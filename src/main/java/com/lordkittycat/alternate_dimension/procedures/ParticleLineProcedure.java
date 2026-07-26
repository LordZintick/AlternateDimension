package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.ParticleTypes;

public class ParticleLineProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Vec3 target) {
		if (target == null)
			return;
		Vec3 diff = Vec3.ZERO;
		double i = 0;
		double j = 0;
		double k = 0;
		diff = target.subtract((new Vec3(x, y, z)));
		i = 0;
		j = 0;
		k = 0;
		for (int index479 = 0; index479 < (int) (diff.x() * 2); index479++) {
			for (int index480 = 0; index480 < (int) (diff.y() * 2); index480++) {
				for (int index481 = 0; index481 < (int) (diff.z() * 2); index481++) {
					if (world instanceof ServerLevel _level)
						_level.sendParticles(ParticleTypes.COPPER_FIRE_FLAME, (x + i), (y + j), (z + k), 1, 0, 0, 0, 0);
					k = k + 0.5;
				}
				k = 0;
				j = j + 0.5;
			}
			j = 0;
			i = i + 0.5;
		}
	}
}