package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;

public class ShaleBlock extends Block {
	public ShaleBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.BASALT).strength(1f).requiresCorrectToolForDrops());
	}
}