package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.Blocks;

public class ScorchedBrickStairsBlock extends StairBlock {
	public ScorchedBrickStairsBlock() {
		super(Blocks.AIR.defaultBlockState(), BlockBehaviour.Properties.of().strength(1f, 2f).requiresCorrectToolForDrops());
	}

	@Override
	public float getExplosionResistance() {
		return 2f;
	}
}