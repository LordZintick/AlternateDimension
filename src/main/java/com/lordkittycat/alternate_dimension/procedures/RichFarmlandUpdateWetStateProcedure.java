package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class RichFarmlandUpdateWetStateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double xoff = 0;
		double zoff = 0;
		xoff = -7;
		zoff = -7;
		for (int index10 = 0; index10 < 15; index10++) {
			for (int index11 = 0; index11 < 15; index11++) {
				if ((world.getFluidState(BlockPos.containing(x + xoff, y, z + zoff)).createLegacyBlock()).getBlock() == Blocks.WATER) {
					{
						BlockPos _pos = BlockPos.containing(x, y, z);
						BlockState _bs = world.getBlockState(_pos);
						if (_bs.getBlock().getStateDefinition().getProperty("wet") instanceof BooleanProperty _booleanProp)
							world.setBlock(_pos, _bs.setValue(_booleanProp, true), 3);
					}
					return;
				}
				zoff = zoff + 1;
			}
			zoff = -7;
			xoff = xoff + 1;
		}
		{
			BlockPos _pos = BlockPos.containing(x, y, z);
			BlockState _bs = world.getBlockState(_pos);
			if (_bs.getBlock().getStateDefinition().getProperty("wet") instanceof BooleanProperty _booleanProp)
				world.setBlock(_pos, _bs.setValue(_booleanProp, false), 3);
		}
	}
}