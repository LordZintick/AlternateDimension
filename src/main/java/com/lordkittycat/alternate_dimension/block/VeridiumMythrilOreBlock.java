package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.entity.player.Player;
import net.minecraft.core.BlockPos;

import com.lordkittycat.alternate_dimension.procedures.CheckForTitaniumToolProcedure;

public class VeridiumMythrilOreBlock extends Block {
	public VeridiumMythrilOreBlock() {
		super(BlockBehaviour.Properties.of().strength(5f, 15f).requiresCorrectToolForDrops());
	}

	@Override
	public boolean canHarvestBlock(BlockState state, BlockGetter world, BlockPos pos, Player player) {
		return super.canHarvestBlock(state, world, pos, player) && CheckForTitaniumToolProcedure.execute(player);
	}
}