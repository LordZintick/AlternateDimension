package com.lordkittycat.alternate_dimension.item.inventory;

import net.neoforged.neoforge.items.ComponentItemHandler;
import net.neoforged.neoforge.event.entity.item.ItemTossEvent;
import net.neoforged.neoforge.common.MutableDataComponentHolder;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.core.component.DataComponents;

import javax.annotation.Nonnull;

import com.lordkittycat.alternate_dimension.world.inventory.CheesyBackpackGUIMenu;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModItems;

@EventBusSubscriber
public class CheesyBackpackInventoryCapability extends ComponentItemHandler {
	@SubscribeEvent
	public static void onItemDropped(ItemTossEvent event) {
		if (event.getEntity().getItem().getItem() == AlternateDimensionModItems.CHEESY_BACKPACK.get()) {
			Player player = event.getPlayer();
			if (player.containerMenu instanceof CheesyBackpackGUIMenu)
				player.closeContainer();
		}
	}

	public CheesyBackpackInventoryCapability(MutableDataComponentHolder parent) {
		super(parent, DataComponents.CONTAINER, 18);
	}

	@Override
	public int getSlotLimit(int slot) {
		return 64;
	}

	@Override
	public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
		return stack.getItem() != AlternateDimensionModItems.CHEESY_BACKPACK.get();
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return super.getStackInSlot(slot).copy();
	}
}