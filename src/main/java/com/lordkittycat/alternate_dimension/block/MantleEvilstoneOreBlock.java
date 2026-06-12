package com.lordkittycat.alternate_dimension.block;

import org.checkerframework.checker.units.qual.s;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

public class MantleEvilstoneOreBlock extends Block {
	public MantleEvilstoneOreBlock() {
		super(BlockBehaviour.Properties.of().strength(3f).lightLevel(s -> 3).requiresCorrectToolForDrops());
	}
}