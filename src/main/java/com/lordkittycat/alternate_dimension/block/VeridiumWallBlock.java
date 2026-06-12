package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.WallBlock;

public class VeridiumWallBlock extends WallBlock {
	public VeridiumWallBlock() {
		super(BlockBehaviour.Properties.of().strength(1.5f, 2f).requiresCorrectToolForDrops().forceSolidOn());
	}
}