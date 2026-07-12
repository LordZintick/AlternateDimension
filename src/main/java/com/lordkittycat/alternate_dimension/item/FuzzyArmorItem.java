package com.lordkittycat.alternate_dimension.item;

import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.world.item.equipment.EquipmentAssets;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.TagKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;

import javax.annotation.Nullable;

import java.util.function.Consumer;
import java.util.Map;

import com.lordkittycat.alternate_dimension.procedures.ChargeBuildupProcedure;

public abstract class FuzzyArmorItem extends Item {
	public static ArmorMaterial ARMOR_MATERIAL = new ArmorMaterial(3, Map.of(ArmorType.BOOTS, 0, ArmorType.LEGGINGS, 5, ArmorType.CHESTPLATE, 6, ArmorType.HELMET, 2, ArmorType.BODY, 6), 7,
			DeferredHolder.create(Registries.SOUND_EVENT, Identifier.parse("block.wool.place")), 0f, 0f, TagKey.create(Registries.ITEM, Identifier.parse("alternate_dimension:fuzzy_armor_repair_items")),
			ResourceKey.create(EquipmentAssets.ROOT_ID, Identifier.parse("alternate_dimension:fuzzy_armor")));

	private FuzzyArmorItem(Item.Properties properties) {
		super(properties);
	}

	public static class Boots extends FuzzyArmorItem {
		public Boots(Item.Properties properties) {
			super(properties.humanoidArmor(ARMOR_MATERIAL, ArmorType.BOOTS));
		}

		@Override
		public void appendHoverText(ItemStack itemstack, Item.TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> componentConsumer, TooltipFlag flag) {
			super.appendHoverText(itemstack, context, tooltipDisplay, componentConsumer, flag);
			componentConsumer.accept(Component.translatable("item.alternate_dimension.fuzzy_armor_boots.description_0"));
		}

		@Override
		public void inventoryTick(ItemStack itemstack, ServerLevel world, Entity entity, @Nullable EquipmentSlot equipmentSlot) {
			super.inventoryTick(itemstack, world, entity, equipmentSlot);
			if (entity instanceof Player player && (equipmentSlot != null && equipmentSlot.getType() == EquipmentSlot.Type.HUMANOID_ARMOR)) {
				ChargeBuildupProcedure.execute(world, entity.getX(), entity.getY(), entity.getZ(), entity, itemstack);
			}
		}
	}
}