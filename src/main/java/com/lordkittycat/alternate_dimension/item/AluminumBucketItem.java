package com.lordkittycat.alternate_dimension.item;

import com.github.lordzintick.item.EmptyBucketItem;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModItems;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;

public class AluminumBucketItem extends EmptyBucketItem {
	public AluminumBucketItem() {
		super(new Item.Properties().stacksTo(16), AlternateDimensionModItems.ALUMINUM_WATER_BUCKET, AlternateDimensionModItems.ALUMINUM_LAVA_BUCKET);
	}
}