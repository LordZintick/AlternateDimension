package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

public class CorruptBricksBlock extends Block {
	public CorruptBricksBlock(BlockBehaviour.Properties properties) {
		super(properties.strength(2f, 5f).requiresCorrectToolForDrops());
	}
}