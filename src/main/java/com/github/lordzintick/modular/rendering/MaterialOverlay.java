package com.github.lordzintick.modular.rendering;

import com.github.lordzintick.init.AlternateDimensionModRegistries;
import com.github.lordzintick.modular.parts.GearPartType;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.resources.Identifier;

import java.util.Optional;

public record MaterialOverlay(
        Identifier texture,
        Optional<Integer> color,
        Optional<HolderSet<GearPartType>> partTypes
) {
    public static final Codec<MaterialOverlay> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Identifier.CODEC.fieldOf("texture").forGetter(MaterialOverlay::texture),
            Codec.INT.optionalFieldOf("color").forGetter(MaterialOverlay::color),
            RegistryCodecs.homogeneousList(AlternateDimensionModRegistries.PART_TYPE_KEY)
                    .optionalFieldOf("part_types").forGetter(MaterialOverlay::partTypes)
    ).apply(instance, MaterialOverlay::new));
}
