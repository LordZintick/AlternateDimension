package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

public class VeridiumAluminumOreBlock extends Block {
	public VeridiumAluminumOreBlock() {
		super(BlockBehaviour.Properties.of().strength(1.5f, 3f).requiresCorrectToolForDrops());
	}
}