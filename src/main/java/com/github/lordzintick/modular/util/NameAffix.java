package com.github.lordzintick.modular.util;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.StringRepresentable;

public record NameAffix(
        AffixPosition position,
        int priority,
        String translationKey,
        boolean takesMaterial,
        boolean tintWithMaterial
) {
    public enum AffixPosition implements StringRepresentable {
        PREFIX("prefix"),
        SUFFIX("suffix");

        private final String name;
        AffixPosition(String name) { this.name = name; }
        @Override public String getSerializedName() { return name; }
        public static final Codec<AffixPosition> CODEC = StringRepresentable.fromEnum(AffixPosition::values);
    }

    public static final Codec<NameAffix> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            AffixPosition.CODEC.fieldOf("position").forGetter(NameAffix::position),
            Codec.INT.optionalFieldOf("priority", 50).forGetter(NameAffix::priority),
            Codec.STRING.fieldOf("translation_key").forGetter(NameAffix::translationKey),
            Codec.BOOL.optionalFieldOf("takes_material", false).forGetter(NameAffix::takesMaterial),
            Codec.BOOL.optionalFieldOf("tint_with_material", true).forGetter(NameAffix::tintWithMaterial)
    ).apply(instance, NameAffix::new));
}
