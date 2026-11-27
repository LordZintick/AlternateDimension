package com.github.lordzintick.item.jetpack;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public record JetpackComponent(
        double verticalSpeed,
        double horizontalSpeed,
        double hoverDescentSpeed,
        TagKey<Item> fuelTag,
        int fuelPerItem,
        int fuelCapacity,
        int fuelUsage,
        boolean engineOn,
        boolean hoverOn
) {
    public static final Codec<JetpackComponent> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.DOUBLE.fieldOf("vertical_speed").forGetter(JetpackComponent::verticalSpeed),
            Codec.DOUBLE.fieldOf("horizontal_speed").forGetter(JetpackComponent::horizontalSpeed),
            Codec.DOUBLE.optionalFieldOf("hover_descent_speed", 0.02).forGetter(JetpackComponent::hoverDescentSpeed),
            TagKey.codec(Registries.ITEM).fieldOf("fuel_tag").forGetter(JetpackComponent::fuelTag),
            Codec.INT.fieldOf("fuel_per_item").forGetter(JetpackComponent::fuelPerItem),
            Codec.INT.fieldOf("fuel_capacity").forGetter(JetpackComponent::fuelCapacity),
            Codec.INT.fieldOf("fuel_usage").forGetter(JetpackComponent::fuelUsage),
            Codec.BOOL.optionalFieldOf("engine_on", false).forGetter(JetpackComponent::engineOn),
            Codec.BOOL.optionalFieldOf("hover_on", false).forGetter(JetpackComponent::hoverOn)
    ).apply(instance, JetpackComponent::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, JetpackComponent> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.DOUBLE, JetpackComponent::verticalSpeed,
            ByteBufCodecs.DOUBLE, JetpackComponent::horizontalSpeed,
            ByteBufCodecs.DOUBLE, JetpackComponent::hoverDescentSpeed,
            TagKey.streamCodec(Registries.ITEM), JetpackComponent::fuelTag,
            ByteBufCodecs.VAR_INT, JetpackComponent::fuelPerItem,
            ByteBufCodecs.VAR_INT, JetpackComponent::fuelCapacity,
            ByteBufCodecs.VAR_INT, JetpackComponent::fuelUsage,
            ByteBufCodecs.BOOL, JetpackComponent::engineOn,
            ByteBufCodecs.BOOL, JetpackComponent::hoverOn,
            JetpackComponent::new
    );

    public JetpackComponent withToggledEngine() {
        return new JetpackComponent(verticalSpeed, horizontalSpeed, hoverDescentSpeed, fuelTag, fuelPerItem, fuelCapacity, fuelUsage, !engineOn, hoverOn);
    }

    public JetpackComponent withToggledHover() {
        return new JetpackComponent(verticalSpeed, horizontalSpeed, hoverDescentSpeed, fuelTag, fuelPerItem, fuelCapacity, fuelUsage, engineOn, !hoverOn);
    }

    public JetpackComponent scaledByPower(float powerFactor, float durabilityMult) {
        return new JetpackComponent(
                verticalSpeed * Math.sqrt(powerFactor),
                horizontalSpeed * Math.sqrt(powerFactor),
                hoverDescentSpeed,
                fuelTag,
                fuelPerItem,
                Math.round(fuelCapacity * durabilityMult),
                fuelUsage,
                engineOn,
                hoverOn
        );
    }
}
