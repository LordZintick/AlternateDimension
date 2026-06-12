package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

public class SkyStoneBlock extends Block {
	public SkyStoneBlock() {
		super(BlockBehaviour.Properties.of().strength(3f, 10f));
	}
}