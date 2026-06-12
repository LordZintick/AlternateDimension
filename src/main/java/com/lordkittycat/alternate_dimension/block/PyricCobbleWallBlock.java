package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.WallBlock;

public class PyricCobbleWallBlock extends WallBlock {
	public PyricCobbleWallBlock() {
		super(BlockBehaviour.Properties.of().strength(2.2f, 10f).requiresCorrectToolForDrops().forceSolidOn());
	}
}