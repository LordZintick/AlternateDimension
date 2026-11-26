package com.lordkittycat.alternate_dimension.item;

import com.github.lordzintick.modular.RepairKitItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;

public class MagicRepairKitItem extends RepairKitItem {
	public MagicRepairKitItem(Item.Properties properties) {
		super(properties, 32, 1);
	}

	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return slotChanged && !oldStack.equals(newStack);
	}
}