package com.github.lordzintick.modular.util;

import com.github.lordzintick.init.AlternateDimensionModComponents;
import com.github.lordzintick.modular.parts.GearMaterial;
import com.github.lordzintick.modular.parts.GearPartType;
import com.github.lordzintick.modular.parts.GearPartsComponent;
import com.github.lordzintick.modular.parts.PartComponent;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
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
import net.minecraft.world.level.block.Block;

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
        applyMergedComponents(stack, gearParts, List.of());
    }

    public static void applyMergedComponents(
            ItemStack stack,
            GearPartsComponent gearParts,
            List<ItemEnchantments> extraEnchantmentsToMerge
    ) {
        stack.set(AlternateDimensionModComponents.GEAR_PARTS.get(), gearParts);

        ItemEnchantments existingEnchants = stack.getOrDefault(DataComponents.ENCHANTMENTS, ItemEnchantments.EMPTY);
        clearSynthesizedComponents(stack);

        ItemEnchantments.Mutable mergedEnchants = new ItemEnchantments.Mutable(existingEnchants);
        for (ItemEnchantments extra : extraEnchantmentsToMerge) {
            mergeEnchantments(mergedEnchants, extra);
        }

        List<Tool.Rule> denialRules = new ArrayList<>();
        Map<HolderSet<Block>, Tool.Rule> positiveRules = new LinkedHashMap<>();
        Map<AttributeKey, AttributeAccumulator> attributeGroups = new LinkedHashMap<>();

        for (var entry : gearParts.materialsByZIndex()) {
            GearPartType part = entry.getKey().value();
            GearMaterial mat = entry.getValue().value();

            float combinedPower = part.powerFactor() * mat.powerFactor();

            Optional<DataComponentPatch> patchOpt = mat.components().stream()
                    .filter(comp -> comp.meetsPartTypes(gearParts))
                    .map(PartComponent::dataComponentPatch)
                    .findFirst();

            patchOpt.ifPresent(patch ->
            applyPatchSafely(
                    stack,
                    patch,
                    denialRules,
                    positiveRules,
                    attributeGroups,
                    mergedEnchants,
                    combinedPower,
                    part.contributeMaterialName()
            ));

            applyPatchSafely(
                    stack,
                    part.components(),
                    denialRules,
                    positiveRules,
                    attributeGroups,
                    mergedEnchants,
                    combinedPower,
                    true
            );
        }

        if (!denialRules.isEmpty() || !positiveRules.isEmpty()) {
            List<Tool.Rule> finalRules = new ArrayList<>(denialRules.size() + positiveRules.size());
            finalRules.addAll(denialRules);
            finalRules.addAll(positiveRules.values());
            stack.set(DataComponents.TOOL, new Tool(finalRules, 1.0f, 1, true));
        }

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

        ItemEnchantments finalEnchants = mergedEnchants.toImmutable();
        if (!finalEnchants.isEmpty()) {
            stack.set(DataComponents.ENCHANTMENTS, finalEnchants);
        }
    }

    private static void clearSynthesizedComponents(ItemStack stack) {
        stack.remove(DataComponents.TOOL);
        stack.remove(DataComponents.ATTRIBUTE_MODIFIERS);
        stack.remove(DataComponents.EQUIPPABLE);
        stack.remove(DataComponents.FOOD);
        stack.remove(DataComponents.CONSUMABLE);
        stack.remove(DataComponents.DAMAGE_RESISTANT);
        stack.remove(DataComponents.GLIDER);
        stack.remove(DataComponents.MAX_DAMAGE);
        stack.remove(AlternateDimensionModComponents.CURIO_SLOT);
        stack.remove(AlternateDimensionModComponents.PERMANENT_EFFECTS);
    }

    public static void applyPatchSafely(
            ItemStack stack,
            DataComponentPatch patch,
            List<Tool.Rule> denialRulesOut,
            Map<HolderSet<Block>, Tool.Rule> positiveRulesOut,
            Map<AttributeKey, AttributeAccumulator> attrGroupsOut,
            ItemEnchantments.Mutable enchantsOut,
            float combinedPower,
            boolean allowTierDenialRules
    ) {
        for (var entry : patch.entrySet()) {
            DataComponentType<?> type = entry.getKey();
            Optional<?> valueOpt = entry.getValue();
            if (valueOpt.isEmpty()) continue;

            Object value = valueOpt.get();

            if (type == DataComponents.TOOL && value instanceof Tool tool) {
                for (Tool.Rule rule : tool.rules()) {
                    boolean isCorrect = rule.correctForDrops().orElse(true);
                    if (!isCorrect) {
                        if (allowTierDenialRules) {
                            denialRulesOut.add(rule);
                        }
                    } else {
                        float scaledSpeed = rule.speed().map(s -> s * combinedPower).orElse(combinedPower);
                        Tool.Rule newRule = new Tool.Rule(rule.blocks(), Optional.of(scaledSpeed), Optional.of(true));

                        // Deduplicate identical block sets, keeping the faster mining speed
                        positiveRulesOut.merge(rule.blocks(), newRule, (existing, incoming) -> {
                            float existingSpeed = existing.speed().orElse(0.0f);
                            float incomingSpeed = incoming.speed().orElse(0.0f);
                            return incomingSpeed > existingSpeed ? incoming : existing;
                        });
                    }
                }
            } else if (type == DataComponents.ATTRIBUTE_MODIFIERS && value instanceof ItemAttributeModifiers mods) {
                for (ItemAttributeModifiers.Entry modEntry : mods.modifiers()) {
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
            } else if (type == DataComponents.ENCHANTMENTS && value instanceof ItemEnchantments patchEnchants) {
                mergeEnchantments(enchantsOut, patchEnchants);
            } else {
                setUnchecked(stack, type, value);
            }
        }
    }

    private static void mergeEnchantments(ItemEnchantments.Mutable target, ItemEnchantments source) {
        for (var entry : source.entrySet()) {
            Holder<Enchantment> enchant = entry.getKey();
            int incomingLevel = entry.getIntValue();
            int currentLevel = target.getLevel(enchant);
            if (incomingLevel > currentLevel) {
                target.set(enchant, incomingLevel);
            }
        }
    }
    @SuppressWarnings("unchecked")
    private static <T> void setUnchecked(ItemStack stack, DataComponentType type, Object value) {
        stack.set(type, (T) value);
    }
}
