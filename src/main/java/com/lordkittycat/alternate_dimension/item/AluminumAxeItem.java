package com.lordkittycat.alternate_dimension.item;

import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.AxeItem;
import net.minecraft.tags.TagKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.Registries;

public class AluminumAxeItem extends AxeItem {
	private static final ToolMaterial TOOL_MATERIAL = new ToolMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, 216, 6f, 0, 13, TagKey.create(Registries.ITEM, Identifier.parse("alternate_dimension:aluminum_axe_repair_items")));

	public AluminumAxeItem(Item.Properties properties) {
		super(TOOL_MATERIAL, 7f, -3f, properties);
	}
}