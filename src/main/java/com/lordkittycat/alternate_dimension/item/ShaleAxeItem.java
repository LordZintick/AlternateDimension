package com.lordkittycat.alternate_dimension.item;

import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.AxeItem;
import net.minecraft.tags.TagKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.Registries;

public class ShaleAxeItem extends AxeItem {
	private static final ToolMaterial TOOL_MATERIAL = new ToolMaterial(BlockTags.INCORRECT_FOR_STONE_TOOL, 141, 4f, 0, 7, TagKey.create(Registries.ITEM, Identifier.parse("alternate_dimension:shale_axe_repair_items")));

	public ShaleAxeItem(Item.Properties properties) {
		super(TOOL_MATERIAL, 7f, -3.2f, properties);
	}
}