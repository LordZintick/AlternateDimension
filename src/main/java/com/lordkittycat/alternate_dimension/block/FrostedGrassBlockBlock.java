package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;

public class FrostedGrassBlockBlock extends Block {
	public FrostedGrassBlockBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.WET_GRASS).strength(1f));
	}
}