package com.lordkittycat.alternate_dimension.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.fml.event.lifecycle.FMLConstructModEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.ModList;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.IEventBus;

import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.core.registries.BuiltInRegistries;

import com.lordkittycat.alternate_dimension.jei_recipes.*;

@EventBusSubscriber
public class AlternateDimensionModRecipeTypes {
	public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(BuiltInRegistries.RECIPE_TYPE, "alternate_dimension");
	public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(BuiltInRegistries.RECIPE_SERIALIZER, "alternate_dimension");

	@SubscribeEvent
	public static void register(FMLConstructModEvent event) {
		IEventBus bus = ModList.get().getModContainerById("alternate_dimension").get().getEventBus();
		event.enqueueWork(() -> {
			RECIPE_TYPES.register(bus);
			SERIALIZERS.register(bus);
			RECIPE_TYPES.register("circuit_maker_recipe_type", () -> CircuitMakerRecipeTypeRecipe.Type.INSTANCE);
			SERIALIZERS.register("circuit_maker_recipe_type", () -> CircuitMakerRecipeTypeRecipe.Serializer.INSTANCE);
			RECIPE_TYPES.register("ingot_presser_recipe_type", () -> IngotPresserRecipeTypeRecipe.Type.INSTANCE);
			SERIALIZERS.register("ingot_presser_recipe_type", () -> IngotPresserRecipeTypeRecipe.Serializer.INSTANCE);
			RECIPE_TYPES.register("imbunement_table_recipe", () -> ImbunementTableRecipeRecipe.Type.INSTANCE);
			SERIALIZERS.register("imbunement_table_recipe", () -> ImbunementTableRecipeRecipe.Serializer.INSTANCE);
			RECIPE_TYPES.register("corruptor_recipe_type", () -> CorruptorRecipeTypeRecipe.Type.INSTANCE);
			SERIALIZERS.register("corruptor_recipe_type", () -> CorruptorRecipeTypeRecipe.Serializer.INSTANCE);
			RECIPE_TYPES.register("nuclear_reactor_recipe_type", () -> NuclearReactorRecipeTypeRecipe.Type.INSTANCE);
			SERIALIZERS.register("nuclear_reactor_recipe_type", () -> NuclearReactorRecipeTypeRecipe.Serializer.INSTANCE);
			RECIPE_TYPES.register("alloy_smelter_recipe_type", () -> AlloySmelterRecipeTypeRecipe.Type.INSTANCE);
			SERIALIZERS.register("alloy_smelter_recipe_type", () -> AlloySmelterRecipeTypeRecipe.Serializer.INSTANCE);
		});
	}
}