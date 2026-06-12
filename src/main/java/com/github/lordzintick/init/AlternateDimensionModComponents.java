package com.github.lordzintick.init;

import com.lordkittycat.alternate_dimension.AlternateDimensionMod;
import net.minecraft.core.component.DataComponentType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class AlternateDimensionModComponents {
    public static final DeferredRegister.DataComponents DATA_COMPONENTS = DeferredRegister.createDataComponents(AlternateDimensionMod.MODID);



    public static void register(IEventBus eventBus) {
        DATA_COMPONENTS.register(eventBus);
    }
}
