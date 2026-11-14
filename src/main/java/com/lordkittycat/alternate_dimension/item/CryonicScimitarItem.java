package com.lordkittycat.alternate_dimension.item;

import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.tags.TagKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.Registries;

import com.lordkittycat.alternate_dimension.procedures.ApplyCryonicScimitarEffectsProcedure;

public class CryonicScimitarItem extends Item {
	private static final ToolMaterial TOOL_MATERIAL = new ToolMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, 1250, 7f, 0, 2, TagKey.create(Registries.ITEM, Identifier.parse("alternate_dimension:cryonic_scimitar_repair_items")));

	public CryonicScimitarItem(Item.Properties properties) {
		super(properties.sword(TOOL_MATERIAL, 5f, -2.4f));
	}

	@Override
	public void hurtEnemy(ItemStack itemstack, LivingEntity entity, LivingEntity sourceentity) {
		super.hurtEnemy(itemstack, entity, sourceentity);
		ApplyCryonicScimitarEffectsProcedure.execute(entity.level(), entity.getX(), entity.getY(), entity.getZ(), entity);
	}
}