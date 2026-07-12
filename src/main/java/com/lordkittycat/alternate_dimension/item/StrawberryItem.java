package com.lordkittycat.alternate_dimension.item;

import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.food.FoodProperties;

public class StrawberryItem extends Item {
	public StrawberryItem(Item.Properties properties) {
		super(properties.food((new FoodProperties.Builder()).nutrition(2).saturationModifier(0f).build(), Consumables.defaultFood().consumeSeconds(1.25F).build()));
	}

	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return slotChanged && !oldStack.equals(newStack);
	}
}