package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SlabBlock;

public class PyricBricksSlabBlock extends SlabBlock {
	public PyricBricksSlabBlock() {
		super(BlockBehaviour.Properties.of().strength(2f, 10f).requiresCorrectToolForDrops());
	}
}