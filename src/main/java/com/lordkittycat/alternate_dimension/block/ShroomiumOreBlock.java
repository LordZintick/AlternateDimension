package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.entity.player.Player;
import net.minecraft.core.BlockPos;

import com.lordkittycat.alternate_dimension.procedures.RequiresMushyToolProcedure;

public class ShroomiumOreBlock extends Block {
	public ShroomiumOreBlock(BlockBehaviour.Properties properties) {
		super(properties.sound(SoundType.SHROOMLIGHT).strength(3f, 10f).requiresCorrectToolForDrops());
	}

	@Override
	public boolean canHarvestBlock(BlockState state, BlockGetter world, BlockPos pos, Player player) {
		return super.canHarvestBlock(state, world, pos, player) && RequiresMushyToolProcedure.execute(player);
	}
}