package com.lordkittycat.alternate_dimension.item;

import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.tags.TagKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.Registries;

public class ShroomiumPickaxeItem extends Item {
	private static final ToolMaterial TOOL_MATERIAL = new ToolMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, 350, 7f, 0, 28, TagKey.create(Registries.ITEM, Identifier.parse("alternate_dimension:shroomium_pickaxe_repair_items")));

	public ShroomiumPickaxeItem(Item.Properties properties) {
		super(properties.pickaxe(TOOL_MATERIAL, 3f, -2.8f));
	}
}