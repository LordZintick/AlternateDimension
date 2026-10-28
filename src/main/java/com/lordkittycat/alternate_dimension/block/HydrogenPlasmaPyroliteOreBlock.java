package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;

public class HydrogenPlasmaPyroliteOreBlock extends Block {
	public HydrogenPlasmaPyroliteOreBlock(BlockBehaviour.Properties properties) {
		super(properties.sound(SoundType.SAND).strength(2f).requiresCorrectToolForDrops());
	}
}