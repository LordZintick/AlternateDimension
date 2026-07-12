package com.lordkittycat.alternate_dimension.item;

import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.food.FoodProperties;

public class ParmesanCheeseItem extends Item {
	public ParmesanCheeseItem(Item.Properties properties) {
		super(properties.rarity(Rarity.UNCOMMON).food((new FoodProperties.Builder()).nutrition(4).saturationModifier(0.3f).build(), Consumables.defaultFood().consumeSeconds(1F).build()));
	}

	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return slotChanged && !oldStack.equals(newStack);
	}
}