package com.github.lordzintick.init;

import com.github.lordzintick.spellcasting.Spell;
import com.lordkittycat.alternate_dimension.AlternateDimensionMod;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NewRegistryEvent;
import net.neoforged.neoforge.registries.RegistryBuilder;

@EventBusSubscriber(modid = AlternateDimensionMod.MODID)
public class AlternateDimensionModRegistries {
    public static final ResourceKey<Registry<Spell>> SPELLS_KEY = ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(AlternateDimensionMod.MODID, "spells"));
    public static final Registry<Spell> SPELLS = new RegistryBuilder<>(SPELLS_KEY)
            .create();

    @SubscribeEvent
    public static void registerRegistries(NewRegistryEvent event) {
        event.register(SPELLS);
    }
}
