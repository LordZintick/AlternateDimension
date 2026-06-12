package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.Blocks;

public class VeridiumCobbleStairsBlock extends StairBlock {
	public VeridiumCobbleStairsBlock() {
		super(Blocks.AIR.defaultBlockState(), BlockBehaviour.Properties.of().strength(2f, 3f).requiresCorrectToolForDrops());
	}

	@Override
	public float getExplosionResistance() {
		return 3f;
	}
}