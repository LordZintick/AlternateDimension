package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.util.RandomSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;

import com.lordkittycat.alternate_dimension.procedures.SpawnElvesProcedure;

public class HeartOfNatureBlock extends Block {
	public HeartOfNatureBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.WET_GRASS).strength(4f, 100f).randomTicks());
	}

	@Override
	public void randomTick(BlockState blockstate, ServerLevel world, BlockPos pos, RandomSource random) {
		super.randomTick(blockstate, world, pos, random);
		SpawnElvesProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
	}

	@Override
	public boolean onDestroyedByPlayer(BlockState blockstate, Level world, BlockPos pos, Player entity, boolean willHarvest, FluidState fluid) {
		boolean retval = super.onDestroyedByPlayer(blockstate, world, pos, entity, willHarvest, fluid);
		SpawnElvesProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
		return retval;
	}
}