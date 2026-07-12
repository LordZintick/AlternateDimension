package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.Blocks;

public class PyricCobbleStairsBlock extends StairBlock {
	public PyricCobbleStairsBlock(BlockBehaviour.Properties properties) {
		super(Blocks.AIR.defaultBlockState(), properties.strength(2.2f, 10f).requiresCorrectToolForDrops());
	}

	@Override
	public float getExplosionResistance() {
		return 10f;
	}
}