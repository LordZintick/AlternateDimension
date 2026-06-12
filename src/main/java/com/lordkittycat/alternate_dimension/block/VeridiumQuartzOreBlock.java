package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

public class VeridiumQuartzOreBlock extends Block {
	public VeridiumQuartzOreBlock() {
		super(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops());
	}
}