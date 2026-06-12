package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SlabBlock;

public class ScorchedBrickSlabBlock extends SlabBlock {
	public ScorchedBrickSlabBlock() {
		super(BlockBehaviour.Properties.of().strength(1f, 2f).requiresCorrectToolForDrops());
	}
}