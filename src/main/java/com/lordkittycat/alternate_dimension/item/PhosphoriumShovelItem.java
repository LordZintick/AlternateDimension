package com.lordkittycat.alternate_dimension.item;

import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.Item;
import net.minecraft.tags.TagKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.Registries;

public class PhosphoriumShovelItem extends ShovelItem {
	private static final ToolMaterial TOOL_MATERIAL = new ToolMaterial(BlockTags.INCORRECT_FOR_STONE_TOOL, 250, 7f, 0, 25, TagKey.create(Registries.ITEM, Identifier.parse("alternate_dimension:phosphorium_shovel_repair_items")));

	public PhosphoriumShovelItem(Item.Properties properties) {
		super(TOOL_MATERIAL, 1.25f, -3f, properties);
	}
}