package com.lordkittycat.alternate_dimension.item;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;

public class PhosphorusItem extends Item {
	public PhosphorusItem(Item.Properties properties) {
		super(properties);
	}

	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return slotChanged && !oldStack.equals(newStack);
	}
}