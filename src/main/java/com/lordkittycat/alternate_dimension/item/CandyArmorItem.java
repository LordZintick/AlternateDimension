package com.lordkittycat.alternate_dimension.item;

import net.minecraft.world.item.equipment.EquipmentAssets;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.tags.TagKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.registries.BuiltInRegistries;

import java.util.function.Consumer;
import java.util.Map;

public abstract class CandyArmorItem extends Item {
	public static ArmorMaterial ARMOR_MATERIAL = new ArmorMaterial(25, Map.of(ArmorType.BOOTS, 0, ArmorType.LEGGINGS, 0, ArmorType.CHESTPLATE, 6, ArmorType.HELMET, 0, ArmorType.BODY, 6), 1,
			BuiltInRegistries.SOUND_EVENT.wrapAsHolder(SoundEvents.EMPTY), 0f, 0f, TagKey.create(Registries.ITEM, Identifier.parse("alternate_dimension:candy_armor_repair_items")),
			ResourceKey.create(EquipmentAssets.ROOT_ID, Identifier.parse("alternate_dimension:candy_armor")));

	private CandyArmorItem(Item.Properties properties) {
		super(properties);
	}

	public static class Chestplate extends CandyArmorItem {
		public Chestplate(Item.Properties properties) {
			super(properties.humanoidArmor(ARMOR_MATERIAL, ArmorType.CHESTPLATE));
		}

		@Override
		public void appendHoverText(ItemStack itemstack, Item.TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> componentConsumer, TooltipFlag flag) {
			super.appendHoverText(itemstack, context, tooltipDisplay, componentConsumer, flag);
			componentConsumer.accept(Component.translatable("item.alternate_dimension.candy_armor_chestplate.description_0"));
			componentConsumer.accept(Component.translatable("item.alternate_dimension.candy_armor_chestplate.description_1"));
		}
	}
}