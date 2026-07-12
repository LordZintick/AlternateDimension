package com.lordkittycat.alternate_dimension.item;

import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.AxeItem;
import net.minecraft.tags.TagKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.Registries;

public class TitaniumBattleaxeItem extends AxeItem {
	private static final ToolMaterial TOOL_MATERIAL = new ToolMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, 2500, 9f, 0, 10, TagKey.create(Registries.ITEM, Identifier.parse("alternate_dimension:titanium_battleaxe_repair_items")));

	public TitaniumBattleaxeItem(Item.Properties properties) {
		super(TOOL_MATERIAL, 13f, -3.4f, properties);
	}
}