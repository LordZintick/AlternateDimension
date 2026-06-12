package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;

public class RubyBlockBlock extends Block {
	public RubyBlockBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.AMETHYST).strength(1.5f, 5f).requiresCorrectToolForDrops());
	}
}