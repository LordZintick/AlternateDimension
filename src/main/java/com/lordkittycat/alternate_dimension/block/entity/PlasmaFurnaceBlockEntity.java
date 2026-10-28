package com.lordkittycat.alternate_dimension.block.entity;

import com.github.lordzintick.block.entity.CustomFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.ContainerHelper;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.network.chat.Component;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.NonNullList;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import javax.annotation.Nullable;

import java.util.stream.IntStream;

import com.lordkittycat.alternate_dimension.init.AlternateDimensionModBlockEntities;

public class PlasmaFurnaceBlockEntity extends CustomFurnaceBlockEntity {
	public PlasmaFurnaceBlockEntity(BlockPos pos, BlockState state) {
		super(AlternateDimensionModBlockEntities.PLASMA_FURNACE.get(), pos, state);
	}
}