package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.SlabBlock;

public class CloudBrickSlabBlock extends SlabBlock {
	public CloudBrickSlabBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.WOOL).strength(1f));
	}
}