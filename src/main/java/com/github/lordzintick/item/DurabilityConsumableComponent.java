package com.github.lordzintick.item;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.item.component.Consumable;

public record DurabilityConsumableComponent(int durabilityLoss, Consumable config) {
    public static final Codec<DurabilityConsumableComponent> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.optionalFieldOf("durability_loss", 1).forGetter(DurabilityConsumableComponent::durabilityLoss),
            Consumable.CODEC.fieldOf("config").forGetter(DurabilityConsumableComponent::config)
    ).apply(instance, DurabilityConsumableComponent::new));
}
