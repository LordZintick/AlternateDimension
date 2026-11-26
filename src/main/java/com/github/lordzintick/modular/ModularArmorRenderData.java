package com.github.lordzintick.modular;

import com.github.lordzintick.init.AlternateDimensionModComponents;
import com.google.common.reflect.TypeToken;
import com.lordkittycat.alternate_dimension.AlternateDimensionMod;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.util.context.ContextKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.renderstate.RegisterRenderStateModifiersEvent;

import java.util.EnumMap;
import java.util.Map;

public class ModularArmorRenderData {
    public static final ContextKey<Map<EquipmentSlot, GearPartsComponent>> KEY =
            new ContextKey<>(Identifier.fromNamespaceAndPath(AlternateDimensionMod.MODID, "modular_armor_parts"));

    public static void onRegisterRenderStateModifiers(RegisterRenderStateModifiersEvent event) {
        event.registerEntityModifier(
                new TypeToken<LivingEntityRenderer<LivingEntity, LivingEntityRenderState, ?>>() {},
                (entity, state) -> {
                    Map<EquipmentSlot, GearPartsComponent> wornParts = new EnumMap<>(EquipmentSlot.class);
                    for (EquipmentSlot slot : new EquipmentSlot[]{
                            EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET
                    }) {
                        ItemStack stack = entity.getItemBySlot(slot);
                        GearPartsComponent comp = stack.get(AlternateDimensionModComponents.GEAR_PARTS.get());
                        if (comp != null && comp.parts().size() > 1) {
                            wornParts.put(slot, comp);
                        }
                    }
                    if (!wornParts.isEmpty()) {
                        state.setRenderData(KEY, wornParts);
                    }
                }
        );
    }
}
