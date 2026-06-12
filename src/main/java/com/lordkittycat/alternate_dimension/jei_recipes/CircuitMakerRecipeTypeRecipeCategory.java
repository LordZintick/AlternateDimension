package com.lordkittycat.alternate_dimension.jei_recipes;

import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.core.NonNullList;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.Minecraft;

import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.constants.VanillaTypes;

import java.util.List;

import com.lordkittycat.alternate_dimension.init.AlternateDimensionModJeiPlugin;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModBlocks;

public class CircuitMakerRecipeTypeRecipeCategory implements IRecipeCategory<CircuitMakerRecipeTypeRecipe> {
	public final static ResourceLocation UID = ResourceLocation.parse("alternate_dimension:circuit_maker_recipe_type");
	public final static ResourceLocation TEXTURE = ResourceLocation.parse("alternate_dimension:textures/screens/circuit_maker_recipe_gui.png");
	private final IDrawable background;
	private final IDrawable icon;
	private final Minecraft mc = Minecraft.getInstance();

	public CircuitMakerRecipeTypeRecipeCategory(IGuiHelper helper) {
		this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 97);
		this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(AlternateDimensionModBlocks.CIRCUIT_MAKER.get().asItem()));
	}

	@Override
	public mezz.jei.api.recipe.RecipeType<CircuitMakerRecipeTypeRecipe> getRecipeType() {
		return AlternateDimensionModJeiPlugin.CircuitMakerRecipeType_Type;
	}

	@Override
	public Component getTitle() {
		return Component.literal("Circuit Making");
	}

	@Override
	public IDrawable getIcon() {
		return this.icon;
	}

	@Override
	public int getWidth() {
		return this.background.getWidth();
	}

	@Override
	public int getHeight() {
		return this.background.getHeight();
	}

	@Override
	public void draw(CircuitMakerRecipeTypeRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
		this.background.draw(guiGraphics);

	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, CircuitMakerRecipeTypeRecipe recipe, IFocusGroup focuses) {
		List<ItemStack> recipeOutputs = recipe.getResultItems();
		List<ItemStack> actualOutputs = NonNullList.withSize(1, ItemStack.EMPTY);
		for (int i = 0; i < recipeOutputs.size(); i++) {
			actualOutputs.set(i, recipeOutputs.get(i));
		}
		builder.addSlot(RecipeIngredientRole.INPUT, 38, 21).addIngredients(recipe.getIngredients().get(0));
		builder.addSlot(RecipeIngredientRole.INPUT, 38, 41).addIngredients(recipe.getIngredients().get(1));
		builder.addSlot(RecipeIngredientRole.INPUT, 38, 61).addIngredients(recipe.getIngredients().get(2));
		builder.addSlot(RecipeIngredientRole.OUTPUT, 111, 41).addItemStack(actualOutputs.get(3));
	}
}