package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.SlabBlock;

public class ShaleSlabBlock extends SlabBlock {
	public ShaleSlabBlock(BlockBehaviour.Properties properties) {
		super(properties.sound(SoundType.BASALT).strength(1f).requiresCorrectToolForDrops());
	}
}