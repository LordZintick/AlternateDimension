package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.WallBlock;

public class VeridiumCobbleWallBlock extends WallBlock {
	public VeridiumCobbleWallBlock() {
		super(BlockBehaviour.Properties.of().strength(2f, 3f).requiresCorrectToolForDrops().forceSolidOn());
	}
}