package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.WallBlock;

public class VeridiumBricksWallBlock extends WallBlock {
	public VeridiumBricksWallBlock() {
		super(BlockBehaviour.Properties.of().strength(1.5f, 4f).requiresCorrectToolForDrops().forceSolidOn());
	}
}