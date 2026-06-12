package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;

public class CompressedBarkBlock extends Block {
	public CompressedBarkBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(15f, 36000000f));
	}
}