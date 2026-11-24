package com.github.lordzintick.init;

import com.github.lordzintick.modular.GearArchetype;
import com.github.lordzintick.modular.GearMaterial;
import com.github.lordzintick.modular.GearPartType;
import com.lordkittycat.alternate_dimension.AlternateDimensionMod;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;

public class AlternateDimensionModRegistries {
    public static final ResourceKey<Registry<GearMaterial>> MATERIAL_KEY =
            ResourceKey.createRegistryKey(Identifier.fromNamespaceAndPath(AlternateDimensionMod.MODID, "gear_material"));
    public static final ResourceKey<Registry<GearPartType>> PART_TYPE_KEY =
            ResourceKey.createRegistryKey(Identifier.fromNamespaceAndPath(AlternateDimensionMod.MODID, "gear_part_type"));
    public static final ResourceKey<Registry<GearArchetype>> ARCHETYPE_KEY =
            ResourceKey.createRegistryKey(Identifier.fromNamespaceAndPath(AlternateDimensionMod.MODID, "gear_archetype"));

    public static void registerDatapackRegistries(DataPackRegistryEvent.NewRegistry event) {
        event.dataPackRegistry(MATERIAL_KEY, GearMaterial.CODEC, GearMaterial.CODEC);
        event.dataPackRegistry(PART_TYPE_KEY, GearPartType.CODEC, GearPartType.CODEC);
        event.dataPackRegistry(ARCHETYPE_KEY, GearArchetype.CODEC, GearArchetype.CODEC);
    }
}
