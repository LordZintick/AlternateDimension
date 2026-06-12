package com.lordkittycat.alternate_dimension.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.food.FoodProperties;

public class BreadstickItem extends Item {
	public BreadstickItem() {
		super(new Item.Properties().food((new FoodProperties.Builder()).nutrition(5).saturationModifier(0.3f).build()));
	}
}