package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;

public class CandyCaneBlockBlock extends Block {
	public CandyCaneBlockBlock(BlockBehaviour.Properties properties) {
		super(properties.sound(SoundType.CALCITE).strength(2f));
	}
}