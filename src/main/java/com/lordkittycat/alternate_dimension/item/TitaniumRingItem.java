package com.lordkittycat.alternate_dimension.item;

import com.github.lordzintick.item.ring.RingItem;
import net.minecraft.world.item.Item;

public class TitaniumRingItem extends RingItem {
	public TitaniumRingItem() {
		super(new Item.Properties());
	}

	@Override
	protected int getPower() {
		return 3;
	}
}