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
import com.mojang.serialization.Codec;

public record RefineryRecipeTypeRecipe(List<ItemStackTemplate> outputs, List<Ingredient> recipeItems, List<Integer> integers, List<String> strings) implements Recipe<RecipeInput> {
	public static final MapCodec<RefineryRecipeTypeRecipe> MAP_CODEC = RecordCodecBuilder
			.mapCodec(
					builder -> builder
							.group(ItemStackTemplate.CODEC.listOf().optionalFieldOf("outputs", List.of()).forGetter(RefineryRecipeTypeRecipe::outputs), Ingredient.CODEC.listOf().fieldOf("ingredients").forGetter(RefineryRecipeTypeRecipe::recipeItems),
									Codec.INT.listOf().fieldOf("integers").forGetter(RefineryRecipeTypeRecipe::integers), Codec.STRING.listOf().fieldOf("strings").forGetter(RefineryRecipeTypeRecipe::strings))
							.apply(builder, RefineryRecipeTypeRecipe::new));
	public static final StreamCodec<RegistryFriendlyByteBuf, RefineryRecipeTypeRecipe> STREAM_CODEC = StreamCodec.of(RefineryRecipeTypeRecipe::toNetwork, RefineryRecipeTypeRecipe::fromNetwork);
	public static final RecipeSerializer<RefineryRecipeTypeRecipe> SERIALIZER = new RecipeSerializer<>(MAP_CODEC, STREAM_CODEC);

	public RefineryRecipeTypeRecipe(List<ItemStackTemplate> outputs, List<Ingredient> recipeItems, List<Integer> integers, List<String> strings) {
		this.outputs = outputs;
		this.recipeItems = recipeItems;
		this.integers = integers;
		this.strings = strings;
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
	public RecipeSerializer<RefineryRecipeTypeRecipe> getSerializer() {
		return SERIALIZER;
	}

	public static class Type implements RecipeType<RefineryRecipeTypeRecipe> {
		private Type() {
		}

		public static final RecipeType<RefineryRecipeTypeRecipe> INSTANCE = new Type();
	}

	private static RefineryRecipeTypeRecipe fromNetwork(RegistryFriendlyByteBuf buf) {
		List<Ingredient> inputs = NonNullList.withSize(buf.readVarInt(), EmptyIngredient.create());
		inputs.replaceAll(ingredients -> Ingredient.CONTENTS_STREAM_CODEC.decode(buf));
		int outputCount = buf.readVarInt();
		List<ItemStackTemplate> outputs = new ArrayList<>(outputCount);
		for (int i = 0; i < outputCount; i++) {
			outputs.add(ItemStackTemplate.STREAM_CODEC.decode(buf));
		}
		List<Integer> numbers = NonNullList.withSize(buf.readVarInt(), 0);
		numbers.replaceAll(num -> buf.readVarInt());
		List<String> strings = NonNullList.withSize(buf.readVarInt(), "");
		strings.replaceAll(string -> buf.readUtf());
		return new RefineryRecipeTypeRecipe(outputs, inputs, numbers, strings);
	}

	private static void toNetwork(RegistryFriendlyByteBuf buf, RefineryRecipeTypeRecipe recipe) {
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
		buf.writeVarInt(recipe.integers().size());
		for (Integer num : recipe.integers()) {
			buf.writeVarInt(num);
		}
		buf.writeVarInt(recipe.strings().size());
		for (String string : recipe.strings()) {
			buf.writeUtf(string);
		}
	}
}