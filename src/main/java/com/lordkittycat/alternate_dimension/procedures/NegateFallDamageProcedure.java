package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.entity.Entity;

public class NegateFallDamageProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		entity.fallDistance = 0;
	}
}