package com.github.lordzintick.modular;

import com.github.lordzintick.init.AlternateDimensionModRegistries;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.resources.Identifier;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public record GearPartType(
        String partItemNameKey,
        int zIndex,
        boolean isHandheld,
        boolean contributeMaterialName,
        Identifier texture,
        int baseDurability,
        float speedMultiplier,
        Optional<NameAffix> nameAffix,
        List<String> traitDescriptions,
        HolderSet<GearPartType> requiresAny,
        HolderSet<GearPartType> incompatibleWith,
        DataComponentPatch components
) {
    public static final Codec<GearPartType> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.fieldOf("part_item_name_key").forGetter(GearPartType::partItemNameKey),
            Codec.INT.fieldOf("z_index").forGetter(GearPartType::zIndex),
            Codec.BOOL.optionalFieldOf("is_handheld", false).forGetter(GearPartType::isHandheld),
            Codec.BOOL.optionalFieldOf("contribute_material_name", false).forGetter(GearPartType::contributeMaterialName),
            Identifier.CODEC.fieldOf("texture").forGetter(GearPartType::texture),
            Codec.INT.optionalFieldOf("base_durability", 0).forGetter(GearPartType::baseDurability),
            Codec.FLOAT.optionalFieldOf("speed_multiplier", 1.0f).forGetter(GearPartType::speedMultiplier),
            NameAffix.CODEC.optionalFieldOf("name_affix").forGetter(GearPartType::nameAffix),
            Codec.STRING.listOf().optionalFieldOf("trait_descriptions", List.of()).forGetter(GearPartType::traitDescriptions),
            RegistryCodecs.homogeneousList(AlternateDimensionModRegistries.PART_TYPE_KEY)
                    .optionalFieldOf("requires_any", HolderSet.empty()).forGetter(GearPartType::requiresAny),
            RegistryCodecs.homogeneousList(AlternateDimensionModRegistries.PART_TYPE_KEY)
                    .optionalFieldOf("incompatible_with", HolderSet.empty()).forGetter(GearPartType::incompatibleWith),
            DataComponentPatch.CODEC.optionalFieldOf("components", DataComponentPatch.EMPTY).forGetter(GearPartType::components)
    ).apply(instance, GearPartType::new));

    public static boolean isValidPartCombination(Set<Holder<GearPartType>> presentParts) {
        for (Holder<GearPartType> partHolder : presentParts) {
            GearPartType part = partHolder.value();
            for (Holder<GearPartType> other : presentParts) {
                if (!other.equals(partHolder) && part.incompatibleWith().contains(other)) {
                    return false;
                }
            }
            if (part.requiresAny().size() > 0 && presentParts.stream().noneMatch(part.requiresAny()::contains)) {
                return false;
            }
        }
        return true;
    }
}
