package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;

import com.lordkittycat.alternate_dimension.procedures.ChillEntityProcedure;

public class IceCreamBlockBlock extends Block {
	public IceCreamBlockBlock(BlockBehaviour.Properties properties) {
		super(properties.sound(SoundType.SOUL_SOIL).strength(0.5f).friction(0.8f).speedFactor(1.1f));
	}

	@Override
	public void stepOn(Level world, BlockPos pos, BlockState blockstate, Entity entity) {
		super.stepOn(world, pos, blockstate, entity);
		ChillEntityProcedure.execute(entity);
	}
}