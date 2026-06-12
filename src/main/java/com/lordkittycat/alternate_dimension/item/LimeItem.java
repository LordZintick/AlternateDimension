package com.lordkittycat.alternate_dimension.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.food.FoodProperties;

public class LimeItem extends Item {
	public LimeItem() {
		super(new Item.Properties().food((new FoodProperties.Builder()).nutrition(4).saturationModifier(0.2f).build()));
	}
}