package com.lordkittycat.alternate_dimension.item;

import com.github.lordzintick.item.ring.RingItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;

public class MythrilRingItem extends RingItem {
	public MythrilRingItem(Item.Properties properties) {
		super(properties);
	}

	@Override
	protected int getPower() {
		return 4;
	}

	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return slotChanged && !oldStack.equals(newStack);
	}
}