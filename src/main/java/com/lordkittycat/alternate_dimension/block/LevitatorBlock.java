package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;

import com.lordkittycat.alternate_dimension.procedures.ApplyLevitationProcedure;

public class LevitatorBlock extends Block {
	public LevitatorBlock(BlockBehaviour.Properties properties) {
		super(properties.sound(SoundType.METAL).strength(3f, 10f).requiresCorrectToolForDrops());
	}

	@Override
	public void stepOn(Level world, BlockPos pos, BlockState blockstate, Entity entity) {
		super.stepOn(world, pos, blockstate, entity);
		ApplyLevitationProcedure.execute(entity);
	}
}