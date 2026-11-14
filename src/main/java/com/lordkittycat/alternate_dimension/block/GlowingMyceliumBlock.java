package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;

public class GlowingMyceliumBlock extends Block {
	public GlowingMyceliumBlock(BlockBehaviour.Properties properties) {
		super(properties.sound(SoundType.ROOTED_DIRT).strength(0.5f, 1f).lightLevel(blockstate -> 5));
	}
}