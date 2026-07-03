package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

public class PureBricksBlock extends Block {
	public PureBricksBlock() {
		super(BlockBehaviour.Properties.of().strength(2f, 5f).requiresCorrectToolForDrops());
	}
}