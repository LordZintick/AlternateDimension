package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;

public class ShalePhosphorusOreBlock extends Block {
	public ShalePhosphorusOreBlock(BlockBehaviour.Properties properties) {
		super(properties.sound(SoundType.BASALT).strength(1.5f, 2f).requiresCorrectToolForDrops());
	}
}