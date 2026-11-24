package com.github.lordzintick.modular;

import com.github.lordzintick.init.AlternateDimensionModRegistries;
import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.RegistryFixedCodec;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public record GearPartsComponent(Map<Holder<GearPartType>, Holder<GearMaterial>> parts) {
    public static final GearPartsComponent EMPTY = new GearPartsComponent(Map.of());

    public static final Codec<GearPartsComponent> CODEC = Codec.unboundedMap(
            RegistryFixedCodec.create(AlternateDimensionModRegistries.PART_TYPE_KEY),
            RegistryFixedCodec.create(AlternateDimensionModRegistries.MATERIAL_KEY)
    ).xmap(GearPartsComponent::new, GearPartsComponent::parts);

    public static final StreamCodec<RegistryFriendlyByteBuf, GearPartsComponent> STREAM_CODEC = ByteBufCodecs.map(
            HashMap::new,
            ByteBufCodecs.holderRegistry(AlternateDimensionModRegistries.PART_TYPE_KEY),
            ByteBufCodecs.holderRegistry(AlternateDimensionModRegistries.MATERIAL_KEY)
    ).map(GearPartsComponent::new, comp -> new HashMap<>(comp.parts));

    public List<Map.Entry<Holder<GearPartType>, Holder<GearMaterial>>> sortedByZIndex() {
        return parts.entrySet().stream()
                .sorted(Comparator.comparingInt(e -> e.getKey().value().zIndex()))
                .toList();
    }
}


