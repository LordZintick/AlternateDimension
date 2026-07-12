package com.lordkittycat.alternate_dimension.item;

import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.tags.TagKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.Registries;

public class VeridiumSwordItem extends Item {
	private static final ToolMaterial TOOL_MATERIAL = new ToolMaterial(BlockTags.INCORRECT_FOR_STONE_TOOL, 141, 4f, 0, 7, TagKey.create(Registries.ITEM, Identifier.parse("alternate_dimension:veridium_sword_repair_items")));

	public VeridiumSwordItem(Item.Properties properties) {
		super(properties.sword(TOOL_MATERIAL, 4f, -2.4f));
	}
}