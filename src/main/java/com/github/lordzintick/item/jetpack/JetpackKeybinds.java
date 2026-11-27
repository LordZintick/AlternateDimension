package com.github.lordzintick.item.jetpack;

import com.github.lordzintick.init.AlternateDimensionModComponents;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.EquipmentSlot;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;
import org.lwjgl.glfw.GLFW;

public class JetpackKeybinds {
    public static final KeyMapping TOGGLE_ENGINE = new KeyMapping(
            "key.alternate_dimension.jetpack_engine",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_G,
            KeyMapping.Category.MOVEMENT
    );

    public static final KeyMapping TOGGLE_HOVER = new KeyMapping(
            "key.alternate_dimension.jetpack_hover",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_H,
            KeyMapping.Category.MOVEMENT
    );

    public static void onRegisterKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(TOGGLE_ENGINE);
        event.register(TOGGLE_HOVER);
    }

    public static void onClientTick(ClientTickEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.screen != null) return;

        if (!mc.player.getItemBySlot(EquipmentSlot.CHEST).has(AlternateDimensionModComponents.JETPACK)) return;

        while (TOGGLE_ENGINE.consumeClick()) {
            ClientPacketDistributor.sendToServer(new JetpackTogglePayload(false));
        }
        while (TOGGLE_HOVER.consumeClick()) {
            ClientPacketDistributor.sendToServer(new JetpackTogglePayload(true));
        }
    }
}
