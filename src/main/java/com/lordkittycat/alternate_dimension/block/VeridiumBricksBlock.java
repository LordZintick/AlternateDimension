package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

public class VeridiumBricksBlock extends Block {
	public VeridiumBricksBlock() {
		super(BlockBehaviour.Properties.of().strength(1.5f, 4f).requiresCorrectToolForDrops());
	}
}