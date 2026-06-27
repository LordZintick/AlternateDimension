package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

public class MantleHolystoneOreBlock extends Block {
	public MantleHolystoneOreBlock() {
		super(BlockBehaviour.Properties.of().strength(3f).lightLevel(blockstate -> 3).requiresCorrectToolForDrops());
	}
}