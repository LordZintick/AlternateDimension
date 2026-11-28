package com.github.lordzintick.modular;

import com.github.lordzintick.init.AlternateDimensionModComponents;
import com.github.lordzintick.item.PermanentCurioEffectsComponent;
import com.github.lordzintick.item.backpack.BackpackInventoryComponent;
import com.github.lordzintick.item.backpack.BackpackTooltipComponent;
import com.github.lordzintick.item.jetpack.JetpackComponent;
import com.github.lordzintick.item.jetpack.JetpackFuelComponent;
import com.github.lordzintick.modular.parts.GearMaterial;
import com.github.lordzintick.modular.parts.GearPartType;
import com.github.lordzintick.modular.parts.GearPartsComponent;
import com.github.lordzintick.modular.util.ComponentMerger;
import com.github.lordzintick.modular.util.NameAffix;
import com.lordkittycat.alternate_dimension.AlternateDimensionMod;
import com.mojang.authlib.minecraft.client.MinecraftClient;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.tooltip.BundleTooltip;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.BundleContents;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jspecify.annotations.Nullable;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class ModularItem extends Item implements ICurioItem {
    public ModularItem(Properties properties) {
        super(properties
                .component(DataComponents.TOOLTIP_DISPLAY, new TooltipDisplay(false, new LinkedHashSet<>(
                        Set.of(DataComponents.ATTRIBUTE_MODIFIERS, DataComponents.ENCHANTMENTS))))
                .component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, false));
    }

    public static Predicate<ItemStack> matches(ItemStack stack) {
        return otherStack -> {
            GearPartsComponent parts = otherStack.get(AlternateDimensionModComponents.GEAR_PARTS);
            GearPartsComponent thisParts = stack.get(AlternateDimensionModComponents.GEAR_PARTS);
            if (thisParts == null || parts == null) return false;
            List<GearPartType> otherPartList = parts.parts().keySet().stream().map(Holder::value).toList();
            return thisParts.parts().keySet().stream()
                    .allMatch(type -> otherPartList.contains(type.value()));
        };
    }

    public static boolean isHoeTool(ItemStack stack) {
        Tool tool = stack.get(DataComponents.TOOL);
        if (tool == null) return false;

        for (Tool.Rule rule : tool.rules()) {
            if (rule.correctForDrops().orElse(true)) {
                if (rule.blocks() instanceof HolderSet.Named<Block> named
                        && named.key().equals(BlockTags.MINEABLE_WITH_HOE)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void inventoryTick(ItemStack itemStack, ServerLevel level, Entity owner, @Nullable EquipmentSlot slot) {
        GearPartsComponent gearParts = itemStack.get(AlternateDimensionModComponents.GEAR_PARTS);
        if (gearParts == null || gearParts.parts().isEmpty()) {
            itemStack.setCount(0);
        }
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (stack.has(AlternateDimensionModComponents.PERMANENT_EFFECTS)) {
            PermanentCurioEffectsComponent comp = stack.get(AlternateDimensionModComponents.PERMANENT_EFFECTS);
            assert comp != null;
            comp.apply(stack, slotContext.entity());
        }
    }

    @Override
    public List<Component> getSlotsTooltip(List<Component> tooltips, TooltipContext context, ItemStack stack) {
        return List.of();
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

            if (partsComp.parts().isEmpty()) {
                stack.shrink(1);
            }
        }

        return 0;
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        GearPartsComponent parts = stack.get(AlternateDimensionModComponents.GEAR_PARTS);
        return parts != null &&
                (parts.parts().size() > 1 || parts.getTotalDurability() < parts.getTotalMaxDurability() - 1);
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

    private void showDurability(GearPartsComponent partsComp, Consumer<Component> tooltip) {
        int maxDurability = partsComp.getTotalMaxDurability();
        int durability = partsComp.getTotalDurability();
        tooltip.accept(Component.translatable("tooltip.alternate_dimension.durability", durability, maxDurability)
                .withStyle(ChatFormatting.GRAY));
    }

    @Override
    public Optional<TooltipComponent> getTooltipImage(ItemStack stack) {
        BackpackInventoryComponent backpack = stack.get(AlternateDimensionModComponents.BACKPACK_INVENTORY);
        if (backpack == null || backpack.occupiedSlots() <= 0) return Optional.empty();
        if (Minecraft.getInstance().hasControlDown()) {
            return Optional.of(new BackpackTooltipComponent(backpack));
        } else {
            return Optional.empty();
        }
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

            if (partsComp.getTotalDurability() < partsComp.getTotalMaxDurability()) {
                showDurability(partsComp, tooltip);
            }

            addComponentTraits(stack, tooltip, sortedParts);
            return;
        }

        BackpackInventoryComponent backpack = stack.get(AlternateDimensionModComponents.BACKPACK_INVENTORY);
        if (backpack != null && !Minecraft.getInstance().hasControlDown()) {
            tooltip.accept(Component.translatable("tooltip.alternate_dimension.hold_ctl")
                    .withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.ITALIC));
        }

        showDurability(partsComp, tooltip);

        JetpackComponent jetpack = stack.get(AlternateDimensionModComponents.JETPACK);
        if (jetpack != null) {
            JetpackFuelComponent fuel = stack.get(AlternateDimensionModComponents.JETPACK_FUEL);
            if (fuel != null) {
                tooltip.accept(Component.translatable("tooltip.alternate_dimension.jetpack_fuel", fuel.fuel(), jetpack.fuelCapacity())
                        .withStyle(ChatFormatting.GRAY));
            }

            if (Minecraft.getInstance().hasAltDown()) {
                tooltip.accept(Component.translatable("tooltip.alternate_dimension.jetpack_stats_header")
                        .withStyle(ChatFormatting.GRAY));

                tooltip.accept(Component.translatable("tooltip.alternate_dimension.jetpack_vertical_speed", jetpack.verticalSpeed())
                        .withStyle(ChatFormatting.GRAY));
                tooltip.accept(Component.translatable("tooltip.alternate_dimension.jetpack_horizontal_speed", jetpack.horizontalSpeed())
                        .withStyle(ChatFormatting.GRAY));
                tooltip.accept(Component.translatable("tooltip.alternate_dimension.jetpack_hover_speed", jetpack.hoverDescentSpeed())
                        .withStyle(ChatFormatting.GRAY));
                tooltip.accept(Component.translatable("tooltip.alternate_dimension.jetpack_fuel_tag", jetpack.fuelTag().location().toString())
                        .withStyle(ChatFormatting.GRAY));
                tooltip.accept(Component.translatable("tooltip.alternate_dimension.jetpack_fuel_capacity", jetpack.fuelCapacity())
                        .withStyle(ChatFormatting.GRAY));
                tooltip.accept(Component.translatable("tooltip.alternate_dimension.jetpack_fuel_per_item", jetpack.fuelPerItem())
                        .withStyle(ChatFormatting.GRAY));
                tooltip.accept(Component.translatable("tooltip.alternate_dimension.jetpack_fuel_usage", jetpack.fuelUsage())
                        .withStyle(ChatFormatting.GRAY));
            } else {
                tooltip.accept(Component.translatable("tooltip.alternate_dimension.hold_alt")
                        .withStyle(ChatFormatting.DARK_GRAY));
            }
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

        addComponentTraits(stack, tooltip, sortedParts);

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

    private void addComponentTraits(
            ItemStack stack,
            Consumer<Component> tooltip,
            List<GearPartsComponent.MaterialEntry> sortedParts
    ) {
        tooltip.accept(Component.translatable("tooltip.alternate_dimension.traits_prefix").withStyle(ChatFormatting.GRAY));
        String indent = " • ";
        ArrayList<Component> traitDescriptions = new ArrayList<>(sortedParts.stream()
                .flatMap(e -> e.getValue().value().traitDescriptions(stack, false).stream())
                .toList());
        if (traitDescriptions.isEmpty()) {
            tooltip.accept(Component.translatable("tooltip.alternate_dimension.no_traits").withStyle(ChatFormatting.GRAY));
        }
        Set<String> seenTraits = new HashSet<>();
        traitDescriptions.forEach(desc -> {
            if (!seenTraits.add(desc.getString())) return;
            tooltip.accept(Component.literal(indent).withStyle(ChatFormatting.DARK_GRAY)
                    .append(desc));
        });
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
        Set<String> seenTraits = new HashSet<>();
        traits = traits.stream()
                .filter(comp -> seenTraits.add(comp.getString()))
                .toList();

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
