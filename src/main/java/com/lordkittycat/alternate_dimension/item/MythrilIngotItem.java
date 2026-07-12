package com.lordkittycat.alternate_dimension.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;

public class MythrilIngotItem extends Item {
	public MythrilIngotItem(Item.Properties properties) {
		super(properties.rarity(Rarity.UNCOMMON));
	}

	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return slotChanged && !oldStack.equals(newStack);
	}
}