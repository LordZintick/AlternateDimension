package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.WallBlock;

public class CorruptBrickWallBlock extends WallBlock {
	public CorruptBrickWallBlock() {
		super(BlockBehaviour.Properties.of().strength(2f, 5f).requiresCorrectToolForDrops().forceSolidOn());
	}
}