package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Blocks;

public class ShaleBricksStairsBlock extends StairBlock {
	public ShaleBricksStairsBlock() {
		super(Blocks.AIR.defaultBlockState(), BlockBehaviour.Properties.of().sound(SoundType.BASALT).strength(2f).requiresCorrectToolForDrops());
	}

	@Override
	public float getExplosionResistance() {
		return 2f;
	}
}