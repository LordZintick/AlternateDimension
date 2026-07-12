package com.lordkittycat.alternate_dimension.item;

import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.tags.TagKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.Registries;

import com.lordkittycat.alternate_dimension.AlternateDimensionMod;

public class RomanSpearItem extends Item {
	private static final ToolMaterial TOOL_MATERIAL = new ToolMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, 355, 6f, 0, 10, TagKey.create(Registries.ITEM, Identifier.parse("alternate_dimension:roman_spear_repair_items")));

	public RomanSpearItem(Item.Properties properties) {
		super(properties.sword(TOOL_MATERIAL, 4.5f, -2.6f)
				.attributes(ItemAttributeModifiers.builder().add(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_ID, 4.5, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
						.add(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_ID, -2.6, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
						.add(Attributes.ENTITY_INTERACTION_RANGE, new AttributeModifier(Identifier.fromNamespaceAndPath(AlternateDimensionMod.MODID, "roman_spear_0"), 2, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.ANY)
						.add(Attributes.BLOCK_INTERACTION_RANGE, new AttributeModifier(Identifier.fromNamespaceAndPath(AlternateDimensionMod.MODID, "roman_spear_1"), 2, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.ANY).build()));
	}
}