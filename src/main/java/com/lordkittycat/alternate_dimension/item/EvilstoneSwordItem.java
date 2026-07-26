package com.lordkittycat.alternate_dimension.item;

import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.tags.TagKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.Registries;

import com.lordkittycat.alternate_dimension.procedures.BonusDamageToHolyMobsProcedure;

public class EvilstoneSwordItem extends Item {
	private static final ToolMaterial TOOL_MATERIAL = new ToolMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, 250, 6f, 0, 18, TagKey.create(Registries.ITEM, Identifier.parse("alternate_dimension:evilstone_sword_repair_items")));

	public EvilstoneSwordItem(Item.Properties properties) {
		super(properties.sword(TOOL_MATERIAL, 5f, -2.4f));
	}

	@Override
	public void hurtEnemy(ItemStack itemstack, LivingEntity entity, LivingEntity sourceentity) {
		super.hurtEnemy(itemstack, entity, sourceentity);
		BonusDamageToHolyMobsProcedure.execute(entity.level(), entity);
	}
}