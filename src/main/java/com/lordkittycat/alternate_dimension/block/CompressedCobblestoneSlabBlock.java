package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SlabBlock;

public class CompressedCobblestoneSlabBlock extends SlabBlock {
	public CompressedCobblestoneSlabBlock() {
		super(BlockBehaviour.Properties.of().strength(3f, 15f).requiresCorrectToolForDrops());
	}
}