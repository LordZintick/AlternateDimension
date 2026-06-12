package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;

public class SaltBlockBlock extends Block {
	public SaltBlockBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.DRIPSTONE_BLOCK).strength(2f).requiresCorrectToolForDrops());
	}
}