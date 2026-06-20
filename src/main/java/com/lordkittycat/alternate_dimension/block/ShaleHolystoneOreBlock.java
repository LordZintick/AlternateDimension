package com.lordkittycat.alternate_dimension.block;

import org.checkerframework.checker.units.qual.s;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;

public class ShaleHolystoneOreBlock extends Block {
	public ShaleHolystoneOreBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.BASALT).strength(3f).lightLevel(s -> 3).requiresCorrectToolForDrops());
	}
}