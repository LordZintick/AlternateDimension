package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

public class VeridiumGemOreBlock extends Block {
	public VeridiumGemOreBlock(BlockBehaviour.Properties properties) {
		super(properties.strength(3f, 3.5f).requiresCorrectToolForDrops());
	}
}