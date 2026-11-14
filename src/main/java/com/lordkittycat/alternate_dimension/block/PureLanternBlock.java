package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;

public class PureLanternBlock extends Block {
	public PureLanternBlock(BlockBehaviour.Properties properties) {
		super(properties.sound(SoundType.GLASS).strength(2f, 1f).lightLevel(blockstate -> 15).requiresCorrectToolForDrops().postProcess((bs, br, bp) -> bp).emissiveRendering((bs, br, bp) -> true));
	}
}