package com.github.lordzintick.modular.parts;

import com.github.lordzintick.init.AlternateDimensionModRegistries;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryCodecs;

import java.util.List;
import java.util.Set;

public record GearArchetype(
        List<HolderSet<GearPartType>> requiredPartGroups
) {
    public static final Codec<GearArchetype> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            RegistryCodecs.homogeneousList(AlternateDimensionModRegistries.PART_TYPE_KEY)
                    .listOf().fieldOf("required_part_groups").forGetter(GearArchetype::requiredPartGroups)
    ).apply(instance, GearArchetype::new));

    public boolean matches(Set<Holder<GearPartType>> presentParts) {
        for (HolderSet<GearPartType> group : requiredPartGroups) {
            if (presentParts.stream().noneMatch(group::contains)) return false;
        }
        return true;
    }
}
