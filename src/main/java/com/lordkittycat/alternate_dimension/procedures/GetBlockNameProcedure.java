package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;

public class GetBlockNameProcedure {
	public static String execute(LevelAccessor world, double x, double y, double z) {
		if (world instanceof Level _level)
			_level.updateNeighborsAt(BlockPos.containing(x, y, z), _level.getBlockState(BlockPos.containing(x, y, z)).getBlock());
		return Component.translatable(world.getBlockState(BlockPos.containing(x, y, z)).getBlock().getDescriptionId()).getString();
	}
}