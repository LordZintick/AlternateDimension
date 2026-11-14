package com.lordkittycat.alternate_dimension.item;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.food.FoodProperties;

public class PastaItem extends Item {
	public PastaItem(Item.Properties properties) {
		super(properties.stacksTo(32).food((new FoodProperties.Builder()).nutrition(8).saturationModifier(0.5f).build()));
	}

	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return slotChanged && !oldStack.equals(newStack);
	}
}