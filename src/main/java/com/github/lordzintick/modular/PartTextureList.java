package com.github.lordzintick.modular;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.Identifier;

import java.util.List;

public record PartTextureList(List<PartTexture> textures, Identifier defaultTexture) {
    public static final Codec<PartTextureList> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            PartTexture.CODEC.listOf().fieldOf("textures").forGetter(PartTextureList::textures),
            Identifier.CODEC.fieldOf("default").forGetter(PartTextureList::defaultTexture)
    ).apply(instance, PartTextureList::new));
}
