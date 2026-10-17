package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.InteractionHand;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.registries.BuiltInRegistries;

public class ConfigureRandomEquipmentProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (!world.isClientSide() && world.getServer() != null) {
			for (ItemStack itemstackiterator : world.getServer().reloadableRegistries()
					.getLootTable(ResourceKey.create(Registries.LOOT_TABLE,
							Identifier.parse((("alternate_dimension:entity/" + (BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType()).toString()).replace("alternate_dimension:", "") + "_head_armor")).toLowerCase(java.util.Locale.ENGLISH))))
					.getRandomItems(new LootParams.Builder((ServerLevel) world).create(LootContextParamSets.EMPTY))) {
				if (entity instanceof LivingEntity _living) {
					_living.setItemSlot(EquipmentSlot.HEAD, itemstackiterator);
				}
			}
		}
		if (!world.isClientSide() && world.getServer() != null) {
			for (ItemStack itemstackiterator : world.getServer().reloadableRegistries()
					.getLootTable(ResourceKey.create(Registries.LOOT_TABLE,
							Identifier.parse((("alternate_dimension:entity/" + (BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType()).toString()).replace("alternate_dimension:", "") + "_body_armor")).toLowerCase(java.util.Locale.ENGLISH))))
					.getRandomItems(new LootParams.Builder((ServerLevel) world).create(LootContextParamSets.EMPTY))) {
				if (entity instanceof LivingEntity _living) {
					_living.setItemSlot(EquipmentSlot.CHEST, itemstackiterator);
				}
			}
		}
		if (!world.isClientSide() && world.getServer() != null) {
			for (ItemStack itemstackiterator : world.getServer().reloadableRegistries()
					.getLootTable(ResourceKey.create(Registries.LOOT_TABLE,
							Identifier.parse((("alternate_dimension:entity/" + (BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType()).toString()).replace("alternate_dimension:", "") + "_leg_armor")).toLowerCase(java.util.Locale.ENGLISH))))
					.getRandomItems(new LootParams.Builder((ServerLevel) world).create(LootContextParamSets.EMPTY))) {
				if (entity instanceof LivingEntity _living) {
					_living.setItemSlot(EquipmentSlot.LEGS, itemstackiterator);
				}
			}
		}
		if (!world.isClientSide() && world.getServer() != null) {
			for (ItemStack itemstackiterator : world.getServer().reloadableRegistries()
					.getLootTable(ResourceKey.create(Registries.LOOT_TABLE,
							Identifier.parse((("alternate_dimension:entity/" + (BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType()).toString()).replace("alternate_dimension:", "") + "_feet_armor")).toLowerCase(java.util.Locale.ENGLISH))))
					.getRandomItems(new LootParams.Builder((ServerLevel) world).create(LootContextParamSets.EMPTY))) {
				if (entity instanceof LivingEntity _living) {
					_living.setItemSlot(EquipmentSlot.FEET, itemstackiterator);
				}
			}
		}
		if (!world.isClientSide() && world.getServer() != null) {
			for (ItemStack itemstackiterator : world.getServer().reloadableRegistries()
					.getLootTable(ResourceKey.create(Registries.LOOT_TABLE,
							Identifier.parse((("alternate_dimension:entity/" + (BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType()).toString()).replace("alternate_dimension:", "") + "_weapon")).toLowerCase(java.util.Locale.ENGLISH))))
					.getRandomItems(new LootParams.Builder((ServerLevel) world).create(LootContextParamSets.EMPTY))) {
				if (entity instanceof LivingEntity _entity) {
					ItemStack _setstack18 = itemstackiterator.copy();
					_setstack18.setCount(1);
					_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack18);
					if (_entity instanceof Player _player)
						_player.getInventory().setChanged();
				}
			}
		}
		if (!world.isClientSide() && world.getServer() != null) {
			for (ItemStack itemstackiterator : world.getServer().reloadableRegistries()
					.getLootTable(ResourceKey.create(Registries.LOOT_TABLE,
							Identifier.parse((("alternate_dimension:entity/" + (BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType()).toString()).replace("alternate_dimension:", "") + "_offhand")).toLowerCase(java.util.Locale.ENGLISH))))
					.getRandomItems(new LootParams.Builder((ServerLevel) world).create(LootContextParamSets.EMPTY))) {
				if (entity instanceof LivingEntity _entity) {
					ItemStack _setstack22 = itemstackiterator.copy();
					_setstack22.setCount(1);
					_entity.setItemInHand(InteractionHand.OFF_HAND, _setstack22);
					if (_entity instanceof Player _player)
						_player.getInventory().setChanged();
				}
			}
		}
	}
}