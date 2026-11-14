package com.github.lordzintick.init;

import com.github.lordzintick.item.ring.RingEffectComponent;
import com.github.lordzintick.magic.ScrollRecipe;
import com.github.lordzintick.magic.WandRecipe;
import com.lordkittycat.alternate_dimension.AlternateDimensionMod;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.RecipeSerializers;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class AlternateDimensionModRecipes {
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(BuiltInRegistries.RECIPE_TYPE, AlternateDimensionMod.MODID);

    public static final Supplier<RecipeType<WandRecipe>> WAND_RECIPE = RECIPE_TYPES.register("wand_craft", () -> WandRecipe.Type.INSTANCE);
    public static final Supplier<RecipeType<ScrollRecipe>> SCROLL_RECIPE = RECIPE_TYPES.register("scroll_craft", () -> ScrollRecipe.Type.INSTANCE);

    public static void register(IEventBus eventBus) {
        RECIPE_TYPES.register(eventBus);
    }
}
