package com.lordkittycat.alternate_dimension.item;

import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.Item;
import net.minecraft.tags.TagKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.Registries;

public class TitaniumShovelItem extends ShovelItem {
	private static final ToolMaterial TOOL_MATERIAL = new ToolMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, 2500, 9f, 0, 10, TagKey.create(Registries.ITEM, Identifier.parse("alternate_dimension:titanium_shovel_repair_items")));

	public TitaniumShovelItem(Item.Properties properties) {
		super(TOOL_MATERIAL, 2.25f, -3f, properties);
	}
}