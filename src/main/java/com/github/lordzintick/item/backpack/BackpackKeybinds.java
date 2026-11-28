package com.github.lordzintick.item.backpack;

import com.lordkittycat.alternate_dimension.AlternateDimensionMod;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import org.lwjgl.glfw.GLFW;

@EventBusSubscriber(modid = AlternateDimensionMod.MODID, value = Dist.CLIENT)
public class BackpackKeybinds {
    public static final KeyMapping OPEN_BACKPACK_KEY = new KeyMapping(
            "key.alternate_dimension.open_backpack",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_B,
            KeyMapping.Category.INVENTORY
    );

    @SubscribeEvent
    public static void onRegisterKeybinds(RegisterKeyMappingsEvent event) {
        event.register(OPEN_BACKPACK_KEY);
    }
}
