package com.lordkittycat.alternate_dimension.item;

import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.tags.TagKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.Registries;

public class ScorchedWoodenPickaxeItem extends Item {
	private static final ToolMaterial TOOL_MATERIAL = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 60, 2f, 0, 15, TagKey.create(Registries.ITEM, Identifier.parse("alternate_dimension:scorched_wooden_pickaxe_repair_items")));

	public ScorchedWoodenPickaxeItem(Item.Properties properties) {
		super(properties.pickaxe(TOOL_MATERIAL, 1f, -2.8f));
	}
}