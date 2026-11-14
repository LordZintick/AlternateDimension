package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;

public class BlockOfShroomiumBlock extends Block {
	public BlockOfShroomiumBlock(BlockBehaviour.Properties properties) {
		super(properties.sound(SoundType.METAL).strength(4f, 10f).lightLevel(blockstate -> 12).requiresCorrectToolForDrops().postProcess((bs, br, bp) -> bp).emissiveRendering((bs, br, bp) -> true));
	}
}