package com.lordkittycat.alternate_dimension.item;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BucketItem;

import com.lordkittycat.alternate_dimension.init.AlternateDimensionModFluids;

public class LiquidPlasmaItem extends BucketItem {
	public LiquidPlasmaItem(Item.Properties properties) {
		super(AlternateDimensionModFluids.LIQUID_PLASMA.get(), properties.craftRemainder(Items.BUCKET).stacksTo(1)

		);
	}
}