package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;

public class ShaleHolystoneOreBlock extends Block {
	public ShaleHolystoneOreBlock(BlockBehaviour.Properties properties) {
		super(properties.sound(SoundType.BASALT).strength(3f).lightLevel(blockstate -> 3).requiresCorrectToolForDrops());
	}
}