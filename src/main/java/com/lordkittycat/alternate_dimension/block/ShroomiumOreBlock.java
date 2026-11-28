package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;

public class ShroomiumOreBlock extends Block {
	public ShroomiumOreBlock(BlockBehaviour.Properties properties) {
		super(properties.sound(SoundType.SHROOMLIGHT).strength(3f, 10f).requiresCorrectToolForDrops());
	}
}