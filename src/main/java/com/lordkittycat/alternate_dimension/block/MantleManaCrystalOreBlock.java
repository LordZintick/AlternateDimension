package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

public class MantleManaCrystalOreBlock extends Block {
	public MantleManaCrystalOreBlock(BlockBehaviour.Properties properties) {
		super(properties.strength(2.5f, 3.5f).requiresCorrectToolForDrops());
	}
}