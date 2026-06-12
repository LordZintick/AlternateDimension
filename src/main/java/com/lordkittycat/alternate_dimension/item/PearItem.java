package com.lordkittycat.alternate_dimension.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.food.FoodProperties;

public class PearItem extends Item {
	public PearItem() {
		super(new Item.Properties().food((new FoodProperties.Builder()).nutrition(3).saturationModifier(0.1f).build()));
	}
}