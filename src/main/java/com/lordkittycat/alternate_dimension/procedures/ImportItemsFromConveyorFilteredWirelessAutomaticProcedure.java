package com.lordkittycat.alternate_dimension.procedures;

import net.neoforged.neoforge.transfer.item.ItemUtil;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.common.extensions.ILevelExtension;
import net.neoforged.neoforge.capabilities.Capabilities;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import java.util.Comparator;
import java.util.ArrayList;

public class ImportItemsFromConveyorFilteredWirelessAutomaticProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, ArrayList filter, Direction side, boolean blacklist, double inX, double inY, double inZ) {
		if (filter == null || side == null)
			return;
		double slot = 0;
		if (world.isClientSide()) {
			return;
		}
		while (slot < getBlockInventorySlotCount(world, BlockPos.containing(x, y, z))) {
			if (itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).getCount() < getBlockInventorySlotStackLimit(world, BlockPos.containing(x, y, z), (int) slot)) {
				{
					final Vec3 _center = new Vec3((inX + side.getStepX() + 0.5), (inY + side.getStepY() + 0.5), (inZ + side.getStepZ() + 0.5));
					for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(1 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList()) {
						if (entityiterator instanceof ItemEntity && (!blacklist && filter.contains((BuiltInRegistries.ITEM.getKey((entityiterator instanceof ItemEntity _itemEnt ? _itemEnt.getItem() : ItemStack.EMPTY).getItem()).toString()))
								|| blacklist && !filter.contains((BuiltInRegistries.ITEM.getKey((entityiterator instanceof ItemEntity _itemEnt ? _itemEnt.getItem() : ItemStack.EMPTY).getItem()).toString())))) {
							if ((itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).copy()).getItem() == ItemStack.EMPTY.getItem()
									|| (itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).copy()).getItem() == (entityiterator instanceof ItemEntity _itemEnt ? _itemEnt.getItem() : ItemStack.EMPTY).getItem()) {
								insertInBlockInventory(world, BlockPos.containing(x, y, z), (int) slot, (entityiterator instanceof ItemEntity _itemEnt ? _itemEnt.getItem() : ItemStack.EMPTY).getCount(),
										(entityiterator instanceof ItemEntity _itemEnt ? _itemEnt.getItem() : ItemStack.EMPTY), false);
								if (world instanceof Level _level) {
									if (!_level.isClientSide()) {
										_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("entity.item.pickup")), SoundSource.BLOCKS, 1, 1);
									} else {
										_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("entity.item.pickup")), SoundSource.BLOCKS, 1, 1, false);
									}
								}
								if (!entityiterator.level().isClientSide())
									entityiterator.discard();
							}
						}
					}
				}
			}
			slot = slot + 1;
		}
	}

	private static int getBlockInventorySlotCount(LevelAccessor world, BlockPos pos) {
		if (world instanceof ILevelExtension ext) {
			ResourceHandler<ItemResource> itemHandler = ext.getCapability(Capabilities.Item.BLOCK, pos, null);
			if (itemHandler != null)
				return itemHandler.size();
		}
		return 0;
	}

	private static ItemStack itemFromBlockInventory(LevelAccessor world, BlockPos pos, int slot) {
		if (world instanceof ILevelExtension ext) {
			ResourceHandler<ItemResource> itemHandler = ext.getCapability(Capabilities.Item.BLOCK, pos, null);
			if (itemHandler != null)
				return ItemUtil.getStack(itemHandler, slot);
		}
		return ItemStack.EMPTY;
	}

	private static int getBlockInventorySlotStackLimit(LevelAccessor world, BlockPos pos, int slotId) {
		if (world instanceof ILevelExtension ext) {
			ResourceHandler<ItemResource> itemHandler = ext.getCapability(Capabilities.Item.BLOCK, pos, null);
			if (itemHandler != null && slotId >= 0 && slotId < itemHandler.size()) {
				return itemHandler.getCapacityAsInt(slotId, itemHandler.getResource(slotId));
			}
		}
		return 0;
	}

	private static int insertInBlockInventory(LevelAccessor world, BlockPos pos, int slotId, int amount, ItemStack itemstack, boolean simulate) {
		if (world instanceof ILevelExtension ext) {
			ResourceHandler<ItemResource> itemHandler = ext.getCapability(Capabilities.Item.BLOCK, pos, null);
			if (itemHandler != null && slotId >= 0 && slotId < itemHandler.size()) {
				ItemStack inserted = itemstack.copy();
				inserted.setCount(amount);
				return ItemUtil.insertItemReturnRemaining(itemHandler, slotId, inserted, simulate, null).getCount();
			}
		}
		return itemstack.getCount();
	}
}