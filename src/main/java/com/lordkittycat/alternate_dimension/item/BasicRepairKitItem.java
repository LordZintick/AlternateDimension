package com.lordkittycat.alternate_dimension.item;

import com.github.lordzintick.modular.RepairKitItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;

public class BasicRepairKitItem extends RepairKitItem {
	public BasicRepairKitItem(Item.Properties properties) {
		super(properties, 16, 0.33f);
	}

	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return slotChanged && !oldStack.equals(newStack);
	}
}