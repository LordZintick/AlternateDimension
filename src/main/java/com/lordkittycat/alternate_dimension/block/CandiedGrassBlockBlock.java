package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;

public class CandiedGrassBlockBlock extends Block {
	public CandiedGrassBlockBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.MOSS).strength(1f));
	}
}