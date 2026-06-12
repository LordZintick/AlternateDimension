package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;

public class ShaleAluminumOreBlock extends Block {
	public ShaleAluminumOreBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.BASALT).strength(1.5f, 3f).requiresCorrectToolForDrops());
	}
}