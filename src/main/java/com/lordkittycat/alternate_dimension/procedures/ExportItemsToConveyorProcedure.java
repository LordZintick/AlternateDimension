package com.lordkittycat.alternate_dimension.procedures;

import net.neoforged.neoforge.transfer.transaction.Transaction;
import net.neoforged.neoforge.transfer.item.ItemUtil;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.common.extensions.ILevelExtension;
import net.neoforged.neoforge.capabilities.Capabilities;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class ExportItemsToConveyorProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Direction side, double outputSlot) {
		if (side == null)
			return;
		if (world.isClientSide()) {
			return;
		}
		if (itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) outputSlot).getCount() > 0) {
			if (world instanceof ServerLevel _level) {
				ItemEntity entityToSpawn = new ItemEntity(_level, (x + side.getStepX() + 0.5), (y + side.getStepY() + 0.5), (z + side.getStepZ() + 0.5),
						(extractFromBlockInventory(world, BlockPos.containing(x, y, z), (int) outputSlot, itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) outputSlot).getCount(), false)));
				entityToSpawn.setPickUpDelay(10);
				entityToSpawn.setUnlimitedLifetime();
				_level.addFreshEntity(entityToSpawn);
			}
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("entity.item.pickup")), SoundSource.BLOCKS, 1, (float) 0.8);
				} else {
					_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("entity.item.pickup")), SoundSource.BLOCKS, 1, (float) 0.8, false);
				}
			}
		}
	}

	private static ItemStack itemFromBlockInventory(LevelAccessor world, BlockPos pos, int slot) {
		if (world instanceof ILevelExtension ext) {
			ResourceHandler<ItemResource> itemHandler = ext.getCapability(Capabilities.Item.BLOCK, pos, null);
			if (itemHandler != null)
				return ItemUtil.getStack(itemHandler, slot);
		}
		return ItemStack.EMPTY;
	}

	private static ItemStack extractFromBlockInventory(LevelAccessor world, BlockPos pos, int slotId, int amount, boolean simulate) {
		if (world instanceof ILevelExtension ext) {
			ResourceHandler<ItemResource> itemHandler = ext.getCapability(Capabilities.Item.BLOCK, pos, null);
			if (itemHandler != null && slotId >= 0 && slotId < itemHandler.size()) {
				ItemResource extractedResource = itemHandler.getResource(slotId);
				ItemStack extractedStack = ItemStack.EMPTY;
				if (extractedResource.isEmpty() || amount < 0)
					return extractedStack;
				try (var tx = Transaction.openRoot()) {
					int extracted = itemHandler.extract(slotId, extractedResource, amount, tx);
					extractedStack = extractedResource.toStack(extracted);
					if (!simulate)
						tx.commit();
				}
				return extractedStack;
			}
		}
		return ItemStack.EMPTY;
	}
}