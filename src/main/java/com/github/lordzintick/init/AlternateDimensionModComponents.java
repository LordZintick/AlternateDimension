package com.github.lordzintick.init;

import com.github.lordzintick.item.ring.RingEffectComponent;
import com.github.lordzintick.modular.BlueprintDataComponent;
import com.github.lordzintick.modular.GearPartsComponent;
import com.lordkittycat.alternate_dimension.AlternateDimensionMod;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class AlternateDimensionModComponents {
    public static final DeferredRegister.DataComponents DATA_COMPONENTS = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, AlternateDimensionMod.MODID);

    public static final Supplier<DataComponentType<RingEffectComponent>> RING_EFFECT = DATA_COMPONENTS.registerComponentType("ring_effect", builder ->
            builder.persistent(RingEffectComponent.CODEC));

    public static final Supplier<DataComponentType<BlueprintDataComponent>> BLUEPRINT_DATA = DATA_COMPONENTS.registerComponentType("blueprint_data", builder ->
            builder.persistent(BlueprintDataComponent.CODEC).networkSynchronized(BlueprintDataComponent.STREAM_CODEC));

    public static final Supplier<DataComponentType<GearPartsComponent>> GEAR_PARTS = DATA_COMPONENTS.registerComponentType("gear_parts", builder ->
            builder.persistent(GearPartsComponent.CODEC).networkSynchronized(GearPartsComponent.STREAM_CODEC));

    public static void register(IEventBus eventBus) {
        DATA_COMPONENTS.register(eventBus);
    }
}
