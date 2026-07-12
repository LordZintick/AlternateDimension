package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

public class VeridiumHolystoneOreBlock extends Block {
	public VeridiumHolystoneOreBlock(BlockBehaviour.Properties properties) {
		super(properties.strength(3f).lightLevel(blockstate -> 3).requiresCorrectToolForDrops());
	}
}