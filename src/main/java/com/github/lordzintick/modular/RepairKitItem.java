package com.github.lordzintick.modular;

import com.github.lordzintick.init.AlternateDimensionModComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.util.ARGB;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;

import java.util.function.Consumer;

public class RepairKitItem extends Item {
    private final int maxCapacity;
    private final float repairEfficiency;

    public RepairKitItem(Properties properties, int maxCapacity, float repairEfficiency) {
        super(properties.stacksTo(1).component(AlternateDimensionModComponents.REPAIR_KIT_DATA, RepairKitDataComponent.EMPTY));
        this.maxCapacity = maxCapacity;
        this.repairEfficiency = repairEfficiency;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public float getRepairEfficiency() {
        return repairEfficiency;
    }

    @Override
    public Component getName(ItemStack stack) {
        RepairKitDataComponent data = stack.getOrDefault(AlternateDimensionModComponents.REPAIR_KIT_DATA, RepairKitDataComponent.EMPTY);
        if (data.isEmpty()) {
            return super.getName(stack);
        }
        GearMaterial mat = data.material().get().value();
        Component matName = Component.translatable(mat.nameKey()).withColor(mat.color());
        return Component.translatable("item.alternate_dimension.repair_kit.filled_format", matName, super.getName(stack));
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        RepairKitDataComponent data = stack.getOrDefault(AlternateDimensionModComponents.REPAIR_KIT_DATA, RepairKitDataComponent.EMPTY);
        return !data.isEmpty();
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        RepairKitDataComponent data = stack.getOrDefault(AlternateDimensionModComponents.REPAIR_KIT_DATA, RepairKitDataComponent.EMPTY);
        if (data.isEmpty() || this.maxCapacity <= 0) {
            return 0;
        }
        return Math.round(13.0f * Math.clamp((float) data.amount() / (float) this.maxCapacity, 0.0f, 1.0f));
    }

    @Override
    public int getBarColor(ItemStack stack) {
        RepairKitDataComponent data = stack.getOrDefault(AlternateDimensionModComponents.REPAIR_KIT_DATA, RepairKitDataComponent.EMPTY);
        return data.material()
                .map(holder -> holder.value().color() & 0x00FFFFFF)
                .orElse(0xFFFFFF);
    }

    @Override
    public void appendHoverText(
            ItemStack stack,
            TooltipContext context,
            TooltipDisplay display,
            Consumer<Component> tooltip,
            TooltipFlag flag
    ) {
        RepairKitDataComponent data = stack.getOrDefault(AlternateDimensionModComponents.REPAIR_KIT_DATA, RepairKitDataComponent.EMPTY);
        tooltip.accept(Component.translatable("tooltip.alternate_dimension.repair_kit.efficiency", Math.round(repairEfficiency * 100))
                .withStyle(ChatFormatting.DARK_AQUA));

        if (data.isEmpty()) {
            tooltip.accept(Component.translatable("tooltip.alternate_dimension.repair_kit.empty", 0, maxCapacity)
                    .withStyle(ChatFormatting.GRAY));
        } else {
            GearMaterial mat = data.material().get().value();
            tooltip.accept(Component.translatable(
                    "tooltip.alternate_dimension.repair_kit.stored",
                    Component.translatable(mat.nameKey()).withColor(mat.color()),
                    data.amount(),
                    maxCapacity
            ).withStyle(ChatFormatting.GRAY));
        }
    }
}
