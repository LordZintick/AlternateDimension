package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;

public class ShaleManaCrystalOreBlock extends Block {
	public ShaleManaCrystalOreBlock(BlockBehaviour.Properties properties) {
		super(properties.sound(SoundType.BASALT).strength(2.5f, 3.5f).requiresCorrectToolForDrops());
	}
}