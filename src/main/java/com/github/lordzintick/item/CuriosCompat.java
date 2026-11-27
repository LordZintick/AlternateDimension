package com.github.lordzintick.item;

import com.github.lordzintick.init.AlternateDimensionModComponents;
import com.github.lordzintick.modular.parts.GearPartsComponent;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModItems;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import top.theillusivec4.curios.api.CuriosCapability;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.List;

public class CuriosCompat {
    public static void init(IEventBus modEventBus) {
        modEventBus.addListener(CuriosCompat::onRegisterCapabilities);
    }

    private static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.registerItem(
                CuriosCapability.ITEM,
                (stack, context) -> new ModularCurioWrapper(stack),
                AlternateDimensionModItems.MODULAR_ITEM.get()
        );
    }

    private record ModularCurioWrapper(ItemStack stack) implements ICurio {
        @Override
        public ItemStack getStack() {
            return stack;
        }

        @Override
        public boolean canEquip(SlotContext slotContext) {
            GearPartsComponent parts = stack.get(AlternateDimensionModComponents.GEAR_PARTS.get());
            if (parts == null || parts.parts().size() <= 1) {
                return false;
            }
            String targetSlot = stack.get(AlternateDimensionModComponents.CURIO_SLOT.get());
            return targetSlot != null && targetSlot.equals(slotContext.identifier());
        }

        @Override
        public boolean canEquipFromUse(SlotContext slotContext) {
            return canEquip(slotContext);
        }

        @Override
        public List<Component> getSlotsTooltip(List<Component> tooltips, Item.TooltipContext context) {
            return List.of();
        }

        @Override
        public List<Component> getAttributesTooltip(List<Component> tooltips, Item.TooltipContext context) {
            return List.of();
        }

        @Override
        public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, Identifier id) {
            Multimap<Holder<Attribute>, AttributeModifier> map = HashMultimap.create();
            ItemAttributeModifiers mods = stack.get(DataComponents.ATTRIBUTE_MODIFIERS);
            if (mods == null) return map;

            for (ItemAttributeModifiers.Entry entry : mods.modifiers()) {
                Identifier slotScopedId = Identifier.fromNamespaceAndPath(
                        id.getNamespace(),
                        id.getPath() + "/" + entry.modifier().id().getNamespace() + "_" + entry.modifier().id().getPath()
                );
                AttributeModifier scopedMod = new AttributeModifier(
                        slotScopedId,
                        entry.modifier().amount(),
                        entry.modifier().operation()
                );
                map.put(entry.attribute(), scopedMod);
            }
            return map;
        }

        @Override
        public void curioTick(SlotContext slotContext) {
            if (slotContext.entity().level() instanceof ServerLevel serverLevel) {
                stack.inventoryTick(serverLevel, slotContext.entity(), null);
            }
        }
    }
}
