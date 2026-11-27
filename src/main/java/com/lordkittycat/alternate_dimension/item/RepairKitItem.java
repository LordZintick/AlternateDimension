package com.lordkittycat.alternate_dimension.item;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;

public class RepairKitItem extends com.github.lordzintick.modular.repair_kit.RepairKitItem {
	public RepairKitItem(Item.Properties properties) {
		super(properties, 24, 0.66f);
	}

	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return slotChanged && !oldStack.equals(newStack);
	}
}