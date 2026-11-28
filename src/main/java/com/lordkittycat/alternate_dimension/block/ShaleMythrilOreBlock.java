package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;

public class ShaleMythrilOreBlock extends Block {
	public ShaleMythrilOreBlock(BlockBehaviour.Properties properties) {
		super(properties.sound(SoundType.BASALT).strength(5f, 15f).requiresCorrectToolForDrops());
	}
}