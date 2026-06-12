/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package com.lordkittycat.alternate_dimension.init;

import org.lwjgl.glfw.GLFW;

import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.client.Minecraft;
import net.minecraft.client.KeyMapping;

import com.lordkittycat.alternate_dimension.network.ToggleJetpackHoverMessage;
import com.lordkittycat.alternate_dimension.network.ToggleJetpackEngineMessage;
import com.lordkittycat.alternate_dimension.network.JetpackActivateKeyMessage;
import com.lordkittycat.alternate_dimension.network.FuzzySocksActivateKeyMessage;

@EventBusSubscriber(Dist.CLIENT)
public class AlternateDimensionModKeyMappings {
	public static final KeyMapping FUZZY_SOCKS_ACTIVATE_KEY = new KeyMapping("key.alternate_dimension.fuzzy_socks_activate_key", GLFW.GLFW_KEY_B, "key.categories.alternate_dimension") {
		private boolean isDownOld = false;

		@Override
		public void setDown(boolean isDown) {
			super.setDown(isDown);
			if (isDownOld != isDown && isDown) {
				PacketDistributor.sendToServer(new FuzzySocksActivateKeyMessage(0, 0));
				FuzzySocksActivateKeyMessage.pressAction(Minecraft.getInstance().player, 0, 0);
			}
			isDownOld = isDown;
		}
	};
	public static final KeyMapping TOGGLE_JETPACK_ENGINE = new KeyMapping("key.alternate_dimension.toggle_jetpack_engine", GLFW.GLFW_KEY_G, "key.categories.alternate_dimension") {
		private boolean isDownOld = false;

		@Override
		public void setDown(boolean isDown) {
			super.setDown(isDown);
			if (isDownOld != isDown && isDown) {
				PacketDistributor.sendToServer(new ToggleJetpackEngineMessage(0, 0));
				ToggleJetpackEngineMessage.pressAction(Minecraft.getInstance().player, 0, 0);
			}
			isDownOld = isDown;
		}
	};
	public static final KeyMapping TOGGLE_JETPACK_HOVER = new KeyMapping("key.alternate_dimension.toggle_jetpack_hover", GLFW.GLFW_KEY_H, "key.categories.alternate_dimension") {
		private boolean isDownOld = false;

		@Override
		public void setDown(boolean isDown) {
			super.setDown(isDown);
			if (isDownOld != isDown && isDown) {
				PacketDistributor.sendToServer(new ToggleJetpackHoverMessage(0, 0));
				ToggleJetpackHoverMessage.pressAction(Minecraft.getInstance().player, 0, 0);
			}
			isDownOld = isDown;
		}
	};
	public static final KeyMapping JETPACK_ACTIVATE_KEY = new KeyMapping("key.alternate_dimension.jetpack_activate_key", GLFW.GLFW_KEY_SPACE, "key.categories.alternate_dimension") {
		private boolean isDownOld = false;

		@Override
		public void setDown(boolean isDown) {
			super.setDown(isDown);
			if (isDownOld != isDown && isDown) {
				PacketDistributor.sendToServer(new JetpackActivateKeyMessage(0, 0));
				JetpackActivateKeyMessage.pressAction(Minecraft.getInstance().player, 0, 0);
			}
			isDownOld = isDown;
		}
	};

	@SubscribeEvent
	public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
		event.register(FUZZY_SOCKS_ACTIVATE_KEY);
		event.register(TOGGLE_JETPACK_ENGINE);
		event.register(TOGGLE_JETPACK_HOVER);
		event.register(JETPACK_ACTIVATE_KEY);
	}

	@EventBusSubscriber(Dist.CLIENT)
	public static class KeyEventListener {
		@SubscribeEvent
		public static void onClientTick(ClientTickEvent.Post event) {
			if (Minecraft.getInstance().screen == null) {
				FUZZY_SOCKS_ACTIVATE_KEY.consumeClick();
				TOGGLE_JETPACK_ENGINE.consumeClick();
				TOGGLE_JETPACK_HOVER.consumeClick();
				JETPACK_ACTIVATE_KEY.consumeClick();
			}
		}
	}
}