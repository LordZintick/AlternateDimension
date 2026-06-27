package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;

public class GlowingMyceliumBlock extends Block {
	public GlowingMyceliumBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.ROOTED_DIRT).strength(1f, 10f).lightLevel(blockstate -> 5));
	}
}