package com.lordkittycat.alternate_dimension.item;

import com.github.lordzintick.item.ring.RingItem;
import net.minecraft.world.item.Item;

public class MythrilRingItem extends RingItem {
	public MythrilRingItem() {
		super(new Item.Properties());
	}

	@Override
	protected int getPower() {
		return 4;
	}
}