package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

import com.lordkittycat.alternate_dimension.init.AlternateDimensionModBlocks;

public class UpdateBlockShaperCasingProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		{
			BlockPos _pos = BlockPos.containing(x, y, z);
			BlockState _bs = world.getBlockState(_pos);
			if (_bs.getBlock().getStateDefinition().getProperty("north") instanceof BooleanProperty _booleanProp)
				world.setBlock(_pos, _bs.setValue(_booleanProp, ((world.getBlockState(BlockPos.containing(x + 0, y + 0, z + 1))).getBlock() == AlternateDimensionModBlocks.BLOCK_SHAPER_CASING.get())), 3);
		}
		{
			BlockPos _pos = BlockPos.containing(x, y, z);
			BlockState _bs = world.getBlockState(_pos);
			if (_bs.getBlock().getStateDefinition().getProperty("east") instanceof BooleanProperty _booleanProp)
				world.setBlock(_pos, _bs.setValue(_booleanProp, ((world.getBlockState(BlockPos.containing(x + -1, y + 0, z + 0))).getBlock() == AlternateDimensionModBlocks.BLOCK_SHAPER_CASING.get())), 3);
		}
		{
			BlockPos _pos = BlockPos.containing(x, y, z);
			BlockState _bs = world.getBlockState(_pos);
			if (_bs.getBlock().getStateDefinition().getProperty("south") instanceof BooleanProperty _booleanProp)
				world.setBlock(_pos, _bs.setValue(_booleanProp, ((world.getBlockState(BlockPos.containing(x + 0, y + 0, z + -1))).getBlock() == AlternateDimensionModBlocks.BLOCK_SHAPER_CASING.get())), 3);
		}
		{
			BlockPos _pos = BlockPos.containing(x, y, z);
			BlockState _bs = world.getBlockState(_pos);
			if (_bs.getBlock().getStateDefinition().getProperty("west") instanceof BooleanProperty _booleanProp)
				world.setBlock(_pos, _bs.setValue(_booleanProp, ((world.getBlockState(BlockPos.containing(x + 1, y + 0, z + 0))).getBlock() == AlternateDimensionModBlocks.BLOCK_SHAPER_CASING.get())), 3);
		}
	}
}