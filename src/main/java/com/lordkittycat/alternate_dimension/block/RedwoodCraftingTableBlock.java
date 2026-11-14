package com.lordkittycat.alternate_dimension.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import com.lordkittycat.alternate_dimension.procedures.OpenCraftingTableGUIProcedure;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class RedwoodCraftingTableBlock extends Block {
	public RedwoodCraftingTableBlock(BlockBehaviour.Properties properties) {
		super(properties.sound(SoundType.WOOD).strength(2f));
	}

	@Override
	protected InteractionResult useWithoutItem(BlockState state, Level world, BlockPos pos, Player entity, BlockHitResult hit) {
		return OpenCraftingTableGUIProcedure.execute(world, entity, pos, state.getBlock());
	}
}