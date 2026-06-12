package com.lordkittycat.alternate_dimension.block;

import org.checkerframework.checker.units.qual.s;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.SlabBlock;

public class BlueMushroomSlabBlock extends SlabBlock {
	public BlueMushroomSlabBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.SHROOMLIGHT).strength(1f, 10f).lightLevel(s -> 12).hasPostProcess((bs, br, bp) -> true).emissiveRendering((bs, br, bp) -> true));
	}
}