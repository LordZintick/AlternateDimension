package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;

public class PermafrostBlockBlock extends Block {
	public PermafrostBlockBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.SNOW).strength(1f, 25f));
	}
}