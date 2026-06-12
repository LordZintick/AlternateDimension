package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.SlabBlock;

public class ShaleCobbleSlabBlock extends SlabBlock {
	public ShaleCobbleSlabBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.BASALT).strength(1.2f, 2f).requiresCorrectToolForDrops());
	}
}