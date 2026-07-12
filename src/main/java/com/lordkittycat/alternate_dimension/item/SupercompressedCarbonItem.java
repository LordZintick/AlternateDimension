package com.lordkittycat.alternate_dimension.item;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;

public class SupercompressedCarbonItem extends Item {
	public SupercompressedCarbonItem(Item.Properties properties) {
		super(properties.fireResistant());
	}

	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return slotChanged && !oldStack.equals(newStack);
	}
}