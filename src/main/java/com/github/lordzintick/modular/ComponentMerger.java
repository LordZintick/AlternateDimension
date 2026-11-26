package com.github.lordzintick.modular;

import com.github.lordzintick.init.AlternateDimensionModComponents;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.ItemEnchantments;

import java.util.*;

public class ComponentMerger {

    public record AttributeKey(
            Holder<Attribute> attribute,
            AttributeModifier.Operation operation,
            EquipmentSlotGroup slot
    ) {}

    public static class AttributeAccumulator {
        final Identifier firstId;
        double sum = 0.0;
        int count = 0;

        AttributeAccumulator(Identifier firstId) {
            this.firstId = firstId;
        }

        void add(double amount) {
            this.sum += amount;
            this.count++;
        }

        double average() {
            return count == 0 ? 0.0 : sum / count;
        }
    }

    public static void applyMergedComponents(ItemStack stack, GearPartsComponent gearParts) {
        stack.set(AlternateDimensionModComponents.GEAR_PARTS.get(), gearParts);
        if (gearParts.parts().size() <= 1) return;

        List<Tool.Rule> combinedToolRules = new ArrayList<>();
        Map<AttributeKey, AttributeAccumulator> attributeGroups = new LinkedHashMap<>();
        ItemEnchantments.Mutable enchantments = new ItemEnchantments.Mutable(ItemEnchantments.EMPTY);
        int totalDurability = 0;

        for (var entry : gearParts.sortedByZIndex()) {
            GearPartType part = entry.getKey().value();
            GearMaterial mat = entry.getValue().value();

            totalDurability += Math.round(part.baseDurability() * mat.durabilityMultiplier());
            float combinedPower = part.powerFactor() * mat.powerFactor();

            for (PartComponent comp : mat.components()) {
                comp.applyIfCorrect(stack, combinedToolRules, attributeGroups, enchantments, combinedPower);
            }
            applyPatchSafely(stack, part.components(), combinedToolRules, attributeGroups, enchantments, combinedPower);
        }

        if (totalDurability > 0) {
            stack.set(DataComponents.MAX_DAMAGE, totalDurability);
        }

        if (!combinedToolRules.isEmpty()) {
            combinedToolRules.sort(Comparator.comparing(r -> r.correctForDrops().orElse(true)));
            stack.set(DataComponents.TOOL, new Tool(combinedToolRules, 1.0f, 1, true));
        }

        stack.set(DataComponents.ENCHANTMENTS, enchantments.toImmutable());

        if (!attributeGroups.isEmpty()) {
            ItemAttributeModifiers.Builder attrBuilder = ItemAttributeModifiers.builder();
            for (var entry : attributeGroups.entrySet()) {
                AttributeKey key = entry.getKey();
                AttributeAccumulator acc = entry.getValue();

                AttributeModifier averagedModifier = new AttributeModifier(
                        acc.firstId,
                        acc.average(),
                        key.operation()
                );
                attrBuilder.add(key.attribute(), averagedModifier, key.slot());
            }
            stack.set(DataComponents.ATTRIBUTE_MODIFIERS, attrBuilder.build());
        }
    }

    public static void applyPatchSafely(
            ItemStack stack,
            DataComponentPatch patch,
            List<Tool.Rule> toolRulesOut,
            Map<AttributeKey, AttributeAccumulator> attrGroupsOut,
            ItemEnchantments.Mutable enchantmentsOut,
            float combinedPower
    ) {
        for (var entry : patch.entrySet()) {
            DataComponentType<?> type = entry.getKey();
            Optional<?> valueOpt = entry.getValue();
            if (valueOpt.isEmpty()) continue;

            Object value = valueOpt.get();
            if (type == DataComponents.TOOL && value instanceof Tool tool) {
                for (Tool.Rule rule : tool.rules()) {
                    float speed = rule.speed().map(s -> s * combinedPower).orElse(combinedPower);
                    toolRulesOut.add(new Tool.Rule(rule.blocks(), Optional.of(speed), rule.correctForDrops()));
                }
            } else if (type == DataComponents.ATTRIBUTE_MODIFIERS && value instanceof ItemAttributeModifiers(
                    List<ItemAttributeModifiers.Entry> modifiers
            )) {
                for (ItemAttributeModifiers.Entry modEntry : modifiers) {
                    AttributeModifier mod = modEntry.modifier();
                    double amount = mod.amount();

                    if (mod.operation() == AttributeModifier.Operation.ADD_VALUE && amount > 0.0) {
                        amount *= combinedPower;
                    }

                    AttributeKey key = new AttributeKey(modEntry.attribute(), mod.operation(), modEntry.slot());
                    attrGroupsOut
                            .computeIfAbsent(key, k -> new AttributeAccumulator(mod.id()))
                            .add(amount);
                }
            } else if (type == DataComponents.ENCHANTMENTS && value instanceof ItemEnchantments enchantments) {
                for (Object2IntMap.Entry<Holder<Enchantment>> entry1 : enchantments.entrySet()) {
                    int level = entry1.getIntValue();
                    Holder<Enchantment> enchantment = entry1.getKey();

                    enchantmentsOut.set(enchantment, enchantmentsOut.getLevel(enchantment) + level);
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
