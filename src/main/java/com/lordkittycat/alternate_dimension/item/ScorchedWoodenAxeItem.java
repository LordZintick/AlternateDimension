package com.lordkittycat.alternate_dimension.item;

import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.AxeItem;
import net.minecraft.tags.TagKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.Registries;

public class ScorchedWoodenAxeItem extends AxeItem {
	private static final ToolMaterial TOOL_MATERIAL = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 60, 2f, 0, 15, TagKey.create(Registries.ITEM, Identifier.parse("alternate_dimension:scorched_wooden_axe_repair_items")));

	public ScorchedWoodenAxeItem(Item.Properties properties) {
		super(TOOL_MATERIAL, 6f, -3.2f, properties);
	}
}