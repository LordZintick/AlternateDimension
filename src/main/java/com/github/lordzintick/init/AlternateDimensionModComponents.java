package com.github.lordzintick.init;

import com.github.lordzintick.item.PermanentEffectsComponent;
import com.github.lordzintick.item.PhotosyntheticComponent;
import com.github.lordzintick.item.SparkingComponent;
import com.github.lordzintick.item.ring.RingEffectComponent;
import com.github.lordzintick.modular.BlueprintDataComponent;
import com.github.lordzintick.modular.GearPartsComponent;
import com.github.lordzintick.modular.RepairKitDataComponent;
import com.lordkittycat.alternate_dimension.AlternateDimensionMod;
import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
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
    public static final Supplier<DataComponentType<String>> CURIO_SLOT = DATA_COMPONENTS.registerComponentType("curio_slot", builder ->
            builder.persistent(Codec.STRING).networkSynchronized(ByteBufCodecs.STRING_UTF8));
    public static final Supplier<DataComponentType<RepairKitDataComponent>> REPAIR_KIT_DATA = DATA_COMPONENTS.registerComponentType("repair_kit_data", builder ->
            builder.persistent(RepairKitDataComponent.CODEC).networkSynchronized(RepairKitDataComponent.STREAM_CODEC));


    public static final Supplier<DataComponentType<PhotosyntheticComponent>> PHOTOSYNTHETIC = DATA_COMPONENTS.registerComponentType("photosynthetic", builder ->
            builder.persistent(PhotosyntheticComponent.CODEC).networkSynchronized(PhotosyntheticComponent.STREAM_CODEC));
    public static final Supplier<DataComponentType<SparkingComponent>> SPARKING = DATA_COMPONENTS.registerComponentType("sparking", builder ->
            builder.persistent(SparkingComponent.CODEC));
    public static final Supplier<DataComponentType<PermanentEffectsComponent>> PERMANENT_EFFECTS = DATA_COMPONENTS.registerComponentType("permanent_effects", builder ->
            builder.persistent(PermanentEffectsComponent.CODEC));

    public static void register(IEventBus eventBus) {
        DATA_COMPONENTS.register(eventBus);
    }
}
