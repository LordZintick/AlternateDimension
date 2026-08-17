package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

public class UnbreakableVeridiumBricksBlock extends Block {
	public UnbreakableVeridiumBricksBlock(BlockBehaviour.Properties properties) {
		super(properties.strength(-1, 3600000));
	}
}