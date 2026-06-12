package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;

public class AluminumBlockBlock extends Block {
	public AluminumBlockBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(5f, 6f).requiresCorrectToolForDrops());
	}
}