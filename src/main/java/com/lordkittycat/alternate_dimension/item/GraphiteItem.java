package com.lordkittycat.alternate_dimension.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class GraphiteItem extends Item {
	public GraphiteItem() {
		super(new Item.Properties().fireResistant().rarity(Rarity.UNCOMMON));
	}
}