package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;

public class WhippedCreamBlockBlock extends Block {
	public WhippedCreamBlockBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.WOOL).strength(0.2f, 0.5f));
	}
}