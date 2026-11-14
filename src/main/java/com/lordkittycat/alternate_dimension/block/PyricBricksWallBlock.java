package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.WallBlock;

public class PyricBricksWallBlock extends WallBlock {
	public PyricBricksWallBlock(BlockBehaviour.Properties properties) {
		super(properties.strength(2f, 10f).requiresCorrectToolForDrops().forceSolidOn());
	}
}