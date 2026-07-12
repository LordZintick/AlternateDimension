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

public class DurableDrillItem extends Item {
	private static final ToolMaterial TOOL_MATERIAL = new ToolMaterial(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 10000, 15f, 0, 9, TagKey.create(Registries.ITEM, Identifier.parse("alternate_dimension:durable_drill_repair_items")));

	public DurableDrillItem(Item.Properties properties) {
		super(properties.pickaxe(TOOL_MATERIAL, 5.5f, -2.6f).fireResistant());
	}

	@Override
	public boolean mineBlock(ItemStack itemstack, Level world, BlockState blockstate, BlockPos pos, LivingEntity entity) {
		boolean retval = super.mineBlock(itemstack, world, blockstate, pos, entity);
		PlayDrillSoundProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
		return retval;
	}
}