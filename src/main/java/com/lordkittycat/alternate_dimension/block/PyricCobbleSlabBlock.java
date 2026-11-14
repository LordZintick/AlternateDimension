package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SlabBlock;

public class PyricCobbleSlabBlock extends SlabBlock {
	public PyricCobbleSlabBlock(BlockBehaviour.Properties properties) {
		super(properties.strength(2.2f, 10f).requiresCorrectToolForDrops());
	}
}