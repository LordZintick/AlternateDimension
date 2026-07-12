package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;

public class SimpleMachineCasingBlock extends Block {
	public SimpleMachineCasingBlock(BlockBehaviour.Properties properties) {
		super(properties.sound(SoundType.METAL).strength(2f, 10f).requiresCorrectToolForDrops());
	}
}