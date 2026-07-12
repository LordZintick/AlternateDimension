package com.lordkittycat.alternate_dimension.jei_recipes;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.resources.Identifier;
import net.minecraft.network.chat.Component;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Holder;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.Minecraft;

import mezz.jei.api.recipe.types.IRecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.constants.VanillaTypes;

import java.util.stream.Collectors;
import java.util.List;
import java.util.ArrayList;

import com.lordkittycat.alternate_dimension.init.AlternateDimensionModJeiPlugin;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModBlocks;

public class ElementalInfuserRecipeTypeRecipeCategory implements IRecipeCategory<ElementalInfuserRecipeTypeRecipe> {
	public final static Identifier UID = Identifier.parse("alternate_dimension:elemental_infuser_recipe_type");
	public final static Identifier TEXTURE = Identifier.parse("alternate_dimension:textures/screens/elemental_inscriber_recipe_gui.png");
	private final IDrawable background;
	private final IDrawable icon;

	private final Minecraft mc = Minecraft.getInstance();

	public ElementalInfuserRecipeTypeRecipeCategory(IGuiHelper helper) {
		this.background = helper.createDrawable(TEXTURE, 0, 0, 101, 72);
		this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(AlternateDimensionModBlocks.ELEMENTAL_INSCRIBER.get().asItem()));
	}

	@Override
	public IRecipeType<ElementalInfuserRecipeTypeRecipe> getRecipeType() {
		return AlternateDimensionModJeiPlugin.ElementalInfuserRecipeType_Type;
	}

	@Override
	public Component getTitle() {
		return Component.literal("Elemental Inscription");
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
	public boolean needsRecipeBorder() {
		return false;
	}

	@Override
	public void draw(ElementalInfuserRecipeTypeRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphicsExtractor guiGraphics, double mouseX, double mouseY) {
		this.background.draw(guiGraphics);
	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, ElementalInfuserRecipeTypeRecipe recipe, IFocusGroup focuses) {
		List<ItemStack> stacks = new ArrayList<>();
		List<ItemStack> recipeOutputs = recipe.getResultItems();
		List<ItemStack> actualOutputs = NonNullList.withSize(1, ItemStack.EMPTY);
		for (int i = 0; i < recipeOutputs.size(); i++) {
			actualOutputs.set(i, recipeOutputs.get(i));
		}
		stacks.clear();
		for (Item item : recipe.getIngredients().get(0).items().map(Holder::value).collect(Collectors.toList()))
			stacks.add(new ItemStack(item, recipe.integers().get(0)));
		builder.addSlot(RecipeIngredientRole.INPUT, 43, 8).addItemStacks(stacks);
		stacks.clear();
		for (Item item : recipe.getIngredients().get(1).items().map(Holder::value).collect(Collectors.toList()))
			stacks.add(new ItemStack(item, recipe.integers().get(1)));
		builder.addSlot(RecipeIngredientRole.INPUT, 7, 27).addItemStacks(stacks);
		stacks.clear();
		for (Item item : recipe.getIngredients().get(2).items().map(Holder::value).collect(Collectors.toList()))
			stacks.add(new ItemStack(item, recipe.integers().get(2)));
		builder.addSlot(RecipeIngredientRole.INPUT, 25, 27).addItemStacks(stacks);
		stacks.clear();
		for (Item item : recipe.getIngredients().get(3).items().map(Holder::value).collect(Collectors.toList()))
			stacks.add(new ItemStack(item, recipe.integers().get(3)));
		builder.addSlot(RecipeIngredientRole.INPUT, 61, 27).addItemStacks(stacks);
		stacks.clear();
		for (Item item : recipe.getIngredients().get(4).items().map(Holder::value).collect(Collectors.toList()))
			stacks.add(new ItemStack(item, recipe.integers().get(4)));
		builder.addSlot(RecipeIngredientRole.INPUT, 79, 27).addItemStacks(stacks);
		builder.addSlot(RecipeIngredientRole.OUTPUT, 43, 48).add(actualOutputs.get(0));
	}
}