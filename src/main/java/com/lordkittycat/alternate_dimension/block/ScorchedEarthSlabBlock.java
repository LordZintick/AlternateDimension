package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.SlabBlock;

public class ScorchedEarthSlabBlock extends SlabBlock {
	public ScorchedEarthSlabBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.TUFF).strength(1f, 2f).requiresCorrectToolForDrops());
	}
}