package com.lordkittycat.alternate_dimension.block;

import org.checkerframework.checker.units.qual.s;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

public class MantleHolystoneOreBlock extends Block {
	public MantleHolystoneOreBlock() {
		super(BlockBehaviour.Properties.of().strength(3f).lightLevel(s -> 3).requiresCorrectToolForDrops());
	}
}