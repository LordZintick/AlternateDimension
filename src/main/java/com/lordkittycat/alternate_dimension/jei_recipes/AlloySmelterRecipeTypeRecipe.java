package com.lordkittycat.alternate_dimension.jei_recipes;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.core.NonNullList;

import java.util.List;
import java.util.ArrayList;

import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.mojang.serialization.MapCodec;

public record AlloySmelterRecipeTypeRecipe(List<ItemStackTemplate> outputs, List<Ingredient> recipeItems) implements Recipe<RecipeInput> {
	public static final MapCodec<AlloySmelterRecipeTypeRecipe> MAP_CODEC = RecordCodecBuilder.mapCodec(builder -> builder
			.group(ItemStackTemplate.CODEC.listOf().optionalFieldOf("outputs", List.of()).forGetter(AlloySmelterRecipeTypeRecipe::outputs), Ingredient.CODEC.listOf().fieldOf("ingredients").forGetter(AlloySmelterRecipeTypeRecipe::recipeItems))
			.apply(builder, AlloySmelterRecipeTypeRecipe::new));
	public static final StreamCodec<RegistryFriendlyByteBuf, AlloySmelterRecipeTypeRecipe> STREAM_CODEC = StreamCodec.of(AlloySmelterRecipeTypeRecipe::toNetwork, AlloySmelterRecipeTypeRecipe::fromNetwork);
	public static final RecipeSerializer<AlloySmelterRecipeTypeRecipe> SERIALIZER = new RecipeSerializer<>(MAP_CODEC, STREAM_CODEC);

	public AlloySmelterRecipeTypeRecipe(List<ItemStackTemplate> outputs, List<Ingredient> recipeItems) {
		this.outputs = outputs;
		this.recipeItems = recipeItems;
	}

	@Override
	public RecipeBookCategory recipeBookCategory() {
		return RecipeBookCategories.CRAFTING_MISC;
	}

	@Override
	public PlacementInfo placementInfo() {
		return PlacementInfo.create(this.recipeItems);
	}

	@Override
	public boolean matches(RecipeInput pContainer, Level pLevel) {
		if (pLevel.isClientSide()) {
			return false;
		}
		return false;
	}

	@Override
	public final String group() {
		return "";
	}

	@Override
	public boolean showNotification() {
		return false;
	}

	public List<Ingredient> getIngredients() {
		return recipeItems;
	}

	@Override
	public ItemStack assemble(RecipeInput input) {
		return ItemStack.EMPTY;
	}

	public List<ItemStack> getResultItems() {
		return this.outputs.stream().map(ItemStackTemplate::create).toList();
	}

	@Override
	public RecipeType<? extends Recipe<RecipeInput>> getType() {
		return Type.INSTANCE;
	}

	@Override
	public RecipeSerializer<AlloySmelterRecipeTypeRecipe> getSerializer() {
		return SERIALIZER;
	}

	public static class Type implements RecipeType<AlloySmelterRecipeTypeRecipe> {
		private Type() {
		}

		public static final RecipeType<AlloySmelterRecipeTypeRecipe> INSTANCE = new Type();
	}

	private static AlloySmelterRecipeTypeRecipe fromNetwork(RegistryFriendlyByteBuf buf) {
		List<Ingredient> inputs = NonNullList.withSize(buf.readVarInt(), EmptyIngredient.create());
		inputs.replaceAll(ingredients -> Ingredient.CONTENTS_STREAM_CODEC.decode(buf));
		int outputCount = buf.readVarInt();
		List<ItemStackTemplate> outputs = new ArrayList<>(outputCount);
		for (int i = 0; i < outputCount; i++) {
			outputs.add(ItemStackTemplate.STREAM_CODEC.decode(buf));
		}
		return new AlloySmelterRecipeTypeRecipe(outputs, inputs);
	}

	private static void toNetwork(RegistryFriendlyByteBuf buf, AlloySmelterRecipeTypeRecipe recipe) {
		buf.writeVarInt(recipe.getIngredients().size());
		for (Ingredient ing : recipe.getIngredients()) {
			if (ing.items().findFirst().get().value() == Items.AIR)
				Ingredient.CONTENTS_STREAM_CODEC.encode(buf, EmptyIngredient.create());
			else
				Ingredient.CONTENTS_STREAM_CODEC.encode(buf, ing);
		}
		buf.writeVarInt(recipe.getResultItems().size());
		for (ItemStackTemplate template : recipe.outputs) {
			ItemStackTemplate.STREAM_CODEC.encode(buf, template);
		}
	}
}