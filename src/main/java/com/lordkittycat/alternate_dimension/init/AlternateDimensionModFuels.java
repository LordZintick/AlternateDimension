/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package com.lordkittycat.alternate_dimension.init;

import net.neoforged.neoforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.item.ItemStack;

@EventBusSubscriber
public class AlternateDimensionModFuels {
	@SubscribeEvent
	public static void furnaceFuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event) {
		ItemStack itemstack = event.getItemStack();
		if (itemstack.getItem() == AlternateDimensionModItems.URANIUM_INGOT.get())
			event.setBurnTime(800);
		else if (itemstack.getItem() == AlternateDimensionModItems.RAW_URANIUM.get())
			event.setBurnTime(100);
		else if (itemstack.getItem() == AlternateDimensionModItems.PLUTONIUM.get())
			event.setBurnTime(400);
	}
}