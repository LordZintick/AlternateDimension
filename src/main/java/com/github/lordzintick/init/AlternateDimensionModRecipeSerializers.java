package com.github.lordzintick.init;

import com.github.lordzintick.modular.*;
import com.lordkittycat.alternate_dimension.AlternateDimensionMod;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RepairItemRecipe;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class AlternateDimensionModRecipeSerializers {
    private static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, AlternateDimensionMod.MODID);

    public static final Supplier<RecipeSerializer<BlueprintPartRecipe>> BLUEPRINT_PART =
            RECIPE_SERIALIZERS.register("blueprint_part", () -> new RecipeSerializer<>(
                    BlueprintPartRecipe.MAP_CODEC,
                    BlueprintPartRecipe.STREAM_CODEC
            ));

    public static final Supplier<RecipeSerializer<ModularAssemblyRecipe>> MODULAR_ASSEMBLY =
            RECIPE_SERIALIZERS.register("modular_assembly", () -> new RecipeSerializer<>(
                    ModularAssemblyRecipe.MAP_CODEC,
                    ModularAssemblyRecipe.STREAM_CODEC
            ));

    public static final Supplier<RecipeSerializer<BlueprintCreationRecipe>> BLUEPRINT_CREATION =
            RECIPE_SERIALIZERS.register("blueprint_creation", () -> new RecipeSerializer<>(
                    BlueprintCreationRecipe.MAP_CODEC,
                    BlueprintCreationRecipe.STREAM_CODEC
            ));

    public static final Supplier<RecipeSerializer<RepairKitFillRecipe>> REPAIR_KIT_FILL =
            RECIPE_SERIALIZERS.register("repair_kit_fill", () -> new RecipeSerializer<>(
                    RepairKitFillRecipe.MAP_CODEC,
                    RepairKitFillRecipe.STREAM_CODEC
            ));

    public static final Supplier<RecipeSerializer<RepairKitEmptyRecipe>> REPAIR_KIT_EMPTY =
            RECIPE_SERIALIZERS.register("repair_kit_empty", () -> new RecipeSerializer<>(
                    RepairKitEmptyRecipe.MAP_CODEC,
                    RepairKitEmptyRecipe.STREAM_CODEC
            ));

    public static final Supplier<RecipeSerializer<RepairKitToolRepairRecipe>> REPAIR_KIT_REPAIR =
            RECIPE_SERIALIZERS.register("repair_kit_tool_repair", () -> new RecipeSerializer<>(
                    RepairKitToolRepairRecipe.MAP_CODEC,
                    RepairKitToolRepairRecipe.STREAM_CODEC
            ));

    public static void register(IEventBus bus) {
        RECIPE_SERIALIZERS.register(bus);
    }
}
