package com.github.lordzintick.modular;

import com.github.lordzintick.init.AlternateDimensionModComponents;
import mezz.jei.api.helpers.IColorHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.ARGB;
import net.minecraft.util.ColorRGBA;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.Nullable;

import java.util.*;
import java.util.function.Consumer;

public class ModularItem extends Item {
    public ModularItem(Properties properties) {
        super(properties
                .component(DataComponents.TOOLTIP_DISPLAY, new TooltipDisplay(false, new LinkedHashSet<>(
                        Set.of(DataComponents.ATTRIBUTE_MODIFIERS, DataComponents.ENCHANTMENTS))))
                .component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, false));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        Consumable consumable = stack.get(DataComponents.CONSUMABLE);

        if (consumable != null) {
            ItemStack dummyCopy = stack.copy();
            consumable.onConsume(level, livingEntity, dummyCopy);
        }

        if (level instanceof ServerLevel serverLevel) {
            if (!(livingEntity instanceof Player player && player.hasInfiniteMaterials())) {
                EquipmentSlot slot = livingEntity.getUsedItemHand() == net.minecraft.world.InteractionHand.MAIN_HAND
                        ? EquipmentSlot.MAINHAND
                        : EquipmentSlot.OFFHAND;

                stack.hurtAndBreak(1, serverLevel, livingEntity, item -> livingEntity.onEquippedItemBroken(item, slot));
            }
        }

