package com.lordkittycat.alternate_dimension.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.entity.LivingEntity;

import com.lordkittycat.alternate_dimension.procedures.ApplyJumpBoostProcedure;

public class GreenGummyBearItem extends Item {
	public GreenGummyBearItem(Item.Properties properties) {
		super(properties.food((new FoodProperties.Builder()).nutrition(1).saturationModifier(0f).alwaysEdible().build(), Consumables.defaultFood().consumeSeconds(0.75F).build()));
	}

	@Override
	public ItemStack finishUsingItem(ItemStack itemstack, Level world, LivingEntity entity) {
		ItemStack retval = super.finishUsingItem(itemstack, world, entity);
		ApplyJumpBoostProcedure.execute(entity);
		return retval;
	}

	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return slotChanged && !oldStack.equals(newStack);
	}
}