package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.ARGB;
import net.minecraft.core.BlockPos;

import com.lordkittycat.alternate_dimension.procedures.NegateFallDamageProcedure;

public class CloudBlockBlock extends Block {
	public CloudBlockBlock(BlockBehaviour.Properties properties) {
		super(properties.sound(SoundType.WOOL).strength(0.1f, 0f).speedFactor(1.2f).jumpFactor(1.5f).pushReaction(PushReaction.DESTROY));
	}

	@Override
	public Integer getBeaconColorMultiplier(BlockState state, LevelReader world, BlockPos pos, BlockPos beaconPos) {
		return ARGB.opaque(-1);
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state) {
		return true;
	}

	@Override
	public int getLightDampening(BlockState state) {
		return 0;
	}

	@Override
	public void stepOn(Level world, BlockPos pos, BlockState blockstate, Entity entity) {
		super.stepOn(world, pos, blockstate, entity);
		NegateFallDamageProcedure.execute(entity);
	}
}