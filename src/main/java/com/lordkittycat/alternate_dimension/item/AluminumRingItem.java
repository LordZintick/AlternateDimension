package com.lordkittycat.alternate_dimension.item;

import com.github.lordzintick.item.ring.RingItem;
import net.minecraft.world.item.Item;

public class AluminumRingItem extends RingItem {
	public AluminumRingItem() {
		super(new Item.Properties());
	}

	@Override
	protected int getPower() {
		return 2;
	}
}