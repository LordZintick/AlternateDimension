package com.lordkittycat.alternate_dimension.item;

import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.tags.TagKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.Registries;

import com.lordkittycat.alternate_dimension.procedures.BonusDamageToHolyMobsProcedure;

public class DarksteelHoeItem extends HoeItem {
	private static final ToolMaterial TOOL_MATERIAL = new ToolMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, 350, 7.5f, 0, 18, TagKey.create(Registries.ITEM, Identifier.parse("alternate_dimension:darksteel_hoe_repair_items")));

	public DarksteelHoeItem(Item.Properties properties) {
		super(TOOL_MATERIAL, 0f, -1f, properties);
	}

	@Override
	public void hurtEnemy(ItemStack itemstack, LivingEntity entity, LivingEntity sourceentity) {
		super.hurtEnemy(itemstack, entity, sourceentity);
		BonusDamageToHolyMobsProcedure.execute(entity.level(), entity);
	}
}