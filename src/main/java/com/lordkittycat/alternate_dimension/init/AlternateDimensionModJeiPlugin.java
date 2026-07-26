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
	public static IRecipeType<ImbunementTableRecipeRecipe> ImbunementTableRecipe_Type = IRecipeType.create(ImbunementTableRecipeRecipeCategory.UID, ImbunementTableRecipeRecipe.class);
	public static IRecipeType<ElementalInfuserRecipeTypeRecipe> ElementalInfuserRecipeType_Type = IRecipeType.create(ElementalInfuserRecipeTypeRecipeCategory.UID, ElementalInfuserRecipeTypeRecipe.class);
	public static IRecipeType<FuserRecipeTypeRecipe> FuserRecipeType_Type = IRecipeType.create(FuserRecipeTypeRecipeCategory.UID, FuserRecipeTypeRecipe.class);
	public static IRecipeType<PhotolithographicMicrofabricatorRecipeTypeRecipe> PhotolithographicMicrofabricatorRecipeType_Type = IRecipeType.create(PhotolithographicMicrofabricatorRecipeTypeRecipeCategory.UID,
			PhotolithographicMicrofabricatorRecipeTypeRecipe.class);
	public static IRecipeType<CompressorRecipeTypeRecipe> CompressorRecipeType_Type = IRecipeType.create(CompressorRecipeTypeRecipeCategory.UID, CompressorRecipeTypeRecipe.class);

	@Override
	public Identifier getPluginUid() {
		return Identifier.parse("alternate_dimension:jei_plugin");
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registration) {
		registration.addRecipeCategories(new ImbunementTableRecipeRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new ElementalInfuserRecipeTypeRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new FuserRecipeTypeRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new PhotolithographicMicrofabricatorRecipeTypeRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new CompressorRecipeTypeRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		List<ImbunementTableRecipeRecipe> ImbunementTableRecipeRecipes = AlternateDimensionModRecipeTypes.recipes.byType(ImbunementTableRecipeRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
		registration.addRecipes(ImbunementTableRecipe_Type, ImbunementTableRecipeRecipes);
		List<ElementalInfuserRecipeTypeRecipe> ElementalInfuserRecipeTypeRecipes = AlternateDimensionModRecipeTypes.recipes.byType(ElementalInfuserRecipeTypeRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
		registration.addRecipes(ElementalInfuserRecipeType_Type, ElementalInfuserRecipeTypeRecipes);
		List<FuserRecipeTypeRecipe> FuserRecipeTypeRecipes = AlternateDimensionModRecipeTypes.recipes.byType(FuserRecipeTypeRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
		registration.addRecipes(FuserRecipeType_Type, FuserRecipeTypeRecipes);
		List<PhotolithographicMicrofabricatorRecipeTypeRecipe> PhotolithographicMicrofabricatorRecipeTypeRecipes = AlternateDimensionModRecipeTypes.recipes.byType(PhotolithographicMicrofabricatorRecipeTypeRecipe.Type.INSTANCE).stream()
				.map(RecipeHolder::value).collect(Collectors.toList());
		registration.addRecipes(PhotolithographicMicrofabricatorRecipeType_Type, PhotolithographicMicrofabricatorRecipeTypeRecipes);
		List<CompressorRecipeTypeRecipe> CompressorRecipeTypeRecipes = AlternateDimensionModRecipeTypes.recipes.byType(CompressorRecipeTypeRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
		registration.addRecipes(CompressorRecipeType_Type, CompressorRecipeTypeRecipes);
	}

	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
		registration.addCraftingStations(ImbunementTableRecipe_Type, VanillaTypes.ITEM_STACK, List.of(new ItemStack(AlternateDimensionModBlocks.IMBUNEMENT_TABLE.get().asItem())));
		registration.addCraftingStations(ElementalInfuserRecipeType_Type, VanillaTypes.ITEM_STACK, List.of(new ItemStack(AlternateDimensionModBlocks.ELEMENTAL_INSCRIBER.get().asItem())));
		registration.addCraftingStations(FuserRecipeType_Type, VanillaTypes.ITEM_STACK, List.of(new ItemStack(AlternateDimensionModBlocks.FUSER.get().asItem())));
		registration.addCraftingStations(PhotolithographicMicrofabricatorRecipeType_Type, VanillaTypes.ITEM_STACK, List.of(new ItemStack(AlternateDimensionModBlocks.PHOTOLITHOGRAPHIC_MICROFABRICATOR.get().asItem())));
		registration.addCraftingStations(CompressorRecipeType_Type, VanillaTypes.ITEM_STACK, List.of(new ItemStack(AlternateDimensionModBlocks.COMPRESSOR.get().asItem())));
	}
}