package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.SoundType;

public class ShaleCobbleWallBlock extends WallBlock {
	public ShaleCobbleWallBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.BASALT).strength(1.2f, 2f).requiresCorrectToolForDrops().forceSolidOn());
	}
}