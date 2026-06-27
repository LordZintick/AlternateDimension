package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

public class VeridiumEvilstoneOreBlock extends Block {
	public VeridiumEvilstoneOreBlock() {
		super(BlockBehaviour.Properties.of().strength(3f).lightLevel(blockstate -> 3).requiresCorrectToolForDrops());
	}
}