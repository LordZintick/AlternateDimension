package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

public class VeridiumMythrilOreBlock extends Block {
	public VeridiumMythrilOreBlock(BlockBehaviour.Properties properties) {
		super(properties.strength(5f, 15f).requiresCorrectToolForDrops());
	}
}