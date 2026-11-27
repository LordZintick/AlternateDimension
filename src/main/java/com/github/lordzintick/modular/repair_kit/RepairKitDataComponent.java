package com.github.lordzintick.modular.repair_kit;

import com.github.lordzintick.init.AlternateDimensionModRegistries;
import com.github.lordzintick.modular.parts.GearMaterial;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.RegistryFixedCodec;

import java.util.Optional;

public record RepairKitDataComponent(
        Optional<Holder<GearMaterial>> material,
        int amount
) {
    public static final RepairKitDataComponent EMPTY = new RepairKitDataComponent(Optional.empty(), 0);

    public static final Codec<RepairKitDataComponent> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            RegistryFixedCodec.create(AlternateDimensionModRegistries.MATERIAL_KEY).optionalFieldOf("material").forGetter(RepairKitDataComponent::material),
            Codec.INT.optionalFieldOf("amount", 0).forGetter(RepairKitDataComponent::amount)
    ).apply(instance, RepairKitDataComponent::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, RepairKitDataComponent> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.optional(ByteBufCodecs.holderRegistry(AlternateDimensionModRegistries.MATERIAL_KEY)), RepairKitDataComponent::material,
            ByteBufCodecs.VAR_INT, RepairKitDataComponent::amount,
            RepairKitDataComponent::new
    );

    public boolean isEmpty() {
        return material.isEmpty() || amount <= 0;
    }
}
