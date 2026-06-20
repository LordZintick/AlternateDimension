package com.lordkittycat.alternate_dimension.item;

import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;

import com.lordkittycat.alternate_dimension.init.AlternateDimensionModItems;
import net.minecraft.world.level.material.Fluids;

public class AluminumWaterBucketItem extends BucketItem {
	public AluminumWaterBucketItem() {
		super(Fluids.LAVA, new Item.Properties().stacksTo(1));
	}

	@Override
	public boolean hasCraftingRemainingItem(ItemStack stack) {
		return true;
	}

	@Override
	public ItemStack getCraftingRemainingItem(ItemStack itemstack) {
		return new ItemStack(AlternateDimensionModItems.ALUMINUM_BUCKET.get());
	}
}