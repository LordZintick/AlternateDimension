package com.github.lordzintick.item;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.entity.LivingEntity;

public record SparkingComponent(float chance, int time) {
    public static final Codec<SparkingComponent> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.FLOAT.optionalFieldOf("chance", 0.4f).forGetter(SparkingComponent::chance),
            Codec.INT.optionalFieldOf("time", 40).forGetter(SparkingComponent::time)
    ).apply(instance, SparkingComponent::new));

    public void apply(LivingEntity entity) {
        if (entity.fireImmune()) return;
        if (Math.random() <= chance) {
            entity.igniteForTicks(time);
        }
    }
}
