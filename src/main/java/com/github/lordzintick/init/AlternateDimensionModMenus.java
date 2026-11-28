package com.github.lordzintick.init;

import com.github.lordzintick.item.backpack.BackpackMenu;
import com.lordkittycat.alternate_dimension.AlternateDimensionMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class AlternateDimensionModMenus {
    private static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(Registries.MENU, AlternateDimensionMod.MODID);

    public static final Supplier<MenuType<BackpackMenu>> BACKPACK = MENUS.register("backpack", () -> IMenuTypeExtension.create(BackpackMenu::new));

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
