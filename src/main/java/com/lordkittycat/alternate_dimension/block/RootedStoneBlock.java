package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;

public class RootedStoneBlock extends Block {
	public RootedStoneBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.BASALT).strength(0.5f, 3f).requiresCorrectToolForDrops());
	}
}