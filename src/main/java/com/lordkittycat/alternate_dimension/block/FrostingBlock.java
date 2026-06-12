package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;

public class FrostingBlock extends Block {
	public FrostingBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.MOSS).strength(0.65f, 0.5f));
	}
}