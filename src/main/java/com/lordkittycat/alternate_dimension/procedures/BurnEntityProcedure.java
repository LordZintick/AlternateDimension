package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.entity.Entity;

public class BurnEntityProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		entity.igniteForSeconds(5);
	}
}