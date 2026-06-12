package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

public class CheckForCloudProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		return world.getBiome(BlockPos.containing(x, world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) x, (int) z), z)).is(ResourceLocation.parse("alternate_dimension:highlands")) && y >= 200;
	}
}