package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.Blocks;

public class VeridiumBricksStairsBlock extends StairBlock {
	public VeridiumBricksStairsBlock(BlockBehaviour.Properties properties) {
		super(Blocks.AIR.defaultBlockState(), properties.strength(1.5f, 4f).requiresCorrectToolForDrops());
	}

	@Override
	public float getExplosionResistance() {
		return 4f;
	}
}