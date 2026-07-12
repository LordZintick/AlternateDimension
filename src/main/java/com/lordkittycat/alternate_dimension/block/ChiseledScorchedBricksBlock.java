package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

public class ChiseledScorchedBricksBlock extends Block {
	public ChiseledScorchedBricksBlock(BlockBehaviour.Properties properties) {
		super(properties.strength(1f, 2f).requiresCorrectToolForDrops());
	}
}