package com.lordkittycat.alternate_dimension.item;

import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.HoeItem;
import net.minecraft.tags.TagKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.Registries;

public class TitaniumHoeItem extends HoeItem {
	private static final ToolMaterial TOOL_MATERIAL = new ToolMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, 2500, 9f, 0, 10, TagKey.create(Registries.ITEM, Identifier.parse("alternate_dimension:titanium_hoe_repair_items")));

	public TitaniumHoeItem(Item.Properties properties) {
		super(TOOL_MATERIAL, 0f, 0f, properties);
	}
}