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

import com.lordkittycat.alternate_dimension.jei_recipes.PhotolithographicMicrofabricatorRecipeTypeRecipe;
import com.lordkittycat.alternate_dimension.jei_recipes.ImbunementTableRecipeRecipe;
import com.lordkittycat.alternate_dimension.jei_recipes.FuserRecipeTypeRecipe;
import com.lordkittycat.alternate_dimension.jei_recipes.ElementalInfuserRecipeTypeRecipe;
import com.lordkittycat.alternate_dimension.jei_recipes.CompressorRecipeTypeRecipe;

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
			RECIPE_TYPES.register("imbunement_table_recipe", () -> ImbunementTableRecipeRecipe.Type.INSTANCE);
			SERIALIZERS.register("imbunement_table_recipe", () -> ImbunementTableRecipeRecipe.SERIALIZER);
			RECIPE_TYPES.register("elemental_infuser_recipe_type", () -> ElementalInfuserRecipeTypeRecipe.Type.INSTANCE);
			SERIALIZERS.register("elemental_infuser_recipe_type", () -> ElementalInfuserRecipeTypeRecipe.SERIALIZER);
			RECIPE_TYPES.register("fuser_recipe_type", () -> FuserRecipeTypeRecipe.Type.INSTANCE);
			SERIALIZERS.register("fuser_recipe_type", () -> FuserRecipeTypeRecipe.SERIALIZER);
			RECIPE_TYPES.register("photolithographic_microfabricator_recipe_type", () -> PhotolithographicMicrofabricatorRecipeTypeRecipe.Type.INSTANCE);
			SERIALIZERS.register("photolithographic_microfabricator_recipe_type", () -> PhotolithographicMicrofabricatorRecipeTypeRecipe.SERIALIZER);
			RECIPE_TYPES.register("compressor_recipe_type", () -> CompressorRecipeTypeRecipe.Type.INSTANCE);
			SERIALIZERS.register("compressor_recipe_type", () -> CompressorRecipeTypeRecipe.SERIALIZER);
		});
	}

	@SubscribeEvent
	public static void syncRecipes(OnDatapackSyncEvent event) {
		event.sendRecipes(ImbunementTableRecipeRecipe.Type.INSTANCE);
		event.sendRecipes(ElementalInfuserRecipeTypeRecipe.Type.INSTANCE);
		event.sendRecipes(FuserRecipeTypeRecipe.Type.INSTANCE);
		event.sendRecipes(PhotolithographicMicrofabricatorRecipeTypeRecipe.Type.INSTANCE);
		event.sendRecipes(CompressorRecipeTypeRecipe.Type.INSTANCE);
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