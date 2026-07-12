package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.WallBlock;

public class VeridiumCobbleWallBlock extends WallBlock {
	public VeridiumCobbleWallBlock(BlockBehaviour.Properties properties) {
		super(properties.strength(2f, 3f).requiresCorrectToolForDrops().forceSolidOn());
	}
}