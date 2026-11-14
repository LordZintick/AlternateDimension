package com.lordkittycat.alternate_dimension.item;

import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.tags.TagKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.Registries;

public class MythrilSwordItem extends Item {
	private static final ToolMaterial TOOL_MATERIAL = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 2048, 10f, 0, 38, TagKey.create(Registries.ITEM, Identifier.parse("alternate_dimension:mythril_sword_repair_items")));

	public MythrilSwordItem(Item.Properties properties) {
		super(properties.sword(TOOL_MATERIAL, 7.5f, -2.4f).fireResistant());
	}
}