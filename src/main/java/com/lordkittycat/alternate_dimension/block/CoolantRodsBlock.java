package com.lordkittycat.alternate_dimension.block;

import org.checkerframework.checker.units.qual.s;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;

public class CoolantRodsBlock extends Block {
	public CoolantRodsBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(2f, 3f).lightLevel(s -> 5).requiresCorrectToolForDrops());
	}
}