package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

public class VeridiumSulfurOreBlock extends Block {
	public VeridiumSulfurOreBlock() {
		super(BlockBehaviour.Properties.of().strength(2.5f, 3.5f).requiresCorrectToolForDrops());
	}
}