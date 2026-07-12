package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;

public class WaffleBlockBlock extends Block {
	public WaffleBlockBlock(BlockBehaviour.Properties properties) {
		super(properties.sound(SoundType.WOOD).strength(2f));
	}
}