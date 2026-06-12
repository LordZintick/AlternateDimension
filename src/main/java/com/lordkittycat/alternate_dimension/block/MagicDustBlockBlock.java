package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.FallingBlock;

import com.mojang.serialization.MapCodec;

public class MagicDustBlockBlock extends FallingBlock {
	public static final MapCodec<MagicDustBlockBlock> CODEC = simpleCodec(properties -> new MagicDustBlockBlock());

	public MapCodec<MagicDustBlockBlock> codec() {
		return CODEC;
	}

	public MagicDustBlockBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.SAND).strength(1f, 10f));
	}
}