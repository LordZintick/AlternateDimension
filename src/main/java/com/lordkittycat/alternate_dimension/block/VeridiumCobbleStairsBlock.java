package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.Blocks;

public class VeridiumCobbleStairsBlock extends StairBlock {
	public VeridiumCobbleStairsBlock(BlockBehaviour.Properties properties) {
		super(Blocks.AIR.defaultBlockState(), properties.strength(2f, 3f).requiresCorrectToolForDrops());
	}

	@Override
	public float getExplosionResistance() {
		return 3f;
	}
}