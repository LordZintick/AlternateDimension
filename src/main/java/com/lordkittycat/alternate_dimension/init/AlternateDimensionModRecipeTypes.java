package com.lordkittycat.alternate_dimension.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.event.OnDatapackSyncEvent;
import net.neoforged.neoforge.client.event.RecipesReceivedEvent;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;
import net.neoforged.fml.event.lifecycle.FMLConstructModEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.ModList;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeMap;
import net.minecraft.core.registries.BuiltInRegistries;

import com.lordkittycat.alternate_dimension.jei_recipes.*;

@EventBusSubscriber
public class AlternateDimensionModRecipeTypes {
	public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(BuiltInRegistries.RECIPE_TYPE, "alternate_dimension");
	public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(BuiltInRegistries.RECIPE_SERIALIZER, "alternate_dimension");
	public static RecipeMap recipes = null;

	@SubscribeEvent
	public static void register(FMLConstructModEvent event) {
		IEventBus bus = ModList.get().getModContainerById("alternate_dimension").get().getEventBus();
		event.enqueueWork(() -> {
			RECIPE_TYPES.register(bus);
			SERIALIZERS.register(bus);
			RECIPE_TYPES.register("circuit_maker_recipe_type", () -> CircuitMakerRecipeTypeRecipe.Type.INSTANCE);
			SERIALIZERS.register("circuit_maker_recipe_type", () -> CircuitMakerRecipeTypeRecipe.SERIALIZER);
			RECIPE_TYPES.register("ingot_presser_recipe_type", () -> IngotPresserRecipeTypeRecipe.Type.INSTANCE);
			SERIALIZERS.register("ingot_presser_recipe_type", () -> IngotPresserRecipeTypeRecipe.SERIALIZER);
			RECIPE_TYPES.register("imbunement_table_recipe", () -> ImbunementTableRecipeRecipe.Type.INSTANCE);
			SERIALIZERS.register("imbunement_table_recipe", () -> ImbunementTableRecipeRecipe.SERIALIZER);
			RECIPE_TYPES.register("corruptor_recipe_type", () -> CorruptorRecipeTypeRecipe.Type.INSTANCE);
			SERIALIZERS.register("corruptor_recipe_type", () -> CorruptorRecipeTypeRecipe.SERIALIZER);
			RECIPE_TYPES.register("nuclear_reactor_recipe_type", () -> NuclearReactorRecipeTypeRecipe.Type.INSTANCE);
			SERIALIZERS.register("nuclear_reactor_recipe_type", () -> NuclearReactorRecipeTypeRecipe.SERIALIZER);
			RECIPE_TYPES.register("alloy_smelter_recipe_type", () -> AlloySmelterRecipeTypeRecipe.Type.INSTANCE);
			SERIALIZERS.register("alloy_smelter_recipe_type", () -> AlloySmelterRecipeTypeRecipe.SERIALIZER);
			RECIPE_TYPES.register("purifier_recipe_type", () -> PurifierRecipeTypeRecipe.Type.INSTANCE);
			SERIALIZERS.register("purifier_recipe_type", () -> PurifierRecipeTypeRecipe.SERIALIZER);
			RECIPE_TYPES.register("refinery_recipe_type", () -> RefineryRecipeTypeRecipe.Type.INSTANCE);
			SERIALIZERS.register("refinery_recipe_type", () -> RefineryRecipeTypeRecipe.SERIALIZER);
			RECIPE_TYPES.register("elemental_infuser_recipe_type", () -> ElementalInfuserRecipeTypeRecipe.Type.INSTANCE);
			SERIALIZERS.register("elemental_infuser_recipe_type", () -> ElementalInfuserRecipeTypeRecipe.SERIALIZER);
		});
	}

	@SubscribeEvent
	public static void syncRecipes(OnDatapackSyncEvent event) {
		event.sendRecipes(CircuitMakerRecipeTypeRecipe.Type.INSTANCE);
		event.sendRecipes(IngotPresserRecipeTypeRecipe.Type.INSTANCE);
		event.sendRecipes(ImbunementTableRecipeRecipe.Type.INSTANCE);
		event.sendRecipes(CorruptorRecipeTypeRecipe.Type.INSTANCE);
		event.sendRecipes(NuclearReactorRecipeTypeRecipe.Type.INSTANCE);
		event.sendRecipes(AlloySmelterRecipeTypeRecipe.Type.INSTANCE);
		event.sendRecipes(PurifierRecipeTypeRecipe.Type.INSTANCE);
		event.sendRecipes(RefineryRecipeTypeRecipe.Type.INSTANCE);
		event.sendRecipes(ElementalInfuserRecipeTypeRecipe.Type.INSTANCE);
	}

	@EventBusSubscriber(value = Dist.CLIENT)
	public static class RecipeReceiver {
		@SubscribeEvent
		public static void receiveRecipes(RecipesReceivedEvent event) {
			recipes = event.getRecipeMap();
		}

		@SubscribeEvent
		public static void clearRecipes(ClientPlayerNetworkEvent.LoggingOut event) {
			recipes = null;
		}
	}
}