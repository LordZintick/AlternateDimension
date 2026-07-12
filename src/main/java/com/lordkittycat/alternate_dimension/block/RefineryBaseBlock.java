package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;

public class RefineryBaseBlock extends Block {
	public RefineryBaseBlock(BlockBehaviour.Properties properties) {
		super(properties.sound(SoundType.METAL).strength(3f).requiresCorrectToolForDrops());
	}
}