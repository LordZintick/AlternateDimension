package com.github.lordzintick.modular;

import com.github.lordzintick.init.AlternateDimensionModComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.item.component.TooltipDisplay;

import java.util.*;
import java.util.function.Consumer;

public class ModularItem extends Item {

    public ModularItem(Properties properties) {
        super(properties);
    }

    @Override
    public Component getName(ItemStack stack) {
        GearPartsComponent partsComp = stack.get(AlternateDimensionModComponents.GEAR_PARTS.get());
        if (partsComp == null || partsComp.parts().isEmpty()) {
            return super.getName(stack);
        }

        // Case 1: Standalone 1-part item (e.g., "Diamond Pickaxe Head")
        if (partsComp.parts().size() == 1) {
            var entry = partsComp.parts().entrySet().iterator().next();
            GearPartType part = entry.getKey().value();
            GearMaterial mat = entry.getValue().value();

            Component matComp = Component.translatable(mat.nameKey()).withColor(mat.color());
            Component partComp = Component.translatable(part.partItemNameKey());
            return Component.translatable("item.yourmod.part_format", matComp, partComp);
        }

        // Case 2: Assembled gear with priority-sorted prefixes and suffixes
        record ResolvedToken(int priority, Component text) {}
        List<ResolvedToken> prefixes = new ArrayList<>();
        List<ResolvedToken> suffixes = new ArrayList<>();
        Set<String> seenKeys = new HashSet<>();

        for (Map.Entry<Holder<GearPartType>, Holder<GearMaterial>> entry : partsComp.parts().entrySet()) {
            GearPartType part = entry.getKey().value();
            GearMaterial mat = entry.getValue().value();

            // 1. Collect the Part's own affix (e.g., "Pickaxe" suffix, or "%s-Coated" prefix)
            part.nameAffix().ifPresent(affix -> {
                Component matName = Component.translatable(mat.nameKey());
                MutableComponent text = affix.takesMaterial()
                        ? Component.translatable(affix.translationKey(), matName)
                        : Component.translatable(affix.translationKey());

                if (affix.tintWithMaterial()) {
                    text.withColor(mat.color());
                }

                String dedupeKey = affix.translationKey() + ":" + (affix.takesMaterial() ? mat.nameKey() : "");
                if (seenKeys.add(dedupeKey)) {
                    (affix.position() == NameAffix.AffixPosition.PREFIX ? prefixes : suffixes)
                            .add(new ResolvedToken(affix.priority(), text));
                }
            });

            // 2. If this part exposes its material's raw name (e.g., Pickaxe Head -> "Diamond"), collect Material affix
            if (part.contributeMaterialName()) {
                mat.nameAffix().ifPresent(affix -> {
                    MutableComponent text = Component.translatable(affix.translationKey());
                    if (affix.tintWithMaterial()) {
                        text.withColor(mat.color());
                    }

                    if (seenKeys.add(affix.translationKey())) {
                        (affix.position() == NameAffix.AffixPosition.PREFIX ? prefixes : suffixes)
                                .add(new ResolvedToken(affix.priority(), text));
                    }
                });
            }
        }

        prefixes.sort(Comparator.comparingInt(ResolvedToken::priority));
        suffixes.sort(Comparator.comparingInt(ResolvedToken::priority));

        MutableComponent fullName = Component.empty();
        List<ResolvedToken> allTokens = new ArrayList<>(prefixes.size() + suffixes.size());
        allTokens.addAll(prefixes);
        allTokens.addAll(suffixes);

        for (int i = 0; i < allTokens.size(); i++) {
            if (i > 0) fullName.append(Component.literal(" "));
            fullName.append(allTokens.get(i).text());
        }

        return fullName.getString().isEmpty() ? super.getName(stack) : fullName;
    }

