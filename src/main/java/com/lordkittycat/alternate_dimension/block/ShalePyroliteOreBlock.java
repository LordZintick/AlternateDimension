package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;

public class ShalePyroliteOreBlock extends Block {
	public ShalePyroliteOreBlock(BlockBehaviour.Properties properties) {
		super(properties.sound(SoundType.BASALT).strength(2.3f, 2f).requiresCorrectToolForDrops());
	}
}