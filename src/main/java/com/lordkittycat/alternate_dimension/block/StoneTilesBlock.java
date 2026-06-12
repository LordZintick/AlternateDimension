package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

public class StoneTilesBlock extends Block {
	public StoneTilesBlock() {
		super(BlockBehaviour.Properties.of().strength(2f, 6f));
	}
}