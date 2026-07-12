package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

public class ChiseledPyricBricksBlock extends Block {
	public ChiseledPyricBricksBlock(BlockBehaviour.Properties properties) {
		super(properties.strength(2f, 10f).requiresCorrectToolForDrops());
	}
}