package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.Blocks;

public class CorruptBrickStairsBlock extends StairBlock {
	public CorruptBrickStairsBlock() {
		super(Blocks.AIR.defaultBlockState(), BlockBehaviour.Properties.of().strength(2f, 5f).requiresCorrectToolForDrops());
	}

	@Override
	public float getExplosionResistance() {
		return 5f;
	}
}