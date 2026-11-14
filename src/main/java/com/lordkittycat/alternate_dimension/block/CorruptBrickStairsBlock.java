package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.Blocks;

public class CorruptBrickStairsBlock extends StairBlock {
	public CorruptBrickStairsBlock(BlockBehaviour.Properties properties) {
		super(Blocks.AIR.defaultBlockState(), properties.strength(2f, 5f).requiresCorrectToolForDrops());
	}

	@Override
	public float getExplosionResistance() {
		return 5f;
	}
}