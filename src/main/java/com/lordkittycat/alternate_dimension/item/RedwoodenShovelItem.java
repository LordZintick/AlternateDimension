package com.lordkittycat.alternate_dimension.item;

import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.Item;
import net.minecraft.tags.TagKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.Registries;

public class RedwoodenShovelItem extends ShovelItem {
	private static final ToolMaterial TOOL_MATERIAL = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 60, 2f, 0, 15, TagKey.create(Registries.ITEM, Identifier.parse("alternate_dimension:redwooden_shovel_repair_items")));

	public RedwoodenShovelItem(Item.Properties properties) {
		super(TOOL_MATERIAL, 0.25f, -3f, properties);
	}
}