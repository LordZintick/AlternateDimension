package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

public class ChiseledScorchedBricksBlock extends Block {
	public ChiseledScorchedBricksBlock() {
		super(BlockBehaviour.Properties.of().strength(1f, 2f).requiresCorrectToolForDrops());
	}
}