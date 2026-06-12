package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

public class ScorchedBricksBlock extends Block {
	public ScorchedBricksBlock() {
		super(BlockBehaviour.Properties.of().strength(1f, 2f).requiresCorrectToolForDrops());
	}
}