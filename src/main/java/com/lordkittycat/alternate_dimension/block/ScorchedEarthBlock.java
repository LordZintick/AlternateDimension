package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;

public class ScorchedEarthBlock extends Block {
	public ScorchedEarthBlock(BlockBehaviour.Properties properties) {
		super(properties.sound(SoundType.TUFF).strength(1f, 2f).requiresCorrectToolForDrops());
	}
}