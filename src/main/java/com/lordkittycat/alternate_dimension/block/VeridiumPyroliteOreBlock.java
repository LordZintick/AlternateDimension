package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

public class VeridiumPyroliteOreBlock extends Block {
	public VeridiumPyroliteOreBlock(BlockBehaviour.Properties properties) {
		super(properties.strength(2.3f, 2f).requiresCorrectToolForDrops());
	}
}