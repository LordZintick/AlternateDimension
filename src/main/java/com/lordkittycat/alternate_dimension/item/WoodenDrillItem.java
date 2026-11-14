package com.lordkittycat.alternate_dimension.item;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.tags.TagKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.BlockPos;

import com.lordkittycat.alternate_dimension.procedures.PlayDrillSoundProcedure;

public class WoodenDrillItem extends Item {
	private static final ToolMaterial TOOL_MATERIAL = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 59, 4.5f, 0, 5, TagKey.create(Registries.ITEM, Identifier.parse("alternate_dimension:wooden_drill_repair_items")));

	public WoodenDrillItem(Item.Properties properties) {
		super(properties.pickaxe(TOOL_MATERIAL, 1f, -2.4f));
	}

	@Override
	public boolean mineBlock(ItemStack itemstack, Level world, BlockState blockstate, BlockPos pos, LivingEntity entity) {
		boolean retval = super.mineBlock(itemstack, world, blockstate, pos, entity);
		PlayDrillSoundProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
		return retval;
	}
}