package com.lordkittycat.alternate_dimension.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.Entity;

import com.lordkittycat.alternate_dimension.procedures.SwiftnessRingProcedure;

public class WoodenRingOfSwiftnessItem extends Item {
	public WoodenRingOfSwiftnessItem() {
		super(new Item.Properties().stacksTo(1));
	}

	@Override
	public void inventoryTick(ItemStack itemstack, Level world, Entity entity, int slot, boolean selected) {
		super.inventoryTick(itemstack, world, entity, slot, selected);
		if (selected)
			SwiftnessRingProcedure.execute(entity, itemstack);
	}
}