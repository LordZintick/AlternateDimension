package com.lordkittycat.alternate_dimension.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.food.FoodProperties;

public class GoldenBeefItem extends Item {
	public GoldenBeefItem() {
		super(new Item.Properties().food((new FoodProperties.Builder()).nutrition(6).saturationModifier(3.2f).build()));
	}
}