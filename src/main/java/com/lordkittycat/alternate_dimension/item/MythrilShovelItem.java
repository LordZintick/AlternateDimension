package com.lordkittycat.alternate_dimension.item;

import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.Item;
import net.minecraft.tags.TagKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.Registries;

public class MythrilShovelItem extends ShovelItem {
	private static final ToolMaterial TOOL_MATERIAL = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 2048, 10f, 0, 38, TagKey.create(Registries.ITEM, Identifier.parse("alternate_dimension:mythril_shovel_repair_items")));

	public MythrilShovelItem(Item.Properties properties) {
		super(TOOL_MATERIAL, 2.25f, -3f, properties.fireResistant());
	}
}