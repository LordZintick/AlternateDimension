package com.github.lordzintick.item.ring;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;

public record RingEffectComponent(Holder<MobEffect> effect) {
    public static final Codec<RingEffectComponent> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    BuiltInRegistries.MOB_EFFECT.holderByNameCodec().fieldOf("effect").forGetter(RingEffectComponent::effect)
            ).apply(instance, RingEffectComponent::new));
}
