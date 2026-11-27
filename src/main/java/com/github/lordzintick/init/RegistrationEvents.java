package com.github.lordzintick.init;

import com.github.lordzintick.item.jetpack.JetpackTogglePayload;
import com.lordkittycat.alternate_dimension.AlternateDimensionMod;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = AlternateDimensionMod.MODID)
public class RegistrationEvents {
    @SubscribeEvent
    public static void registerPayloadHandlers(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar("1");

        registrar.playToServer(JetpackTogglePayload.TYPE, JetpackTogglePayload.STREAM_CODEC, JetpackTogglePayload::handleOnServer);
    }
}
