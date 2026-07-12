package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SlabBlock;

public class CorruptBrickSlabBlock extends SlabBlock {
	public CorruptBrickSlabBlock(BlockBehaviour.Properties properties) {
		super(properties.strength(2f, 5f).requiresCorrectToolForDrops());
	}
}