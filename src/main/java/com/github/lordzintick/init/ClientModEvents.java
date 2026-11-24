package com.github.lordzintick.init;

import com.github.lordzintick.modular.ModularItemModel;
import com.lordkittycat.alternate_dimension.AlternateDimensionMod;
import net.minecraft.resources.Identifier;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterItemModelsEvent;

@EventBusSubscriber(modid = AlternateDimensionMod.MODID, value = Dist.CLIENT)
public class ClientModEvents {
    @SubscribeEvent
    public static void onRegisterItemModels(RegisterItemModelsEvent event) {
        event.register(Identifier.fromNamespaceAndPath(AlternateDimensionMod.MODID, "modular_model"), ModularItemModel.Unbaked.MAP_CODEC);
    }
}
