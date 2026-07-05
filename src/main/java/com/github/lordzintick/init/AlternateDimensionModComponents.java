package com.github.lordzintick.init;

import com.github.lordzintick.item.ring.RingEffectComponent;
import com.lordkittycat.alternate_dimension.AlternateDimensionMod;
import net.minecraft.core.component.DataComponentType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class AlternateDimensionModComponents {
    public static final DeferredRegister.DataComponents DATA_COMPONENTS = DeferredRegister.createDataComponents(AlternateDimensionMod.MODID);

    public static final Supplier<DataComponentType<RingEffectComponent>> RING_EFFECT = DATA_COMPONENTS.registerComponentType("ring_effect", builder ->
            builder.persistent(RingEffectComponent.CODEC));

    public static void register(IEventBus eventBus) {
        DATA_COMPONENTS.register(eventBus);
    }
}
