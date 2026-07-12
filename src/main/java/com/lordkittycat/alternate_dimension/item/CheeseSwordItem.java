package com.lordkittycat.alternate_dimension.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.tags.TagKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.Registries;

import com.lordkittycat.alternate_dimension.procedures.EatItemProcedure;

public class CheeseSwordItem extends Item {
	private static final ToolMaterial TOOL_MATERIAL = new ToolMaterial(BlockTags.INCORRECT_FOR_STONE_TOOL, 500, 5f, 0, 20, TagKey.create(Registries.ITEM, Identifier.parse("alternate_dimension:cheese_sword_repair_items")));

	public CheeseSwordItem(Item.Properties properties) {
		super(properties.sword(TOOL_MATERIAL, 2f, -2.3f));
	}

	@Override
	public InteractionResult use(Level world, Player entity, InteractionHand hand) {
		InteractionResult ar = super.use(world, entity, hand);
		EatItemProcedure.execute(world, entity.getX(), entity.getY(), entity.getZ(), entity, entity.getItemInHand(hand));
		return ar;
	}
}