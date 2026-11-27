package com.github.lordzintick.item.jetpack;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record JetpackFuelComponent(int fuel) {
    public static final JetpackFuelComponent EMPTY = new JetpackFuelComponent(0);

    public static final Codec<JetpackFuelComponent> CODEC =
            Codec.INT.xmap(JetpackFuelComponent::new, JetpackFuelComponent::fuel);

    public static final StreamCodec<ByteBuf, JetpackFuelComponent> STREAM_CODEC =
            ByteBufCodecs.VAR_INT.map(JetpackFuelComponent::new, JetpackFuelComponent::fuel);
}
