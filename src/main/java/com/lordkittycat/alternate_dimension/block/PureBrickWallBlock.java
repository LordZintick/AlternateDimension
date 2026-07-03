package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.WallBlock;

public class PureBrickWallBlock extends WallBlock {
	public PureBrickWallBlock() {
		super(BlockBehaviour.Properties.of().strength(2f, 5f).requiresCorrectToolForDrops().forceSolidOn());
	}
}