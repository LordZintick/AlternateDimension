package com.github.lordzintick.modular.blueprint;

import com.github.lordzintick.init.AlternateDimensionModRegistries;
import com.github.lordzintick.modular.parts.GearPartType;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.RegistryFixedCodec;

public record BlueprintDataComponent(Holder<GearPartType> partType, int materialCost) {
    public static final Codec<BlueprintDataComponent> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            RegistryFixedCodec.create(AlternateDimensionModRegistries.PART_TYPE_KEY).fieldOf("part_type").forGetter(BlueprintDataComponent::partType),
            Codec.INT.optionalFieldOf("material_cost", 1).forGetter(BlueprintDataComponent::materialCost)
    ).apply(instance, BlueprintDataComponent::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, BlueprintDataComponent> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.holderRegistry(AlternateDimensionModRegistries.PART_TYPE_KEY), BlueprintDataComponent::partType,
            ByteBufCodecs.VAR_INT, BlueprintDataComponent::materialCost,
            BlueprintDataComponent::new
    );
}