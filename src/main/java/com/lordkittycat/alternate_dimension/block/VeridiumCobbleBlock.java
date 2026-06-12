package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

public class VeridiumCobbleBlock extends Block {
	public VeridiumCobbleBlock() {
		super(BlockBehaviour.Properties.of().strength(2f, 3f).requiresCorrectToolForDrops());
	}
}