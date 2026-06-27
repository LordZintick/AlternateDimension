package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.item.ItemStack;

public class CancelItemstackConsumptionProcedure {
	public static void execute(ItemStack itemstack) {
		itemstack.grow(1);
	}
}