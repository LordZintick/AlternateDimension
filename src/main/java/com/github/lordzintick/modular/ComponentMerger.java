package com.github.lordzintick.modular;

import com.github.lordzintick.init.AlternateDimensionModComponents;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Tool;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ComponentMerger {
    public static void applyMergedComponents(ItemStack stack, GearPartsComponent gearParts) {
        stack.set(AlternateDimensionModComponents.GEAR_PARTS.get(), gearParts);
        if (gearParts.parts().size() <= 1) return;

        List<Tool.Rule> combinedToolRules = new ArrayList<>();
        ItemAttributeModifiers.Builder attrBuilder = ItemAttributeModifiers.builder();
        int totalDurability = 0;

        for (var entry : gearParts.sortedByZIndex()) {
            GearPartType part = entry.getKey().value();
            GearMaterial mat = entry.getValue().value();

            totalDurability += Math.round(part.baseDurability() * mat.durabilityMultiplier());
            float calcSpeed = mat.miningSpeed() * part.speedMultiplier();

            applyPatchSafely(stack, mat.components(), combinedToolRules, attrBuilder, calcSpeed);
            applyPatchSafely(stack, part.components(), combinedToolRules, attrBuilder, calcSpeed);
        }

        if (totalDurability > 0) {
            stack.set(DataComponents.MAX_DAMAGE, totalDurability);
        }
        if (!combinedToolRules.isEmpty()) {
            stack.set(DataComponents.TOOL, new Tool(combinedToolRules, 1.0f, 1, true));
        }
        ItemAttributeModifiers builtModifiers = attrBuilder.build();
        if (!builtModifiers.modifiers().isEmpty()) {
            stack.set(DataComponents.ATTRIBUTE_MODIFIERS, builtModifiers);
        }
    }

    private static void applyPatchSafely(
            ItemStack stack,
            DataComponentPatch patch,
            List<Tool.Rule> toolRulesOut,
            ItemAttributeModifiers.Builder attrOut,
            float calculatedSpeed
    ) {
        for (var entry : patch.entrySet()) {
            DataComponentType<?> type = entry.getKey();
            Optional<?> valueOpt = entry.getValue();
            if (valueOpt.isEmpty()) continue;

            Object value = valueOpt.get();
            if (type == DataComponents.TOOL && value instanceof Tool tool) {
                for (Tool.Rule rule : tool.rules()) {
                    float speed = rule.speed().map(s -> s * calculatedSpeed).orElse(calculatedSpeed);
                    toolRulesOut.add(new Tool.Rule(rule.blocks(), Optional.of(speed), rule.correctForDrops()));
                }
            } else if (type == DataComponents.ATTRIBUTE_MODIFIERS && value instanceof ItemAttributeModifiers mods) {
                for (ItemAttributeModifiers.Entry modEntry : mods.modifiers()) {
                    attrOut.add(modEntry.attribute(), modEntry.modifier(), modEntry.slot());
                }
            } else {
                setUnchecked(stack, type, value);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private static <T> void setUnchecked(ItemStack stack, DataComponentType<T> type, Object value) {
        stack.set(type, (T) value);
    }
}
