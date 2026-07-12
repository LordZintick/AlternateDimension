package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SlabBlock;

public class VeridiumSlabBlock extends SlabBlock {
	public VeridiumSlabBlock(BlockBehaviour.Properties properties) {
		super(properties.strength(1.5f, 2f).requiresCorrectToolForDrops());
	}
}