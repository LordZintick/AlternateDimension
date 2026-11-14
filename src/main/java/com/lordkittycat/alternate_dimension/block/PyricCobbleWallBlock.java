package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.WallBlock;

public class PyricCobbleWallBlock extends WallBlock {
	public PyricCobbleWallBlock(BlockBehaviour.Properties properties) {
		super(properties.strength(2.2f, 10f).requiresCorrectToolForDrops().forceSolidOn());
	}
}