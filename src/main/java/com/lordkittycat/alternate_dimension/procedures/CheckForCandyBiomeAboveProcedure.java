package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.resources.Identifier;
import net.minecraft.core.BlockPos;

public class CheckForCandyBiomeAboveProcedure {
	public static boolean execute(LevelAccessor world, double x, double z) {
		return world.getBiome(BlockPos.containing(x, world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) x, (int) z), z)).is(Identifier.parse("alternate_dimension:candy_biome"));
	}
}