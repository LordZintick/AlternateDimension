package com.lordkittycat.alternate_dimension.init;

import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.Minecraft;

import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.IModPlugin;

import java.util.stream.Collectors;
import java.util.Objects;
import java.util.List;

import com.lordkittycat.alternate_dimension.jei_recipes.*;

@JeiPlugin
public class AlternateDimensionModJeiPlugin implements IModPlugin {
	public static mezz.jei.api.recipe.RecipeType<CircuitMakerRecipeTypeRecipe> CircuitMakerRecipeType_Type = new mezz.jei.api.recipe.RecipeType<>(CircuitMakerRecipeTypeRecipeCategory.UID, CircuitMakerRecipeTypeRecipe.class);
	public static mezz.jei.api.recipe.RecipeType<IngotPresserRecipeTypeRecipe> IngotPresserRecipeType_Type = new mezz.jei.api.recipe.RecipeType<>(IngotPresserRecipeTypeRecipeCategory.UID, IngotPresserRecipeTypeRecipe.class);
	public static mezz.jei.api.recipe.RecipeType<ImbunementTableRecipeRecipe> ImbunementTableRecipe_Type = new mezz.jei.api.recipe.RecipeType<>(ImbunementTableRecipeRecipeCategory.UID, ImbunementTableRecipeRecipe.class);
	public static mezz.jei.api.recipe.RecipeType<CorruptorRecipeTypeRecipe> CorruptorRecipeType_Type = new mezz.jei.api.recipe.RecipeType<>(CorruptorRecipeTypeRecipeCategory.UID, CorruptorRecipeTypeRecipe.class);
	public static mezz.jei.api.recipe.RecipeType<NuclearReactorRecipeTypeRecipe> NuclearReactorRecipeType_Type = new mezz.jei.api.recipe.RecipeType<>(NuclearReactorRecipeTypeRecipeCategory.UID, NuclearReactorRecipeTypeRecipe.class);
	public static mezz.jei.api.recipe.RecipeType<AlloySmelterRecipeTypeRecipe> AlloySmelterRecipeType_Type = new mezz.jei.api.recipe.RecipeType<>(AlloySmelterRecipeTypeRecipeCategory.UID, AlloySmelterRecipeTypeRecipe.class);

	@Override
	public ResourceLocation getPluginUid() {
		return ResourceLocation.parse("alternate_dimension:jei_plugin");
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registration) {
		registration.addRecipeCategories(new CircuitMakerRecipeTypeRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new IngotPresserRecipeTypeRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new ImbunementTableRecipeRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new CorruptorRecipeTypeRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new NuclearReactorRecipeTypeRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new AlloySmelterRecipeTypeRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		RecipeManager recipeManager = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();
		List<CircuitMakerRecipeTypeRecipe> CircuitMakerRecipeTypeRecipes = recipeManager.getAllRecipesFor(CircuitMakerRecipeTypeRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
		registration.addRecipes(CircuitMakerRecipeType_Type, CircuitMakerRecipeTypeRecipes);
		List<IngotPresserRecipeTypeRecipe> IngotPresserRecipeTypeRecipes = recipeManager.getAllRecipesFor(IngotPresserRecipeTypeRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
		registration.addRecipes(IngotPresserRecipeType_Type, IngotPresserRecipeTypeRecipes);
		List<ImbunementTableRecipeRecipe> ImbunementTableRecipeRecipes = recipeManager.getAllRecipesFor(ImbunementTableRecipeRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
		registration.addRecipes(ImbunementTableRecipe_Type, ImbunementTableRecipeRecipes);
		List<CorruptorRecipeTypeRecipe> CorruptorRecipeTypeRecipes = recipeManager.getAllRecipesFor(CorruptorRecipeTypeRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
		registration.addRecipes(CorruptorRecipeType_Type, CorruptorRecipeTypeRecipes);
		List<NuclearReactorRecipeTypeRecipe> NuclearReactorRecipeTypeRecipes = recipeManager.getAllRecipesFor(NuclearReactorRecipeTypeRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
		registration.addRecipes(NuclearReactorRecipeType_Type, NuclearReactorRecipeTypeRecipes);
		List<AlloySmelterRecipeTypeRecipe> AlloySmelterRecipeTypeRecipes = recipeManager.getAllRecipesFor(AlloySmelterRecipeTypeRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
		registration.addRecipes(AlloySmelterRecipeType_Type, AlloySmelterRecipeTypeRecipes);
	}

	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
		registration.addRecipeCatalyst(new ItemStack(AlternateDimensionModBlocks.CIRCUIT_MAKER.get().asItem()), CircuitMakerRecipeType_Type);
		registration.addRecipeCatalyst(new ItemStack(AlternateDimensionModBlocks.INGOT_PRESSER.get().asItem()), IngotPresserRecipeType_Type);
		registration.addRecipeCatalyst(new ItemStack(AlternateDimensionModBlocks.IMBUNEMENT_TABLE.get().asItem()), ImbunementTableRecipe_Type);
		registration.addRecipeCatalyst(new ItemStack(AlternateDimensionModBlocks.CORRUPTOR.get().asItem()), CorruptorRecipeType_Type);
		registration.addRecipeCatalyst(new ItemStack(AlternateDimensionModBlocks.COOLANT_RODS.get().asItem()), NuclearReactorRecipeType_Type);
		registration.addRecipeCatalyst(new ItemStack(AlternateDimensionModBlocks.NUCLEAR_REACTOR.get().asItem()), NuclearReactorRecipeType_Type);
		registration.addRecipeCatalyst(new ItemStack(AlternateDimensionModBlocks.ALLOY_SMELTER.get().asItem()), AlloySmelterRecipeType_Type);
	}
}