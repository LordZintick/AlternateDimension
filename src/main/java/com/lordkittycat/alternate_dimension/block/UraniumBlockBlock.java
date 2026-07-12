package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;

import com.lordkittycat.alternate_dimension.procedures.BurnEntityProcedure;

public class UraniumBlockBlock extends Block {
	public UraniumBlockBlock(BlockBehaviour.Properties properties) {
		super(properties.sound(SoundType.METAL).strength(4f, 10f).lightLevel(blockstate -> 10).requiresCorrectToolForDrops().postProcess((bs, br, bp) -> bp).emissiveRendering((bs, br, bp) -> true));
	}

	@Override
	public void stepOn(Level world, BlockPos pos, BlockState blockstate, Entity entity) {
		super.stepOn(world, pos, blockstate, entity);
		BurnEntityProcedure.execute(entity);
	}
}