package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

public class PyricCobbleBlock extends Block {
	public PyricCobbleBlock() {
		super(BlockBehaviour.Properties.of().strength(2.2f, 10f).requiresCorrectToolForDrops());
	}
}