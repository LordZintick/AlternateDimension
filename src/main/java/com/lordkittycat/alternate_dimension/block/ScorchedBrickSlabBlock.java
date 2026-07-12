package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SlabBlock;

public class ScorchedBrickSlabBlock extends SlabBlock {
	public ScorchedBrickSlabBlock(BlockBehaviour.Properties properties) {
		super(properties.strength(1f, 2f).requiresCorrectToolForDrops());
	}
}