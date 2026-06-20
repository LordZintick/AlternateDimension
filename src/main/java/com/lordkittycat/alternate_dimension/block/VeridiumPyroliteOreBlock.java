package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

public class VeridiumPyroliteOreBlock extends Block {
	public VeridiumPyroliteOreBlock() {
		super(BlockBehaviour.Properties.of().strength(2f).requiresCorrectToolForDrops());
	}
}