package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Blocks;

public class ShaleStairsBlock extends StairBlock {
	public ShaleStairsBlock(BlockBehaviour.Properties properties) {
		super(Blocks.AIR.defaultBlockState(), properties.sound(SoundType.BASALT).strength(1f).requiresCorrectToolForDrops());
	}

	@Override
	public float getExplosionResistance() {
		return 1f;
	}
}