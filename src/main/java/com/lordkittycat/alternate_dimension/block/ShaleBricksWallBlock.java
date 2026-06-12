package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.SoundType;

public class ShaleBricksWallBlock extends WallBlock {
	public ShaleBricksWallBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.BASALT).strength(2f).requiresCorrectToolForDrops().forceSolidOn());
	}
}