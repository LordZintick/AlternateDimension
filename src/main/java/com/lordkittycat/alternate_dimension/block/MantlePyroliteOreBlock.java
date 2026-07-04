package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

public class MantlePyroliteOreBlock extends Block {
	public MantlePyroliteOreBlock() {
		super(BlockBehaviour.Properties.of().strength(2.3f, 2f).requiresCorrectToolForDrops());
	}
}