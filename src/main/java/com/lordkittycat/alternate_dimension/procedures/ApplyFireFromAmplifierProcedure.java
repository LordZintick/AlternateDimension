package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.entity.Entity;

public class ApplyFireFromAmplifierProcedure {
	public static void execute(Entity entity, double amplifier) {
		if (entity == null)
			return;
		entity.igniteForSeconds((int) amplifier);
	}
}