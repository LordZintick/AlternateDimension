package com.lordkittycat.alternate_dimension.block;

import com.github.lordzintick.block.CustomFurnaceBlock;
import com.lordkittycat.alternate_dimension.block.entity.ShaleFurnaceBlockEntity;
import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.Containers;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import com.lordkittycat.alternate_dimension.block.entity.MantleFurnaceBlockEntity;

public class MantleFurnaceBlock extends CustomFurnaceBlock {
	public MantleFurnaceBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(2f, 10f).requiresCorrectToolForDrops());
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new MantleFurnaceBlockEntity(pos, state);
	}

	@Override
	protected MapCodec<? extends BaseEntityBlock> codec() {
		return simpleCodec((p) -> new MantleFurnaceBlock());
	}

	@Override
	public int getSmeltSpeed() {
		return 2;
	}
}