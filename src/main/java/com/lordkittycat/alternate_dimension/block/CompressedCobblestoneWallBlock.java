package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.WallBlock;

public class CompressedCobblestoneWallBlock extends WallBlock {
	public CompressedCobblestoneWallBlock() {
		super(BlockBehaviour.Properties.of().strength(3f, 15f).requiresCorrectToolForDrops().forceSolidOn());
	}
}