package com.github.lordzintick.modular.parts;

import com.github.lordzintick.init.AlternateDimensionModRegistries;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.resources.Identifier;

public record PartTexture(HolderSet<GearPartType> partTypes, Identifier texture) {
    public static final Codec<PartTexture> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            RegistryCodecs.homogeneousList(AlternateDimensionModRegistries.PART_TYPE_KEY)
                    .fieldOf("part_types").forGetter(PartTexture::partTypes),
            Identifier.CODEC.fieldOf("texture").forGetter(PartTexture::texture)
    ).apply(instance, PartTexture::new));
}
