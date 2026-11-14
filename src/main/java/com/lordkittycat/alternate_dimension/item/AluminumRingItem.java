package com.lordkittycat.alternate_dimension.item;

import com.github.lordzintick.item.ring.RingItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;

public class AluminumRingItem extends RingItem {
	public AluminumRingItem(Item.Properties properties) {
		super(properties);
	}

	@Override
	protected int getPower() {
		return 2;
	}

	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return slotChanged && !oldStack.equals(newStack);
	}
}