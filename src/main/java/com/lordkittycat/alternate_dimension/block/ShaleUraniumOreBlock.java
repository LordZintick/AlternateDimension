package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

public class ShaleUraniumOreBlock extends Block {
	public ShaleUraniumOreBlock() {
		super(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops());
	}
}