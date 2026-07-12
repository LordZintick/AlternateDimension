package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.SlabBlock;

public class ScorchedEarthSlabBlock extends SlabBlock {
	public ScorchedEarthSlabBlock(BlockBehaviour.Properties properties) {
		super(properties.sound(SoundType.TUFF).strength(1f, 2f).requiresCorrectToolForDrops());
	}
}