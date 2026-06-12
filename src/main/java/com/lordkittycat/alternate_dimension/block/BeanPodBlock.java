package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;

public class BeanPodBlock extends Block {
	public BeanPodBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.SWEET_BERRY_BUSH).strength(1f));
	}
}