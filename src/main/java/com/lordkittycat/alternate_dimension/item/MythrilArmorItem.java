package com.lordkittycat.alternate_dimension.item;

import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.world.item.equipment.EquipmentAssets;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.tags.TagKey;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.Registries;

import java.util.Map;

public abstract class MythrilArmorItem extends Item {
	public static ArmorMaterial ARMOR_MATERIAL = new ArmorMaterial(42, Map.of(ArmorType.BOOTS, 3, ArmorType.LEGGINGS, 7, ArmorType.CHESTPLATE, 8, ArmorType.HELMET, 4, ArmorType.BODY, 8), 23,
			DeferredHolder.create(Registries.SOUND_EVENT, Identifier.parse("item.armor.equip_diamond")), 2f, 0f, TagKey.create(Registries.ITEM, Identifier.parse("alternate_dimension:mythril_armor_repair_items")),
			ResourceKey.create(EquipmentAssets.ROOT_ID, Identifier.parse("alternate_dimension:mythril_armor")));

	private MythrilArmorItem(Item.Properties properties) {
		super(properties);
	}

	public static class Helmet extends MythrilArmorItem {
		public Helmet(Item.Properties properties) {
			super(properties.humanoidArmor(ARMOR_MATERIAL, ArmorType.HELMET));
		}
	}

	public static class Chestplate extends MythrilArmorItem {
		public Chestplate(Item.Properties properties) {
			super(properties.humanoidArmor(ARMOR_MATERIAL, ArmorType.CHESTPLATE));
		}
	}

	public static class Leggings extends MythrilArmorItem {
		public Leggings(Item.Properties properties) {
			super(properties.humanoidArmor(ARMOR_MATERIAL, ArmorType.LEGGINGS));
		}
	}

	public static class Boots extends MythrilArmorItem {
		public Boots(Item.Properties properties) {
			super(properties.humanoidArmor(ARMOR_MATERIAL, ArmorType.BOOTS));
		}
	}
}