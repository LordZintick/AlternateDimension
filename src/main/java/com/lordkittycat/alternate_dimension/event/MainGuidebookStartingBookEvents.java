package com.lordkittycat.alternate_dimension.event;

import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.item.ItemStack;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.nbt.CompoundTag;

import com.lordkittycat.alternate_dimension.init.AlternateDimensionModItems;

@EventBusSubscriber(modid = "alternate_dimension")
public final class MainGuidebookStartingBookEvents {
	private static final String RECEIVED_TAG = "alternate_dimension:received_starting_book_main_guidebook";

	private MainGuidebookStartingBookEvents() {
	}

	@SubscribeEvent
	public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
		if (!(event.getEntity() instanceof ServerPlayer player)) {
			return;
		}
		CompoundTag persistentData = player.getPersistentData();
		if (persistentData.getBooleanOr(RECEIVED_TAG, false)) {
			return;
		}
		ItemStack book = new ItemStack(AlternateDimensionModItems.MAIN_GUIDEBOOK.get());
		if (!player.getInventory().add(book)) {
			player.drop(book, false);
		}
		persistentData.putBoolean(RECEIVED_TAG, true);
	}

	@SubscribeEvent
	public static void onPlayerClone(PlayerEvent.Clone event) {
		if (event.getOriginal().getPersistentData().getBooleanOr(RECEIVED_TAG, false)) {
			event.getEntity().getPersistentData().putBoolean(RECEIVED_TAG, true);
		}
	}
}