package com.lordkittycat.alternate_dimension.item.inventory;

import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.item.ItemAccessItemHandler;
import net.neoforged.neoforge.transfer.access.ItemAccess;
import net.neoforged.neoforge.event.entity.item.ItemTossEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.entity.player.Player;
import net.minecraft.core.component.DataComponents;

import com.lordkittycat.alternate_dimension.world.inventory.EssencePouchGUIMenu;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModItems;

@EventBusSubscriber
public class EssencePouchInventoryCapability extends ItemAccessItemHandler {
	@SubscribeEvent
	public static void onItemDropped(ItemTossEvent event) {
		if (event.getEntity().getItem().getItem() == AlternateDimensionModItems.ESSENCE_POUCH.get()) {
			Player player = event.getPlayer();
			if (player.containerMenu instanceof EssencePouchGUIMenu)
				player.closeContainer();
		}
	}

	public EssencePouchInventoryCapability(ItemAccess access) {
		super(access, DataComponents.CONTAINER, 14);
	}

	@Override
	public boolean isValid(int index, ItemResource resource) {
		return super.isValid(index, resource) && resource.getItem() != AlternateDimensionModItems.ESSENCE_POUCH.get();
	}
}