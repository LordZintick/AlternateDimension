package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.SoundType;

public class ShaleBricksWallBlock extends WallBlock {
	public ShaleBricksWallBlock(BlockBehaviour.Properties properties) {
		super(properties.sound(SoundType.BASALT).strength(2f).requiresCorrectToolForDrops().forceSolidOn());
	}
}