package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

public class PyricBricksBlock extends Block {
	public PyricBricksBlock() {
		super(BlockBehaviour.Properties.of().strength(2f, 10f).requiresCorrectToolForDrops());
	}
}