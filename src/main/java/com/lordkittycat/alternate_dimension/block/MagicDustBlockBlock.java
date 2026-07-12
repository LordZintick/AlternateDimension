package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.core.BlockPos;

import com.mojang.serialization.MapCodec;

public class MagicDustBlockBlock extends FallingBlock {
	public static final MapCodec<MagicDustBlockBlock> CODEC = simpleCodec(MagicDustBlockBlock::new);

	@Override
	public MapCodec<MagicDustBlockBlock> codec() {
		return CODEC;
	}

	@Override
	public int getDustColor(BlockState blockstate, BlockGetter world, BlockPos pos) {
		return blockstate.getMapColor(world, pos).col;
	}

	public MagicDustBlockBlock(BlockBehaviour.Properties properties) {
		super(properties.sound(SoundType.SAND).strength(1f, 10f));
	}
}