package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.WallBlock;

public class CorruptBrickWallBlock extends WallBlock {
	public CorruptBrickWallBlock(BlockBehaviour.Properties properties) {
		super(properties.strength(2f, 5f).requiresCorrectToolForDrops().forceSolidOn());
	}
}