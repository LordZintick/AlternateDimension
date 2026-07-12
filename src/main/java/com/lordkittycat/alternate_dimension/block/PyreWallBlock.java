package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.WallBlock;

public class PyreWallBlock extends WallBlock {
	public PyreWallBlock(BlockBehaviour.Properties properties) {
		super(properties.strength(2f, 10f).requiresCorrectToolForDrops().forceSolidOn());
	}
}