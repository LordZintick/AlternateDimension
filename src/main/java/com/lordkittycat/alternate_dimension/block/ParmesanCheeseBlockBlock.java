package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;

public class ParmesanCheeseBlockBlock extends Block {
	public ParmesanCheeseBlockBlock(BlockBehaviour.Properties properties) {
		super(properties.sound(SoundType.SLIME_BLOCK).strength(2f));
	}
}