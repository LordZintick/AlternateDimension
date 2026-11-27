package com.github.lordzintick.init;

import com.github.lordzintick.item.HitEffectsComponent;
import com.github.lordzintick.item.PermanentCurioEffectsComponent;
import com.github.lordzintick.item.PhotosyntheticComponent;
import com.github.lordzintick.item.SparkingComponent;
import com.github.lordzintick.item.jetpack.JetpackComponent;
import com.github.lordzintick.item.jetpack.JetpackFuelComponent;
import com.github.lordzintick.modular.blueprint.BlueprintDataComponent;
import com.github.lordzintick.modular.parts.GearPartsComponent;
import com.github.lordzintick.modular.repair_kit.RepairKitDataComponent;
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

    public static final Supplier<DataComponentType<BlueprintDataComponent>> BLUEPRINT_DATA = DATA_COMPONENTS.registerComponentType("blueprint_data", builder ->
            builder.persistent(BlueprintDataComponent.CODEC).networkSynchronized(BlueprintDataComponent.STREAM_CODEC));
    public static final Supplier<DataComponentType<GearPartsComponent>> GEAR_PARTS = DATA_COMPONENTS.registerComponentType("gear_parts", builder ->
            builder.persistent(GearPartsComponent.CODEC).networkSynchronized(GearPartsComponent.STREAM_CODEC));
    public static final Supplier<DataComponentType<String>> CURIO_SLOT = DATA_COMPONENTS.registerComponentType("curio_slot", builder ->
            builder.persistent(Codec.STRING).networkSynchronized(ByteBufCodecs.STRING_UTF8));
    public static final Supplier<DataComponentType<RepairKitDataComponent>> REPAIR_KIT_DATA = DATA_COMPONENTS.registerComponentType("repair_kit_data", builder ->
            builder.persistent(RepairKitDataComponent.CODEC).networkSynchronized(RepairKitDataComponent.STREAM_CODEC));
    public static final Supplier<DataComponentType<JetpackComponent>> JETPACK = DATA_COMPONENTS.registerComponentType("jetpack", builder ->
            builder.persistent(JetpackComponent.CODEC).networkSynchronized(JetpackComponent.STREAM_CODEC));
    public static final Supplier<DataComponentType<JetpackFuelComponent>> JETPACK_FUEL = DATA_COMPONENTS.registerComponentType("jetpack_fuel", builder ->
            builder.persistent(JetpackFuelComponent.CODEC).networkSynchronized(JetpackFuelComponent.STREAM_CODEC));


    public static final Supplier<DataComponentType<PhotosyntheticComponent>> PHOTOSYNTHETIC = DATA_COMPONENTS.registerComponentType("photosynthetic", builder ->
            builder.persistent(PhotosyntheticComponent.CODEC).networkSynchronized(PhotosyntheticComponent.STREAM_CODEC));
    public static final Supplier<DataComponentType<SparkingComponent>> SPARKING = DATA_COMPONENTS.registerComponentType("sparking", builder ->
            builder.persistent(SparkingComponent.CODEC));
    public static final Supplier<DataComponentType<PermanentCurioEffectsComponent>> PERMANENT_EFFECTS = DATA_COMPONENTS.registerComponentType("permanent_effects", builder ->
            builder.persistent(PermanentCurioEffectsComponent.CODEC));
    public static final Supplier<DataComponentType<HitEffectsComponent>> HIT_EFFECTS = DATA_COMPONENTS.registerComponentType("hit_effects", builder ->
            builder.persistent(HitEffectsComponent.CODEC));

    public static void register(IEventBus eventBus) {
        DATA_COMPONENTS.register(eventBus);
    }
}
