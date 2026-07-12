package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.tags.TagKey;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.BlockPos;

public class CheckForHighlandsProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		return world.getBiome(BlockPos.containing(x, y, z)).is(TagKey.create(Registries.BIOME, Identifier.parse("alternate_dimension:highlands_biomes")));
	}
}