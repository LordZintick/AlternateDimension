package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SlabBlock;

public class PureBrickSlabBlock extends SlabBlock {
	public PureBrickSlabBlock(BlockBehaviour.Properties properties) {
		super(properties.strength(2f, 5f).requiresCorrectToolForDrops());
	}
}