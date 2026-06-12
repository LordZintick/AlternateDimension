package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

public class MantleSulfurOreBlock extends Block {
	public MantleSulfurOreBlock() {
		super(BlockBehaviour.Properties.of().strength(2.5f, 3.5f).requiresCorrectToolForDrops());
	}
}