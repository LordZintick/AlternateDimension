package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

public class VeridiumQuartzOreBlock extends Block {
	public VeridiumQuartzOreBlock(BlockBehaviour.Properties properties) {
		super(properties.strength(3f).requiresCorrectToolForDrops());
	}
}