package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;

public class CloudBricksBlock extends Block {
	public CloudBricksBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.WOOL).strength(1f));
	}
}