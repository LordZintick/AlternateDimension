package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.Blocks;

public class VeridiumStairsBlock extends StairBlock {
	public VeridiumStairsBlock(BlockBehaviour.Properties properties) {
		super(Blocks.AIR.defaultBlockState(), properties.strength(1.5f, 2f).requiresCorrectToolForDrops());
	}

	@Override
	public float getExplosionResistance() {
		return 2f;
	}
}