package com.lordkittycat.alternate_dimension.item;

import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.tags.TagKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.Registries;

public class ShroomiumSwordItem extends Item {
	private static final ToolMaterial TOOL_MATERIAL = new ToolMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, 350, 8.5f, 0, 28, TagKey.create(Registries.ITEM, Identifier.parse("alternate_dimension:shroomium_sword_repair_items")));

	public ShroomiumSwordItem(Item.Properties properties) {
		super(properties.sword(TOOL_MATERIAL, 5f, -2.4f));
	}
}