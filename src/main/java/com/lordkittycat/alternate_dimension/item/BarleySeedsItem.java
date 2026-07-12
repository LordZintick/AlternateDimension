package com.lordkittycat.alternate_dimension.item;

import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.InteractionResult;

import com.lordkittycat.alternate_dimension.procedures.PlantBarleyPlantProcedure;

public class BarleySeedsItem extends Item {
	public BarleySeedsItem(Item.Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		super.useOn(context);
		PlantBarleyPlantProcedure.execute(context.getLevel(), context.getClickedPos().getX(), context.getClickedPos().getY(), context.getClickedPos().getZ(), context.getLevel().getBlockState(context.getClickedPos()), context.getClickedFace(),
				context.getItemInHand());
		return InteractionResult.SUCCESS;
	}

	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return slotChanged && !oldStack.equals(newStack);
	}
}