    @Override
    public void appendHoverText(
            ItemStack stack,
            TooltipContext context,
            TooltipDisplay display,
            Consumer<Component> tooltip,
            TooltipFlag flag
    ) {
        GearPartsComponent partsComp = stack.get(AlternateDimensionModComponents.GEAR_PARTS.get());
        if (partsComp == null || partsComp.parts().isEmpty()) return;

        var sortedParts = partsComp.sortedByZIndex();

        // Standalone Part Preview (1 Part)
        if (sortedParts.size() == 1) {
            var entry = sortedParts.get(0);
            GearPartType part = entry.getKey().value();
            GearMaterial mat = entry.getValue().value();

            tooltip.accept(Component.translatable("tooltip.yourmod.part_stats",
                    part.baseDurability(),
                    String.format("%.1fx", part.speedMultiplier()),
                    String.format("%.1fx", mat.durabilityMultiplier())
            ).withStyle(ChatFormatting.GRAY));

            appendComponentTraits(part, mat, tooltip, true);
            return;
        }

        // Assembled Gear Stat Summary
        int maxDamage = stack.getOrDefault(DataComponents.MAX_DAMAGE, 0);
        int currentDamage = stack.getOrDefault(DataComponents.DAMAGE, 0);
        if (maxDamage > 0) {
            tooltip.accept(Component.translatable("tooltip.alternate_dimension.durability", (maxDamage - currentDamage), maxDamage)
                    .withStyle(ChatFormatting.GRAY));
        }

        Tool toolComp = stack.get(DataComponents.TOOL);
        if (toolComp != null && !toolComp.rules().isEmpty()) {
            float maxSpeed = toolComp.rules().stream()
                    .map(r -> r.speed().orElse(1.0f))
                    .max(Float::compare)
                    .orElse(1.0f);
            tooltip.accept(Component.translatable("tooltip.alternate_dimension.mining_speed", String.format("%.1f", maxSpeed))
                    .withStyle(ChatFormatting.DARK_AQUA));
        }

        // Active Component Traits
        appendAutoDetectedComponentLabels(stack, tooltip);

        // Construction Breakdown (Hold Shift)
        if (Minecraft.getInstance().hasShiftDown()) {
            tooltip.accept(Component.empty());
            tooltip.accept(Component.translatable("tooltip.alternate_dimension.construction_header").withStyle(ChatFormatting.GOLD));

            for (var entry : sortedParts) {
                GearPartType part = entry.getKey().value();
                GearMaterial mat = entry.getValue().value();

                Component partLabel = Component.translatable(part.partItemNameKey()).withStyle(ChatFormatting.GRAY);
                Component matLabel = Component.translatable(mat.nameKey()).withColor(mat.color());

                tooltip.accept(Component.literal(" • ").withStyle(ChatFormatting.DARK_GRAY)
                        .append(partLabel)
                        .append(Component.literal(": ").withStyle(ChatFormatting.DARK_GRAY))
                        .append(matLabel));

                appendComponentTraits(part, mat, tooltip, false);
            }
        } else {
            tooltip.accept(Component.translatable("tooltip.alternate_dimension.hold_shift")
                    .withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.ITALIC));
        }
    }

    private void appendAutoDetectedComponentLabels(ItemStack stack, Consumer<Component> tooltip) {
        List<Component> badges = new ArrayList<>();

        FoodProperties food = stack.get(DataComponents.FOOD);
        if (food != null) {
            badges.add(Component.translatable("trait.alternate_dimension.edible", food.nutrition())
                    .withStyle(ChatFormatting.GREEN));
        }
        if (stack.has(DataComponents.DAMAGE_RESISTANT)) {
            badges.add(Component.translatable("trait.alternate_dimension.fireproof")
                    .withStyle(ChatFormatting.GOLD));
        }
        if (stack.has(DataComponents.GLIDER)) {
            badges.add(Component.translatable("trait.alternate_dimension.glider")
                    .withStyle(ChatFormatting.AQUA));
        }

        if (!badges.isEmpty()) {
            MutableComponent line = Component.translatable("tooltip.alternate_dimension.traits_prefix").withStyle(ChatFormatting.GRAY);
            for (int i = 0; i < badges.size(); i++) {
                if (i > 0) line.append(Component.literal(", ").withStyle(ChatFormatting.GRAY));
                line.append(badges.get(i));
            }
            tooltip.accept(line);
        }
    }

    private void appendComponentTraits(
            GearPartType part,
            GearMaterial mat,
            Consumer<Component> tooltip,
            boolean isStandalonePart
    ) {
        String indent = isStandalonePart ? " " : "   ↳ ";
        for (String descKey : mat.traitDescriptions()) {
            tooltip.accept(Component.literal(indent).withStyle(ChatFormatting.DARK_GRAY)
                    .append(Component.translatable(descKey).withStyle(ChatFormatting.BLUE)));
        }
        for (String descKey : part.traitDescriptions()) {
            tooltip.accept(Component.literal(indent).withStyle(ChatFormatting.DARK_GRAY)
                    .append(Component.translatable(descKey).withStyle(ChatFormatting.LIGHT_PURPLE)));
        }
    }
}
