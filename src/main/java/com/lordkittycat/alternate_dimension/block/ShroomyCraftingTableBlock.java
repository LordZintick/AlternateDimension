package com.lordkittycat.alternate_dimension.block;

import com.lordkittycat.alternate_dimension.procedures.OpenCraftingTableGUIProcedure;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class ShroomyCraftingTableBlock extends Block {
	public ShroomyCraftingTableBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.SHROOMLIGHT).strength(1f));
	}

	@Override
	protected InteractionResult useWithoutItem(BlockState state, Level world, BlockPos pos, Player entity, BlockHitResult hit) {
		return OpenCraftingTableGUIProcedure.execute(world, entity, pos, state.getBlock());
	}
}