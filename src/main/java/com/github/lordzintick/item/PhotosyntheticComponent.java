package com.github.lordzintick.item;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record PhotosyntheticComponent(float factor, boolean requiresSkyView, int minTime, int maxTime) {
    public static final Codec<PhotosyntheticComponent> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.FLOAT.fieldOf("factor").forGetter(PhotosyntheticComponent::factor),
            Codec.BOOL.fieldOf("requires_sky_view").forGetter(PhotosyntheticComponent::requiresSkyView),
            Codec.INT.fieldOf("min_time").forGetter(PhotosyntheticComponent::minTime),
            Codec.INT.fieldOf("max_time").forGetter(PhotosyntheticComponent::maxTime)
    ).apply(instance, PhotosyntheticComponent::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, PhotosyntheticComponent> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.FLOAT, PhotosyntheticComponent::factor,
            ByteBufCodecs.BOOL, PhotosyntheticComponent::requiresSkyView,
            ByteBufCodecs.VAR_INT, PhotosyntheticComponent::minTime,
            ByteBufCodecs.VAR_INT, PhotosyntheticComponent::maxTime,
            PhotosyntheticComponent::new
    );
}
