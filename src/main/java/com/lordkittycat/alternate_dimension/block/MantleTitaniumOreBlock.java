package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

public class MantleTitaniumOreBlock extends Block {
	public MantleTitaniumOreBlock(BlockBehaviour.Properties properties) {
		super(properties.strength(9f, 12.0411234264f).requiresCorrectToolForDrops().instrument(NoteBlockInstrument.BASEDRUM));
	}
}