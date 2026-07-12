package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SlabBlock;

public class VeridiumBricksSlabBlock extends SlabBlock {
	public VeridiumBricksSlabBlock(BlockBehaviour.Properties properties) {
		super(properties.strength(1.5f, 4f).requiresCorrectToolForDrops());
	}
}