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
import java.util.ArrayList;

import com.lordkittycat.alternate_dimension.init.AlternateDimensionModJeiPlugin;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModBlocks;

public class RefineryRecipeTypeRecipeCategory implements IRecipeCategory<RefineryRecipeTypeRecipe> {
	public final static ResourceLocation UID = ResourceLocation.parse("alternate_dimension:refinery_recipe_type");
	public final static ResourceLocation TEXTURE = ResourceLocation.parse("alternate_dimension:textures/screens/refinery_recipe_gui.png");
	private final IDrawable background;
	private final IDrawable icon;
	private final Minecraft mc = Minecraft.getInstance();

	public RefineryRecipeTypeRecipeCategory(IGuiHelper helper) {
		this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 104);
		this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(AlternateDimensionModBlocks.REFINERY_CONTROLLER.get().asItem()));
	}

	@Override
	public mezz.jei.api.recipe.RecipeType<RefineryRecipeTypeRecipe> getRecipeType() {
		return AlternateDimensionModJeiPlugin.RefineryRecipeType_Type;
	}

	@Override
	public Component getTitle() {
		return Component.literal("Electrolytic Refining");
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
	public void draw(RefineryRecipeTypeRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
		this.background.draw(guiGraphics);

		guiGraphics.blit(ResourceLocation.parse("alternate_dimension:textures/screens/red_right_arrow.png"), 66, 18, 0, 16 * ((mc.player.tickCount / 1) % 1) - 1, 16, 16, 16, 16);

	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, RefineryRecipeTypeRecipe recipe, IFocusGroup focuses) {
		List<ItemStack> stacks = new ArrayList<>();
		List<ItemStack> recipeOutputs = recipe.getResultItems();
		List<ItemStack> actualOutputs = NonNullList.withSize(2, ItemStack.EMPTY);
		for (int i = 0; i < recipeOutputs.size(); i++) {
			actualOutputs.set(i, recipeOutputs.get(i));
		}
		stacks.clear();
		for (ItemStack item : (List<ItemStack>) List.of(recipe.getIngredients().get(0).getItems()))
			stacks.add(new ItemStack(item.getItem(), recipe.integers().get(0)));
		builder.addSlot(RecipeIngredientRole.INPUT, 25, 19).addItemStacks(stacks);
		stacks.clear();
		for (ItemStack item : (List<ItemStack>) List.of(recipe.getIngredients().get(1).getItems()))
			stacks.add(new ItemStack(item.getItem(), recipe.integers().get(1)));
		builder.addSlot(RecipeIngredientRole.INPUT, 43, 19).addItemStacks(stacks);
		builder.addSlot(RecipeIngredientRole.OUTPUT, 87, 19).addItemStack(actualOutputs.get(0));
		builder.addSlot(RecipeIngredientRole.OUTPUT, 105, 19).addItemStack(actualOutputs.get(1));
	}
}