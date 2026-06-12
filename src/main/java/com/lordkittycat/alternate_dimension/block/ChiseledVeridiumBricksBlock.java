package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

public class ChiseledVeridiumBricksBlock extends Block {
	public ChiseledVeridiumBricksBlock() {
		super(BlockBehaviour.Properties.of().strength(1.5f, 4f).requiresCorrectToolForDrops().hasPostProcess((bs, br, bp) -> true).emissiveRendering((bs, br, bp) -> true));
	}
}