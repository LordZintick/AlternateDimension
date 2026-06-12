package com.lordkittycat.alternate_dimension.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.Entity;

import com.lordkittycat.alternate_dimension.procedures.BurnEntityProcedure;

public class UraniumGearItem extends Item {
	public UraniumGearItem() {
		super(new Item.Properties());
	}

	@Override
	public void inventoryTick(ItemStack itemstack, Level world, Entity entity, int slot, boolean selected) {
		super.inventoryTick(itemstack, world, entity, slot, selected);
		if (selected)
			BurnEntityProcedure.execute(entity);
	}
}