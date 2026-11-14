package com.lordkittycat.alternate_dimension.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.component.WrittenBookContent;
import net.minecraft.world.item.WrittenBookItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.server.network.Filterable;
import net.minecraft.network.chat.Component;
import net.minecraft.core.component.DataComponents;

import java.util.List;

public class MainGuidebookItem extends WrittenBookItem {
	public MainGuidebookItem(Item.Properties properties) {
		super(properties.stacksTo(1).rarity(Rarity.RARE).component(() -> DataComponents.WRITTEN_BOOK_CONTENT,
				new WrittenBookContent(Filterable.passThrough("The Enchanted Journal"), "Unknown", 0, List.of(Filterable.passThrough(Component.literal("The Enchanted Journal"))), true)));
	}

	@Override
	public InteractionResult use(Level level, Player player, InteractionHand hand) {
		if (level.isClientSide()) {
			openCustomBookScreen();
		}
		return InteractionResult.SUCCESS;
	}

	/**
	 * Kept reflection-only so the common item class never has a hard client-class reference
	 * when loaded on a dedicated server. The input event is still the primary path because
	 * it fires before blocks/entities can consume the right click.
	 */
	private static void openCustomBookScreen() {
		try {
			Class<?> minecraftClass = Class.forName("net.minecraft.client.Minecraft");
			Class<?> screenBase = Class.forName("net.minecraft.client.gui.screens.Screen");
			Class<?> screenClass = Class.forName("com.lordkittycat.alternate_dimension.client.gui.MainGuidebookBookScreen");
			Object minecraft = minecraftClass.getMethod("getInstance").invoke(null);
			Object screen = screenClass.getConstructor().newInstance();
			minecraftClass.getMethod("setScreen", screenBase).invoke(minecraft, screen);
		} catch (ReflectiveOperationException ignored) {
		}
	}
}