package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;

public class CoolantRodsBlock extends Block {
	public CoolantRodsBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(2f, 3f).lightLevel(blockstate -> 5).requiresCorrectToolForDrops());
	}
}