        return stack;
    }

    @Override
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, @Nullable T entity, Consumer<Item> onBroken) {
        GearPartsComponent partsComp = stack.get(AlternateDimensionModComponents.GEAR_PARTS);
        if (partsComp != null) {
            partsComp.damageBy(stack, amount);
        }

        return 0;
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        return stack.has(AlternateDimensionModComponents.GEAR_PARTS) && stack.get(AlternateDimensionModComponents.GEAR_PARTS).parts().size() > 1;
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        GearPartsComponent partsComp = stack.get(AlternateDimensionModComponents.GEAR_PARTS);
        if (partsComp != null) {
            float ratio = (float) partsComp.getTotalDurability() / (float) partsComp.getTotalMaxDurability();
            return Mth.clamp(Math.round(ratio * 13.0f), 0, 13);
        }
        return 6;
    }

    @Override
    public int getBarColor(ItemStack stack) {
        GearPartsComponent partsComp = stack.get(AlternateDimensionModComponents.GEAR_PARTS);
        if (partsComp == null || partsComp.parts().isEmpty()) {
            return super.getBarColor(stack);
        }

        int totalR = 0;
        int totalG = 0;
        int totalB = 0;
        int count = 0;

        for (var partInstance : partsComp.parts().values()) {
            int rgb = partInstance.material().value().color();
            totalR += (rgb >> 16) & 0xFF;
            totalG += (rgb >> 8) & 0xFF;
            totalB += rgb & 0xFF;
            count++;
        }

        if (count == 0) {
            return super.getBarColor(stack);
        }

        int avgR = totalR / count;
        int avgG = totalG / count;
        int avgB = totalB / count;

        return (avgR << 16) | (avgG << 8) | avgB;
    }

    @Override
    public Component getName(ItemStack stack) {
        GearPartsComponent partsComp = stack.get(AlternateDimensionModComponents.GEAR_PARTS);
        if (partsComp == null || partsComp.parts().isEmpty()) {
            return super.getName(stack);
        }

        if (partsComp.parts().size() == 1) {
            var entry = partsComp.parts().entrySet().iterator().next();
            GearPartType part = entry.getKey().value();
            GearMaterial mat = entry.getValue().material().value();

            Component matComp = Component.translatable(mat.nameKey()).withColor(mat.color());
            Component partComp = Component.translatable(part.partItemNameKey());
            return Component.translatable("item.alternate_dimension.part_format", matComp, partComp);
        }

        record ResolvedToken(int priority, Component text) {}
        List<ResolvedToken> prefixes = new ArrayList<>();
        List<ResolvedToken> suffixes = new ArrayList<>();
        Set<String> seenKeys = new HashSet<>();

        for (Map.Entry<Holder<GearPartType>, GearPartsComponent.GearPart> entry : partsComp.parts().entrySet()) {
            GearPartType part = entry.getKey().value();
            GearMaterial mat = entry.getValue().material().value();

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

        var sortedParts = partsComp.materialsByZIndex();

        if (sortedParts.size() == 1) {
            var entry = sortedParts.getFirst();
            GearPartType part = entry.getKey().value();
            GearMaterial mat = entry.getValue().value();

            tooltip.accept(Component.translatable("tooltip.alternate_dimension.part_stats",
                    part.baseDurability(),
                    String.format("%.1fx", part.powerFactor()),
                    String.format("%.1fx", mat.durabilityMultiplier())
            ).withStyle(ChatFormatting.GRAY));

            tooltip.accept(Component.translatable("tooltip.alternate_dimension.traits_prefix").withStyle(ChatFormatting.GRAY));
            appendComponentTraits(stack, mat, tooltip);
            return;
        }

        int maxDurability = partsComp.getTotalMaxDurability();
        int durability = partsComp.getTotalDurability();
        tooltip.accept(Component.translatable("tooltip.alternate_dimension.durability", durability, maxDurability)
                .withStyle(ChatFormatting.GRAY));

        Tool toolComp = stack.get(DataComponents.TOOL);
        if (toolComp != null && !toolComp.rules().isEmpty()) {
            float maxSpeed = toolComp.rules().stream()
                    .map(r -> r.speed().orElse(1.0f))
                    .max(Float::compare)
                    .orElse(1.0f);
            tooltip.accept(Component.translatable("tooltip.alternate_dimension.mining_speed", String.format("%.1f", maxSpeed))
                    .withStyle(ChatFormatting.DARK_AQUA));
        }

        tooltip.accept(Component.translatable("tooltip.alternate_dimension.traits_prefix").withStyle(ChatFormatting.GRAY));
        for (var entry : sortedParts) {
            GearMaterial mat = entry.getValue().value();
            appendComponentTraits(stack, mat, tooltip);
        }

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
                        .append(matLabel)
                        .append(Component.literal(" - ").withStyle(ChatFormatting.DARK_GRAY))
                        .append(Component.literal(entry.getDurability() + "/" + entry.getMaxDurability()).withStyle(ChatFormatting.WHITE, ChatFormatting.ITALIC)));

                addPartTraits(stack, mat, part, tooltip);
            }
        } else {
            tooltip.accept(Component.translatable("tooltip.alternate_dimension.hold_shift")
                    .withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.ITALIC));
        }
    }

    private void appendComponentTraits(
            ItemStack stack,
            GearMaterial mat,
            Consumer<Component> tooltip
    ) {
        String indent = " • ";
        for (Component entry : mat.traitDescriptions(stack, false)) {
            tooltip.accept(Component.literal(indent).withStyle(ChatFormatting.DARK_GRAY)
                    .append(entry));
        }
    }

    private void addPartTraits(
            ItemStack stack,
            GearMaterial mat,
            GearPartType part,
            Consumer<Component> tooltip
    ) {
        MutableComponent line = Component.literal("  -> Contributes Traits: ").withStyle(ChatFormatting.ITALIC, ChatFormatting.DARK_GRAY);
        List<Component> traits = mat.traitDescriptions(stack, false);
        traits.addAll(part.traitDescriptions().stream()
                .map(Component::translatable)
                .toList());

        for (int i = 0; i < traits.size(); i++) {
            Component trait = traits.get(i);
            if (i < traits.size() - 1) {
                line.append(trait.copy().append(", "));
            } else {
                line.append(trait);
            }
        }
        tooltip.accept(line);
    }
}
