package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.Blocks;

public class CompressedCobblestoneStairsBlock extends StairBlock {
	public CompressedCobblestoneStairsBlock(BlockBehaviour.Properties properties) {
		super(Blocks.AIR.defaultBlockState(), properties.strength(3f, 15f).requiresCorrectToolForDrops());
	}

	@Override
	public float getExplosionResistance() {
		return 15f;
	}
}