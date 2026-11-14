package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.SoundType;

public class ShaleWallBlock extends WallBlock {
	public ShaleWallBlock(BlockBehaviour.Properties properties) {
		super(properties.sound(SoundType.BASALT).strength(1f).requiresCorrectToolForDrops().forceSolidOn());
	}
}