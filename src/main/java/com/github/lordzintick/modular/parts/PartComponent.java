package com.github.lordzintick.modular.parts;

import com.github.lordzintick.init.AlternateDimensionModRegistries;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.core.component.DataComponentPatch;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public record PartComponent(
        String name,
        Optional<HolderSet<GearPartType>> partTypes,
        List<String> traitDescriptions,
        DataComponentPatch dataComponentPatch
) {
    public static final Codec<PartComponent> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.fieldOf("name").forGetter(PartComponent::name),
            RegistryCodecs.homogeneousList(AlternateDimensionModRegistries.PART_TYPE_KEY).optionalFieldOf("part_types").forGetter(PartComponent::partTypes),
            Codec.STRING.listOf().optionalFieldOf("trait_descriptions", List.of()).forGetter(PartComponent::traitDescriptions),
            DataComponentPatch.CODEC.fieldOf("components").forGetter(PartComponent::dataComponentPatch)
    ).apply(instance, PartComponent::new));

    public boolean meetsPartTypes(GearPartsComponent partsComp) {
        return partTypes.map(holders -> holders.stream()
                .allMatch(part -> partsComp.parts().containsKey(part))).orElse(true);
    }
}
