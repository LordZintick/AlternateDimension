package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.WallBlock;

public class PyricBricksWallBlock extends WallBlock {
	public PyricBricksWallBlock() {
		super(BlockBehaviour.Properties.of().strength(2f, 10f).requiresCorrectToolForDrops().forceSolidOn());
	}
}