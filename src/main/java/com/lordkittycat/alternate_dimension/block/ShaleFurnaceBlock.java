package com.lordkittycat.alternate_dimension.block;

import com.github.lordzintick.block.CustomFurnaceBlock;
import com.lordkittycat.alternate_dimension.AlternateDimensionMod;
import com.lordkittycat.alternate_dimension.block.entity.VeridiumFurnaceBlockEntity;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.Containers;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import com.lordkittycat.alternate_dimension.block.entity.ShaleFurnaceBlockEntity;

public class ShaleFurnaceBlock extends CustomFurnaceBlock {
	public ShaleFurnaceBlock(Properties properties) {
		super(properties
				.setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(AlternateDimensionMod.MODID, "shale_furnace"))).sound(SoundType.STONE).strength(2f, 10f).requiresCorrectToolForDrops());
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new ShaleFurnaceBlockEntity(pos, state);
	}

	@Override
	protected MapCodec<? extends BaseEntityBlock> codec() {
		return simpleCodec(ShaleFurnaceBlock::new);
	}

	@Override
	public int getSmeltSpeed() {
		return 1;
	}
}