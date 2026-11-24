package com.github.lordzintick.modular;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;
import java.util.Optional;

public record GearMaterial(
        String nameKey,
        int color,
        float durabilityMultiplier,
        float miningSpeed,
        Ingredient repairIngredient,
        Optional<NameAffix> nameAffix,
        List<String> traitDescriptions,
        DataComponentPatch components
) {
    public static final Codec<GearMaterial> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.fieldOf("name_key").forGetter(GearMaterial::nameKey),
            Codec.INT.fieldOf("color").forGetter(GearMaterial::color),
            Codec.FLOAT.optionalFieldOf("durability_multiplier", 1.0f).forGetter(GearMaterial::durabilityMultiplier),
            Codec.FLOAT.optionalFieldOf("mining_speed", 1.0f).forGetter(GearMaterial::miningSpeed),
            Ingredient.CODEC.fieldOf("repair_ingredient").forGetter(GearMaterial::repairIngredient),
            NameAffix.CODEC.optionalFieldOf("name_affix").forGetter(GearMaterial::nameAffix),
            Codec.STRING.listOf().optionalFieldOf("trait_descriptions", List.of()).forGetter(GearMaterial::traitDescriptions),
            DataComponentPatch.CODEC.optionalFieldOf("components", DataComponentPatch.EMPTY).forGetter(GearMaterial::components)
    ).apply(instance, GearMaterial::new));
}
