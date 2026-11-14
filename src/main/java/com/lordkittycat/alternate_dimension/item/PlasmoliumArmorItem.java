package com.lordkittycat.alternate_dimension.item;

import net.minecraft.world.item.equipment.EquipmentAssets;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.tags.TagKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.registries.BuiltInRegistries;

import java.util.Map;

import com.lordkittycat.alternate_dimension.AlternateDimensionMod;

public abstract class PlasmoliumArmorItem extends Item {
	public static ArmorMaterial ARMOR_MATERIAL = new ArmorMaterial(23, Map.of(ArmorType.BOOTS, 2, ArmorType.LEGGINGS, 5, ArmorType.CHESTPLATE, 7, ArmorType.HELMET, 3, ArmorType.BODY, 7), 25,
			BuiltInRegistries.SOUND_EVENT.wrapAsHolder(SoundEvents.EMPTY), 0f, 0f, TagKey.create(Registries.ITEM, Identifier.parse("alternate_dimension:plasmolium_armor_repair_items")),
			ResourceKey.create(EquipmentAssets.ROOT_ID, Identifier.parse("alternate_dimension:plasmolium_armor")));

	private PlasmoliumArmorItem(Item.Properties properties) {
		super(properties);
	}

	public static class Helmet extends PlasmoliumArmorItem {
		public Helmet(Item.Properties properties) {
			super(properties.humanoidArmor(ARMOR_MATERIAL, ArmorType.HELMET)
					.attributes(ItemAttributeModifiers.builder().add(Attributes.ARMOR, new AttributeModifier(Identifier.withDefaultNamespace("armor.helmet"), 3, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.HEAD)
							.add(Attributes.BURNING_TIME, new AttributeModifier(Identifier.fromNamespaceAndPath(AlternateDimensionMod.MODID, "plasmolium_armor_0.helmet"), -100, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.HEAD)
							.build()));
		}
	}

	public static class Chestplate extends PlasmoliumArmorItem {
		public Chestplate(Item.Properties properties) {
			super(properties.humanoidArmor(ARMOR_MATERIAL, ArmorType.CHESTPLATE)
					.attributes(ItemAttributeModifiers.builder().add(Attributes.ARMOR, new AttributeModifier(Identifier.withDefaultNamespace("armor.chestplate"), 7, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.CHEST)
							.add(Attributes.BURNING_TIME, new AttributeModifier(Identifier.fromNamespaceAndPath(AlternateDimensionMod.MODID, "plasmolium_armor_0.chestplate"), -100, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.CHEST)
							.build()));
		}
	}

	public static class Leggings extends PlasmoliumArmorItem {
		public Leggings(Item.Properties properties) {
			super(properties.humanoidArmor(ARMOR_MATERIAL, ArmorType.LEGGINGS)
					.attributes(ItemAttributeModifiers.builder().add(Attributes.ARMOR, new AttributeModifier(Identifier.withDefaultNamespace("armor.leggings"), 5, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.LEGS)
							.add(Attributes.BURNING_TIME, new AttributeModifier(Identifier.fromNamespaceAndPath(AlternateDimensionMod.MODID, "plasmolium_armor_0.leggings"), -100, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.LEGS)
							.build()));
		}
	}

	public static class Boots extends PlasmoliumArmorItem {
		public Boots(Item.Properties properties) {
			super(properties.humanoidArmor(ARMOR_MATERIAL, ArmorType.BOOTS)
					.attributes(ItemAttributeModifiers.builder().add(Attributes.ARMOR, new AttributeModifier(Identifier.withDefaultNamespace("armor.boots"), 2, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.FEET)
							.add(Attributes.BURNING_TIME, new AttributeModifier(Identifier.fromNamespaceAndPath(AlternateDimensionMod.MODID, "plasmolium_armor_0.boots"), -100, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.FEET)
							.build()));
		}
	}
}