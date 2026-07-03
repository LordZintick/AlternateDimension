package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

public class CorruptBricksBlock extends Block {
	public CorruptBricksBlock() {
		super(BlockBehaviour.Properties.of().strength(2f, 5f).requiresCorrectToolForDrops());
	}
}