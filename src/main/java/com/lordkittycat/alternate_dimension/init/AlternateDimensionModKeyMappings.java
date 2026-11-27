/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package com.lordkittycat.alternate_dimension.init;

import org.lwjgl.glfw.GLFW;

import net.neoforged.neoforge.client.network.ClientPacketDistributor;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.client.Minecraft;
import net.minecraft.client.KeyMapping;

import com.lordkittycat.alternate_dimension.network.OpenPouchKeyMessage;
import com.lordkittycat.alternate_dimension.network.OpenBackpackKeyMessage;

@EventBusSubscriber(Dist.CLIENT)
public class AlternateDimensionModKeyMappings {
	public static final KeyMapping OPEN_BACKPACK_KEY = new KeyMapping("key.alternate_dimension.open_backpack_key", GLFW.GLFW_KEY_B, KeyMapping.Category.INVENTORY) {
		private boolean isDownOld = false;

		@Override
		public void setDown(boolean isDown) {
			super.setDown(isDown);
			if (isDownOld != isDown && isDown) {
				ClientPacketDistributor.sendToServer(new OpenBackpackKeyMessage(0, 0));
				OpenBackpackKeyMessage.pressAction(Minecraft.getInstance().player, 0, 0);
			}
			isDownOld = isDown;
		}
	};
	public static final KeyMapping OPEN_POUCH_KEY = new KeyMapping("key.alternate_dimension.open_pouch_key", GLFW.GLFW_KEY_N, KeyMapping.Category.INVENTORY) {
		private boolean isDownOld = false;

		@Override
		public void setDown(boolean isDown) {
			super.setDown(isDown);
			if (isDownOld != isDown && isDown) {
				ClientPacketDistributor.sendToServer(new OpenPouchKeyMessage(0, 0));
				OpenPouchKeyMessage.pressAction(Minecraft.getInstance().player, 0, 0);
			}
			isDownOld = isDown;
		}
	};

	@SubscribeEvent
	public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
		event.register(OPEN_BACKPACK_KEY);
		event.register(OPEN_POUCH_KEY);
	}

	@EventBusSubscriber(Dist.CLIENT)
	public static class KeyEventListener {
		@SubscribeEvent
		public static void onClientTick(ClientTickEvent.Post event) {
			if (Minecraft.getInstance().screen == null) {
				OPEN_BACKPACK_KEY.consumeClick();
				OPEN_POUCH_KEY.consumeClick();
			}
		}
	}
}