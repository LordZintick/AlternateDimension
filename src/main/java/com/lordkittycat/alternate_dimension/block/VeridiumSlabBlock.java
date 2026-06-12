package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SlabBlock;

public class VeridiumSlabBlock extends SlabBlock {
	public VeridiumSlabBlock() {
		super(BlockBehaviour.Properties.of().strength(1.5f, 2f).requiresCorrectToolForDrops());
	}
}