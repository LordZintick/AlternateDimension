package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

public class ChiseledVeridiumBricksBlock extends Block {
	public ChiseledVeridiumBricksBlock(BlockBehaviour.Properties properties) {
		super(properties.strength(1.5f, 4f).requiresCorrectToolForDrops().postProcess((bs, br, bp) -> bp).emissiveRendering((bs, br, bp) -> true));
	}
}