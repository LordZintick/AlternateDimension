package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.FallingBlock;

import com.mojang.serialization.MapCodec;

public class SugarBlockBlock extends FallingBlock {
	public static final MapCodec<SugarBlockBlock> CODEC = simpleCodec(properties -> new SugarBlockBlock());

	public MapCodec<SugarBlockBlock> codec() {
		return CODEC;
	}

	public SugarBlockBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.DRIPSTONE_BLOCK).strength(1f, 2f).requiresCorrectToolForDrops());
	}
}