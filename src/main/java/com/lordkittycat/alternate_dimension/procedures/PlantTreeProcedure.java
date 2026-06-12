package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class PlantTreeProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (Blocks.OAK_SAPLING.defaultBlockState().canSurvive(world, BlockPos.containing(x, y, z))) {
			world.setBlock(BlockPos.containing(x, y, z), Blocks.OAK_SAPLING.defaultBlockState(), 3);
		}
	}
}