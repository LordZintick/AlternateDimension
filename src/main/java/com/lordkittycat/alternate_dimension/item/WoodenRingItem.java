package com.lordkittycat.alternate_dimension.item;

import com.github.lordzintick.item.ring.RingItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;

public class WoodenRingItem extends RingItem {
	public WoodenRingItem(Item.Properties properties) {
		super(properties);
	}

	@Override
	protected int getPower() {
		return 1;
	}

	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return slotChanged && !oldStack.equals(newStack);
	}
}