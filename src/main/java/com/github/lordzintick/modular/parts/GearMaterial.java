package com.github.lordzintick.modular.parts;

import com.github.lordzintick.init.AlternateDimensionModComponents;
import com.github.lordzintick.modular.rendering.MaterialOverlay;
import com.github.lordzintick.modular.util.NameAffix;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public record GearMaterial(
        String nameKey,
        int color,
        float durabilityMultiplier,
        float powerFactor,
        Ingredient ingredient,
        Optional<NameAffix> nameAffix,
        List<PartComponent> components,
        List<MaterialOverlay> overlays
) {
    public static final Codec<GearMaterial> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.fieldOf("name_key").forGetter(GearMaterial::nameKey),
            Codec.INT.fieldOf("color").forGetter(GearMaterial::color),
            Codec.FLOAT.optionalFieldOf("durability_multiplier", 1.0f).forGetter(GearMaterial::durabilityMultiplier),
            Codec.FLOAT.optionalFieldOf("power_factor", 1.0f).forGetter(GearMaterial::powerFactor),
            Ingredient.CODEC.fieldOf("ingredient").forGetter(GearMaterial::ingredient),
            NameAffix.CODEC.optionalFieldOf("name_affix").forGetter(GearMaterial::nameAffix),
            PartComponent.CODEC.listOf().optionalFieldOf("components", List.of()).forGetter(GearMaterial::components),
            MaterialOverlay.CODEC.listOf().optionalFieldOf("overlays", List.of())
                    .forGetter(GearMaterial::overlays)
    ).apply(instance, GearMaterial::new));

    public List<Component> traitDescriptions(ItemStack stack, boolean categories) {
        ArrayList<Component> traitDescriptions = new ArrayList<>();
        GearPartsComponent gearParts = stack.get(AlternateDimensionModComponents.GEAR_PARTS);
        if (gearParts == null) {
            for (PartComponent partComp : components) {
                for (String descKey : partComp.traitDescriptions()) {
                    if (categories) {
                        traitDescriptions.add(Component.translatable(partComp.name()).append(" - ").append(Component.translatable(descKey)));
                    } else {
                        traitDescriptions.add(Component.translatable(descKey));
                    }
                }
            }
        } else {
            for (PartComponent partComp : components) {
                if (partComp.meetsPartTypes(gearParts)) {
                    for (String descKey : partComp.traitDescriptions()) {
                        if (categories) {
                            traitDescriptions.add(Component.translatable(partComp.name()).append(" - ").append(Component.translatable(descKey)));
                        } else {
                            traitDescriptions.add(Component.translatable(descKey));
                        }
                    }
                }
            }
        }

        return traitDescriptions;
    }
}
