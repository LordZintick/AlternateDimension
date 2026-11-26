package com.github.lordzintick.init;

import com.github.lordzintick.modular.BlueprintCreationRecipe;
import com.github.lordzintick.modular.BlueprintPartRecipe;
import com.github.lordzintick.modular.ModularAssemblyRecipe;
import com.lordkittycat.alternate_dimension.AlternateDimensionMod;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class AlternateDimensionModRecipeSerializers {
    private static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, AlternateDimensionMod.MODID);

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<BlueprintPartRecipe>> BLUEPRINT_PART =
            RECIPE_SERIALIZERS.register("blueprint_part", () -> new RecipeSerializer<>(
                    BlueprintPartRecipe.MAP_CODEC,
                    BlueprintPartRecipe.STREAM_CODEC
            ));

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<ModularAssemblyRecipe>> MODULAR_ASSEMBLY =
            RECIPE_SERIALIZERS.register("modular_assembly", () -> new RecipeSerializer<>(
                    ModularAssemblyRecipe.MAP_CODEC,
                    ModularAssemblyRecipe.STREAM_CODEC
            ));

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<BlueprintCreationRecipe>> BLUEPRINT_CREATION =
            RECIPE_SERIALIZERS.register("blueprint_creation", () -> new RecipeSerializer<>(
                    BlueprintCreationRecipe.MAP_CODEC,
                    BlueprintCreationRecipe.STREAM_CODEC
            ));

    public static void register(IEventBus bus) {
        RECIPE_SERIALIZERS.register(bus);
    }
}
