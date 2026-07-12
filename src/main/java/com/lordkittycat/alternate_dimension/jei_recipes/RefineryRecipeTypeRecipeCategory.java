package com.lordkittycat.alternate_dimension.jei_recipes;

import com.github.lordzintick.util.ColorUtil;
import com.mojang.authlib.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Font;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.resources.Identifier;
import net.minecraft.network.chat.Component;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Holder;
import net.minecraft.client.renderer.RenderPipelines;
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

public class RefineryRecipeTypeRecipeCategory implements IRecipeCategory<RefineryRecipeTypeRecipe> {
	public final static Identifier UID = Identifier.parse("alternate_dimension:refinery_recipe_type");
	public final static Identifier TEXTURE = Identifier.parse("alternate_dimension:textures/screens/refinery_recipe_gui.png");
	private final IDrawable background;
	private final IDrawable icon;

	private final Minecraft mc = Minecraft.getInstance();

	public RefineryRecipeTypeRecipeCategory(IGuiHelper helper) {
		this.background = helper.createDrawable(TEXTURE, 0, 0, 110, 42);
		this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(AlternateDimensionModBlocks.REFINERY_CONTROLLER.get().asItem()));
	}

	@Override
	public IRecipeType<RefineryRecipeTypeRecipe> getRecipeType() {
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
	public void draw(RefineryRecipeTypeRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphicsExtractor guiGraphics, double mouseX, double mouseY) {
		this.background.draw(guiGraphics);

		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, Identifier.parse("alternate_dimension:textures/screens/red_right_arrow.png"), 46, 3, 0, 16 * ((mc.player.tickCount / 1) % 1) - 1, 16, 16, 16, 16);
		guiGraphics.text(mc.font, "Required Power: " + recipe.strings().getFirst(), 5, 25, ColorUtil.fromRGB(100, 100, 100));
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
		for (Item item : recipe.getIngredients().get(0).items().map(Holder::value).collect(Collectors.toList()))
			stacks.add(new ItemStack(item, recipe.integers().get(0)));
		builder.addSlot(RecipeIngredientRole.INPUT, 7, 6).addItemStacks(stacks);
		stacks.clear();
		for (Item item : recipe.getIngredients().get(1).items().map(Holder::value).collect(Collectors.toList()))
			stacks.add(new ItemStack(item, recipe.integers().get(1)));
		builder.addSlot(RecipeIngredientRole.INPUT, 25, 6).addItemStacks(stacks);
		builder.addSlot(RecipeIngredientRole.OUTPUT, 69, 6).add(actualOutputs.get(0));
		builder.addSlot(RecipeIngredientRole.OUTPUT, 87, 6).add(actualOutputs.get(1));
	}
}