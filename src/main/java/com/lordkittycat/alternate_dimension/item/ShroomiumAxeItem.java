package com.lordkittycat.alternate_dimension.item;

import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.AxeItem;
import net.minecraft.tags.TagKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.Registries;

public class ShroomiumAxeItem extends AxeItem {
	private static final ToolMaterial TOOL_MATERIAL = new ToolMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, 350, 8.5f, 0, 28, TagKey.create(Registries.ITEM, Identifier.parse("alternate_dimension:shroomium_axe_repair_items")));

	public ShroomiumAxeItem(Item.Properties properties) {
		super(TOOL_MATERIAL, 8f, -3.2f, properties);
	}
}