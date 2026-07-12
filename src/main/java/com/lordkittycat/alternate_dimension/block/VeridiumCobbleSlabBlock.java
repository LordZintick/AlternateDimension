package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SlabBlock;

public class VeridiumCobbleSlabBlock extends SlabBlock {
	public VeridiumCobbleSlabBlock(BlockBehaviour.Properties properties) {
		super(properties.strength(2f, 3f).requiresCorrectToolForDrops());
	}
}