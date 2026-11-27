package com.github.lordzintick.modular.blueprint;

import com.github.lordzintick.init.AlternateDimensionModComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;

import java.util.Optional;
import java.util.function.Consumer;

public class BlueprintItem extends Item {
    public BlueprintItem(Properties properties) {
        super(properties);
    }

    @Override
    public Component getName(ItemStack itemStack) {
        Optional<BlueprintDataComponent> printData = Optional.ofNullable(itemStack.get(AlternateDimensionModComponents.BLUEPRINT_DATA));
        if (printData.isEmpty())
            return super.getName(itemStack);
        return super.getName(itemStack).copy().append(" [").append(Component.translatable(printData.get().partType().value().partItemNameKey())).append("]");
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        Optional<BlueprintDataComponent> blueprintData = Optional.ofNullable(itemStack.get(AlternateDimensionModComponents.BLUEPRINT_DATA));
        if (blueprintData.isEmpty())
            return;

        builder.accept(Component.translatable("tooltip.alternate_dimension.material_required", blueprintData.get().materialCost()));
    }
}
