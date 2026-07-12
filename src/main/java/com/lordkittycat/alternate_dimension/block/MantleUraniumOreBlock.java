package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

public class MantleUraniumOreBlock extends Block {
	public MantleUraniumOreBlock(BlockBehaviour.Properties properties) {
		super(properties.strength(3f).requiresCorrectToolForDrops());
	}
}