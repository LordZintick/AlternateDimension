package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

public class MantleAluminumOreBlock extends Block {
	public MantleAluminumOreBlock(BlockBehaviour.Properties properties) {
		super(properties.strength(1.5f, 3f).requiresCorrectToolForDrops());
	}
}