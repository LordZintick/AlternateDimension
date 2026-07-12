package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.SlabBlock;

public class ShaleBricksSlabBlock extends SlabBlock {
	public ShaleBricksSlabBlock(BlockBehaviour.Properties properties) {
		super(properties.sound(SoundType.BASALT).strength(2f).requiresCorrectToolForDrops());
	}
}