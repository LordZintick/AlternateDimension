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

public class AlloySmelterRecipeTypeRecipeCategory implements IRecipeCategory<AlloySmelterRecipeTypeRecipe> {
	public final static ResourceLocation UID = ResourceLocation.parse("alternate_dimension:alloy_smelter_recipe_type");
	public final static ResourceLocation TEXTURE = ResourceLocation.parse("alternate_dimension:textures/screens/alloy_smelter_recipe_gui.png");
	private final IDrawable background;
	private final IDrawable icon;
	private final Minecraft mc = Minecraft.getInstance();

	public AlloySmelterRecipeTypeRecipeCategory(IGuiHelper helper) {
		this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 92);
		this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(AlternateDimensionModBlocks.ALLOY_SMELTER.get().asItem()));
	}

	@Override
	public mezz.jei.api.recipe.RecipeType<AlloySmelterRecipeTypeRecipe> getRecipeType() {
		return AlternateDimensionModJeiPlugin.AlloySmelterRecipeType_Type;
	}

	@Override
	public Component getTitle() {
		return Component.literal("Alloy Smelting");
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
	public void draw(AlloySmelterRecipeTypeRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
		this.background.draw(guiGraphics);

	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, AlloySmelterRecipeTypeRecipe recipe, IFocusGroup focuses) {
		List<ItemStack> recipeOutputs = recipe.getResultItems();
		List<ItemStack> actualOutputs = NonNullList.withSize(1, ItemStack.EMPTY);
		for (int i = 0; i < recipeOutputs.size(); i++) {
			actualOutputs.set(i, recipeOutputs.get(i));
		}
		builder.addSlot(RecipeIngredientRole.INPUT, 56, 19).addIngredients(recipe.getIngredients().get(0));
		builder.addSlot(RecipeIngredientRole.INPUT, 81, 19).addIngredients(recipe.getIngredients().get(1));
		builder.addSlot(RecipeIngredientRole.INPUT, 106, 19).addIngredients(recipe.getIngredients().get(2));
		builder.addSlot(RecipeIngredientRole.OUTPUT, 81, 56).addItemStack(actualOutputs.get(0));
	}
}