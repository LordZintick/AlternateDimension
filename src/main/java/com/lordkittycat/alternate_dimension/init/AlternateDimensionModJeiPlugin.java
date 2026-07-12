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
	public static IRecipeType<CircuitMakerRecipeTypeRecipe> CircuitMakerRecipeType_Type = IRecipeType.create(CircuitMakerRecipeTypeRecipeCategory.UID, CircuitMakerRecipeTypeRecipe.class);
	public static IRecipeType<IngotPresserRecipeTypeRecipe> IngotPresserRecipeType_Type = IRecipeType.create(IngotPresserRecipeTypeRecipeCategory.UID, IngotPresserRecipeTypeRecipe.class);
	public static IRecipeType<ImbunementTableRecipeRecipe> ImbunementTableRecipe_Type = IRecipeType.create(ImbunementTableRecipeRecipeCategory.UID, ImbunementTableRecipeRecipe.class);
	public static IRecipeType<CorruptorRecipeTypeRecipe> CorruptorRecipeType_Type = IRecipeType.create(CorruptorRecipeTypeRecipeCategory.UID, CorruptorRecipeTypeRecipe.class);
	public static IRecipeType<NuclearReactorRecipeTypeRecipe> NuclearReactorRecipeType_Type = IRecipeType.create(NuclearReactorRecipeTypeRecipeCategory.UID, NuclearReactorRecipeTypeRecipe.class);
	public static IRecipeType<AlloySmelterRecipeTypeRecipe> AlloySmelterRecipeType_Type = IRecipeType.create(AlloySmelterRecipeTypeRecipeCategory.UID, AlloySmelterRecipeTypeRecipe.class);
	public static IRecipeType<PurifierRecipeTypeRecipe> PurifierRecipeType_Type = IRecipeType.create(PurifierRecipeTypeRecipeCategory.UID, PurifierRecipeTypeRecipe.class);
	public static IRecipeType<RefineryRecipeTypeRecipe> RefineryRecipeType_Type = IRecipeType.create(RefineryRecipeTypeRecipeCategory.UID, RefineryRecipeTypeRecipe.class);
	public static IRecipeType<ElementalInfuserRecipeTypeRecipe> ElementalInfuserRecipeType_Type = IRecipeType.create(ElementalInfuserRecipeTypeRecipeCategory.UID, ElementalInfuserRecipeTypeRecipe.class);

	@Override
	public Identifier getPluginUid() {
		return Identifier.parse("alternate_dimension:jei_plugin");
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registration) {
		registration.addRecipeCategories(new CircuitMakerRecipeTypeRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new IngotPresserRecipeTypeRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new ImbunementTableRecipeRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new CorruptorRecipeTypeRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new NuclearReactorRecipeTypeRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new AlloySmelterRecipeTypeRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new PurifierRecipeTypeRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new RefineryRecipeTypeRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new ElementalInfuserRecipeTypeRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		List<CircuitMakerRecipeTypeRecipe> CircuitMakerRecipeTypeRecipes = AlternateDimensionModRecipeTypes.recipes.byType(CircuitMakerRecipeTypeRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
		registration.addRecipes(CircuitMakerRecipeType_Type, CircuitMakerRecipeTypeRecipes);
		List<IngotPresserRecipeTypeRecipe> IngotPresserRecipeTypeRecipes = AlternateDimensionModRecipeTypes.recipes.byType(IngotPresserRecipeTypeRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
		registration.addRecipes(IngotPresserRecipeType_Type, IngotPresserRecipeTypeRecipes);
		List<ImbunementTableRecipeRecipe> ImbunementTableRecipeRecipes = AlternateDimensionModRecipeTypes.recipes.byType(ImbunementTableRecipeRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
		registration.addRecipes(ImbunementTableRecipe_Type, ImbunementTableRecipeRecipes);
		List<CorruptorRecipeTypeRecipe> CorruptorRecipeTypeRecipes = AlternateDimensionModRecipeTypes.recipes.byType(CorruptorRecipeTypeRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
		registration.addRecipes(CorruptorRecipeType_Type, CorruptorRecipeTypeRecipes);
		List<NuclearReactorRecipeTypeRecipe> NuclearReactorRecipeTypeRecipes = AlternateDimensionModRecipeTypes.recipes.byType(NuclearReactorRecipeTypeRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
		registration.addRecipes(NuclearReactorRecipeType_Type, NuclearReactorRecipeTypeRecipes);
		List<AlloySmelterRecipeTypeRecipe> AlloySmelterRecipeTypeRecipes = AlternateDimensionModRecipeTypes.recipes.byType(AlloySmelterRecipeTypeRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
		registration.addRecipes(AlloySmelterRecipeType_Type, AlloySmelterRecipeTypeRecipes);
		List<PurifierRecipeTypeRecipe> PurifierRecipeTypeRecipes = AlternateDimensionModRecipeTypes.recipes.byType(PurifierRecipeTypeRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
		registration.addRecipes(PurifierRecipeType_Type, PurifierRecipeTypeRecipes);
		List<RefineryRecipeTypeRecipe> RefineryRecipeTypeRecipes = AlternateDimensionModRecipeTypes.recipes.byType(RefineryRecipeTypeRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
		registration.addRecipes(RefineryRecipeType_Type, RefineryRecipeTypeRecipes);
		List<ElementalInfuserRecipeTypeRecipe> ElementalInfuserRecipeTypeRecipes = AlternateDimensionModRecipeTypes.recipes.byType(ElementalInfuserRecipeTypeRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
		registration.addRecipes(ElementalInfuserRecipeType_Type, ElementalInfuserRecipeTypeRecipes);
	}

	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
		registration.addCraftingStations(CircuitMakerRecipeType_Type, VanillaTypes.ITEM_STACK, List.of(new ItemStack(AlternateDimensionModBlocks.CIRCUIT_MAKER.get().asItem())));
		registration.addCraftingStations(IngotPresserRecipeType_Type, VanillaTypes.ITEM_STACK,
				List.of(new ItemStack(AlternateDimensionModBlocks.INGOT_PRESSER.get().asItem()), new ItemStack(AlternateDimensionModBlocks.ADVANCED_INGOT_PRESSER.get().asItem())));
		registration.addCraftingStations(ImbunementTableRecipe_Type, VanillaTypes.ITEM_STACK, List.of(new ItemStack(AlternateDimensionModBlocks.IMBUNEMENT_TABLE.get().asItem())));
		registration.addCraftingStations(CorruptorRecipeType_Type, VanillaTypes.ITEM_STACK, List.of(new ItemStack(AlternateDimensionModBlocks.CORRUPTOR.get().asItem()), new ItemStack(AlternateDimensionModBlocks.ADVANCED_CORRUPTOR.get().asItem())));
		registration.addCraftingStations(NuclearReactorRecipeType_Type, VanillaTypes.ITEM_STACK,
				List.of(new ItemStack(AlternateDimensionModBlocks.COOLANT_RODS.get().asItem()), new ItemStack(AlternateDimensionModBlocks.NUCLEAR_REACTOR.get().asItem())));
		registration.addCraftingStations(AlloySmelterRecipeType_Type, VanillaTypes.ITEM_STACK, List.of(new ItemStack(AlternateDimensionModBlocks.ALLOY_SMELTER.get().asItem())));
		registration.addCraftingStations(PurifierRecipeType_Type, VanillaTypes.ITEM_STACK, List.of(new ItemStack(AlternateDimensionModBlocks.PURIFIER.get().asItem()), new ItemStack(AlternateDimensionModBlocks.ADVANCED_PURIFIER.get().asItem())));
		registration.addCraftingStations(RefineryRecipeType_Type, VanillaTypes.ITEM_STACK,
				List.of(new ItemStack(AlternateDimensionModBlocks.REFINERY_CONTROLLER.get().asItem()), new ItemStack(AlternateDimensionModBlocks.REFINERY_BASE.get().asItem())));
		registration.addCraftingStations(ElementalInfuserRecipeType_Type, VanillaTypes.ITEM_STACK, List.of(new ItemStack(AlternateDimensionModBlocks.ELEMENTAL_INSCRIBER.get().asItem())));
	}
}