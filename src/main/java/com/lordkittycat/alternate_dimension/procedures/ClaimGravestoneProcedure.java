package com.lordkittycat.alternate_dimension.procedures;

import net.neoforged.neoforge.transfer.transaction.Transaction;
import net.neoforged.neoforge.transfer.item.ItemUtil;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.common.extensions.ILevelExtension;
import net.neoforged.neoforge.capabilities.Capabilities;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.InteractionHand;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.Identifier;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

public class ClaimGravestoneProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double slot = 0;
		ItemStack item = ItemStack.EMPTY;
		if ((entity.getStringUUID()).equals(getBlockNBTString(world, BlockPos.containing(x, y, z), "owner"))) {
			if (entity.getCapability(Capabilities.Item.ENTITY, null) instanceof ResourceHandler<ItemResource> _resourceHandlerIter) {
				for (int _idx = 0; _idx < _resourceHandlerIter.size(); _idx++) {
					ItemStack itemstackiterator = ItemUtil.getStack(_resourceHandlerIter, _idx);
					if (!(itemstackiterator.getItem() == ItemStack.EMPTY.getItem())) {
						if (entity instanceof ServerPlayer _player)
							_player.sendSystemMessage(Component.literal("Inventory must be empty to claim gravestone."), true);
						return;
					}
				}
			}
			slot = 0;
			for (int _i1 = 0; _i1 < 36; _i1++) {
				item = (extractFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot, 99, false)).copy();
				if (entity.getCapability(Capabilities.Item.ENTITY, null) instanceof ResourceHandler<ItemResource> _resourceHandler) {
					setStackInSlot(_resourceHandler, (int) slot, ItemResource.of(item), item.getCount());
				}
				slot = slot + 1;
			}
			slot = 36;
			for (int _i1 = 0; _i1 < 4; _i1++) {
				item = (extractFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot, 99, false)).copy();
				if (entity instanceof LivingEntity _living) {
					_living.setItemSlot(new Object() {
						public static EquipmentSlot armorSlotByIndex(int _slotindex) {
							for (EquipmentSlot _slot : EquipmentSlot.values()) {
								if (_slot.getType() == EquipmentSlot.Type.HUMANOID_ARMOR && _slot.getIndex() == _slotindex) {
									return _slot;
								}
							}
							throw new IllegalArgumentException("Invalid slot index: " + _slotindex);
						}
					}.armorSlotByIndex((int) (slot - 36)), item);
				}
				slot = slot + 1;
			}
			slot = 40;
			item = (extractFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot, 99, false)).copy();
			if (entity instanceof LivingEntity _entity) {
				ItemStack _setstack14 = item.copy();
				_setstack14.setCount(item.getCount());
				_entity.setItemInHand(InteractionHand.OFF_HAND, _setstack14);
				if (_entity instanceof Player _player)
					_player.getInventory().setChanged();
			}
			if (entity instanceof ServerPlayer _player)
				_player.sendSystemMessage(Component.literal("Claimed gravestone."), true);
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("entity.item.pickup")), SoundSource.PLAYERS, 1, (float) 0.5);
				} else {
					_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("entity.item.pickup")), SoundSource.PLAYERS, 1, (float) 0.5, false);
				}
			}
			world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
		} else {
			if (entity instanceof ServerPlayer _player)
				_player.sendSystemMessage(Component.literal("This isn't your gravestone!"), true);
		}
	}

	private static String getBlockNBTString(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getStringOr(tag, "");
		return "";
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

	private static void setStackInSlot(ResourceHandler<ItemResource> handler, int index, ItemResource resource, int amount) {
		try (var tx = Transaction.openRoot()) {
			if (!handler.getResource(index).isEmpty())
				handler.extract(index, handler.getResource(index), handler.getAmountAsInt(index), tx);
			if (!resource.isEmpty() && amount > 0)
				handler.insert(index, resource, amount, tx);
			tx.commit();
		}
	}
}