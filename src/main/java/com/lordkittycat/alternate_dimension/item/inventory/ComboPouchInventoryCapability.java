package com.lordkittycat.alternate_dimension.item.inventory;

import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.item.ItemAccessItemHandler;
import net.neoforged.neoforge.transfer.access.ItemAccess;
import net.neoforged.neoforge.event.entity.item.ItemTossEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.entity.player.Player;
import net.minecraft.core.component.DataComponents;

import com.lordkittycat.alternate_dimension.world.inventory.ComboPouchGUIMenu;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModItems;

@EventBusSubscriber
public class ComboPouchInventoryCapability extends ItemAccessItemHandler {
	@SubscribeEvent
	public static void onItemDropped(ItemTossEvent event) {
		if (event.getEntity().getItem().getItem() == AlternateDimensionModItems.COMBO_POUCH.get()) {
			Player player = event.getPlayer();
			if (player.containerMenu instanceof ComboPouchGUIMenu)
				player.closeContainer();
		}
	}

	public ComboPouchInventoryCapability(ItemAccess access) {
		super(access, DataComponents.CONTAINER, 38);
	}

	@Override
	public boolean isValid(int index, ItemResource resource) {
		return super.isValid(index, resource) && resource.getItem() != AlternateDimensionModItems.COMBO_POUCH.get();
	}
}