package com.lordkittycat.alternate_dimension.init;

import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.Identifier;

import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.recipe.types.IRecipeType;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.IModPlugin;

import java.util.stream.Collectors;
import java.util.List;

import com.lordkittycat.alternate_dimension.jei_recipes.*;

@JeiPlugin
public class AlternateDimensionModJeiPlugin implements IModPlugin {
	public static IRecipeType<ElementalInfuserRecipeTypeRecipe> ElementalInfuserRecipeType_Type = IRecipeType.create(ElementalInfuserRecipeTypeRecipeCategory.UID, ElementalInfuserRecipeTypeRecipe.class);
	public static IRecipeType<FuserRecipeTypeRecipe> FuserRecipeType_Type = IRecipeType.create(FuserRecipeTypeRecipeCategory.UID, FuserRecipeTypeRecipe.class);
	public static IRecipeType<PhotolithographicMicrofabricatorRecipeTypeRecipe> PhotolithographicMicrofabricatorRecipeType_Type = IRecipeType.create(PhotolithographicMicrofabricatorRecipeTypeRecipeCategory.UID,
			PhotolithographicMicrofabricatorRecipeTypeRecipe.class);
	public static IRecipeType<CompressorRecipeTypeRecipe> CompressorRecipeType_Type = IRecipeType.create(CompressorRecipeTypeRecipeCategory.UID, CompressorRecipeTypeRecipe.class);
	public static IRecipeType<ManaMystifierRecipeTypeRecipe> ManaMystifierRecipeType_Type = IRecipeType.create(ManaMystifierRecipeTypeRecipeCategory.UID, ManaMystifierRecipeTypeRecipe.class);
	public static IRecipeType<FungalProcessorRecipeTypeRecipe> FungalProcessorRecipeType_Type = IRecipeType.create(FungalProcessorRecipeTypeRecipeCategory.UID, FungalProcessorRecipeTypeRecipe.class);

	@Override
	public Identifier getPluginUid() {
		return Identifier.parse("alternate_dimension:jei_plugin");
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registration) {
		registration.addRecipeCategories(new ElementalInfuserRecipeTypeRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new FuserRecipeTypeRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new PhotolithographicMicrofabricatorRecipeTypeRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new CompressorRecipeTypeRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new ManaMystifierRecipeTypeRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new FungalProcessorRecipeTypeRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		List<ElementalInfuserRecipeTypeRecipe> ElementalInfuserRecipeTypeRecipes = AlternateDimensionModRecipeTypes.recipes.byType(ElementalInfuserRecipeTypeRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
		registration.addRecipes(ElementalInfuserRecipeType_Type, ElementalInfuserRecipeTypeRecipes);
		List<FuserRecipeTypeRecipe> FuserRecipeTypeRecipes = AlternateDimensionModRecipeTypes.recipes.byType(FuserRecipeTypeRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
		registration.addRecipes(FuserRecipeType_Type, FuserRecipeTypeRecipes);
		List<PhotolithographicMicrofabricatorRecipeTypeRecipe> PhotolithographicMicrofabricatorRecipeTypeRecipes = AlternateDimensionModRecipeTypes.recipes.byType(PhotolithographicMicrofabricatorRecipeTypeRecipe.Type.INSTANCE).stream()
				.map(RecipeHolder::value).collect(Collectors.toList());
		registration.addRecipes(PhotolithographicMicrofabricatorRecipeType_Type, PhotolithographicMicrofabricatorRecipeTypeRecipes);
		List<CompressorRecipeTypeRecipe> CompressorRecipeTypeRecipes = AlternateDimensionModRecipeTypes.recipes.byType(CompressorRecipeTypeRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
		registration.addRecipes(CompressorRecipeType_Type, CompressorRecipeTypeRecipes);
		List<ManaMystifierRecipeTypeRecipe> ManaMystifierRecipeTypeRecipes = AlternateDimensionModRecipeTypes.recipes.byType(ManaMystifierRecipeTypeRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
		registration.addRecipes(ManaMystifierRecipeType_Type, ManaMystifierRecipeTypeRecipes);
		List<FungalProcessorRecipeTypeRecipe> FungalProcessorRecipeTypeRecipes = AlternateDimensionModRecipeTypes.recipes.byType(FungalProcessorRecipeTypeRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
		registration.addRecipes(FungalProcessorRecipeType_Type, FungalProcessorRecipeTypeRecipes);
	}

	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
		registration.addCraftingStations(ElementalInfuserRecipeType_Type, VanillaTypes.ITEM_STACK, List.of(new ItemStack(AlternateDimensionModBlocks.ELEMENTAL_INSCRIBER.get().asItem())));
		registration.addCraftingStations(FuserRecipeType_Type, VanillaTypes.ITEM_STACK, List.of(new ItemStack(AlternateDimensionModBlocks.FUSER.get().asItem())));
		registration.addCraftingStations(PhotolithographicMicrofabricatorRecipeType_Type, VanillaTypes.ITEM_STACK, List.of(new ItemStack(AlternateDimensionModBlocks.PHOTOLITHOGRAPHIC_MICROFABRICATOR.get().asItem())));
		registration.addCraftingStations(CompressorRecipeType_Type, VanillaTypes.ITEM_STACK, List.of(new ItemStack(AlternateDimensionModBlocks.COMPRESSOR.get().asItem())));
		registration.addCraftingStations(ManaMystifierRecipeType_Type, VanillaTypes.ITEM_STACK, List.of(new ItemStack(AlternateDimensionModBlocks.MANA_MYSTIFIER.get().asItem())));
		registration.addCraftingStations(FungalProcessorRecipeType_Type, VanillaTypes.ITEM_STACK, List.of(new ItemStack(AlternateDimensionModBlocks.FUNGAL_PROCESSOR.get().asItem())));
	}
}