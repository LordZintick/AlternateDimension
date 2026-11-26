package com.github.lordzintick.init;

import com.github.lordzintick.modular.*;
import com.lordkittycat.alternate_dimension.AlternateDimensionMod;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterItemModelsEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@EventBusSubscriber(modid = AlternateDimensionMod.MODID, value = Dist.CLIENT)
public class ClientModEvents {
    @SubscribeEvent
    public static void onRegisterItemModels(RegisterItemModelsEvent event) {
        event.register(Identifier.fromNamespaceAndPath(AlternateDimensionMod.MODID, "modular_model"), ModularItemModel.Unbaked.MAP_CODEC);
    }

    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event) {
        RegistryAccess registries = event.getContext().level() != null ? event.getContext().level().registryAccess() : null;
        if (registries == null) return;

        ItemStack stack = event.getItemStack();
        if (stack.isEmpty()) return;

        Optional<Registry<GearMaterial>> matRegistryOpt = registries.lookup(AlternateDimensionModRegistries.MATERIAL_KEY);
        Optional<Registry<GearPartType>> partRegistryOpt = registries.lookup(AlternateDimensionModRegistries.PART_TYPE_KEY);
        if (matRegistryOpt.isEmpty() || partRegistryOpt.isEmpty()) return;

        Optional<Holder.Reference<GearMaterial>> matchedMatOpt = matRegistryOpt.get().listElements()
                .filter(holder -> holder.value().ingredient().test(stack))
                .findFirst();

        if (matchedMatOpt.isEmpty()) return;

        Holder.Reference<GearMaterial> matHolder = matchedMatOpt.get();
        GearMaterial material = matHolder.value();
        List<Component> tooltip = event.getToolTip();

        if (!Minecraft.getInstance().hasShiftDown()) {
            tooltip.add(Component.translatable(
                    "tooltip.alternate_dimension.material_hint",
                    Component.translatable(material.nameKey()).withColor(material.color())
            ).withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.ITALIC));
            return;
        }

        tooltip.add(Component.empty());
        tooltip.add(Component.translatable(
                "tooltip.alternate_dimension.material_header",
                Component.translatable(material.nameKey()).withColor(material.color())
        ).withStyle(ChatFormatting.GOLD));

        tooltip.add(Component.literal(" • ").withStyle(ChatFormatting.DARK_GRAY)
                .append(Component.translatable(
                        "tooltip.alternate_dimension.material_durability",
                        String.format("%.2fx", material.durabilityMultiplier())
                ).withStyle(ChatFormatting.GRAY)));

        tooltip.add(Component.literal(" • ").withStyle(ChatFormatting.DARK_GRAY)
                .append(Component.translatable(
                        "tooltip.alternate_dimension.material_speed",
                        String.format("%.1f", material.powerFactor())
                ).withStyle(ChatFormatting.DARK_AQUA)));

        for(Component entry : material.traitDescriptions(stack, true)) {
            tooltip.add(Component.literal(" • ").append(entry));
        }

        List<Component> allowedPartNames = partRegistryOpt.get().listElements()
                .filter(partHolder -> partHolder.value().allowsMaterial(matHolder))
                .map(partHolder -> (Component) Component.translatable(partHolder.value().partItemNameKey())
                        .withStyle(ChatFormatting.GREEN))
                .toList();

        if (!allowedPartNames.isEmpty()) {
            MutableComponent partsLine = Component.literal(" • ").withStyle(ChatFormatting.DARK_GRAY)
                    .append(Component.translatable("tooltip.alternate_dimension.allowed_parts").withStyle(ChatFormatting.GRAY));

            for (int i = 0; i < allowedPartNames.size(); i++) {
                if (i > 0) partsLine.append(Component.literal(", ").withStyle(ChatFormatting.GRAY));
                partsLine.append(allowedPartNames.get(i));
            }
            tooltip.add(partsLine);
        }
    }
}
