package com.github.lordzintick.magic;

import com.github.lordzintick.magic.loader.Spell;
import com.github.lordzintick.magic.loader.SpellLoader;
import com.lordkittycat.alternate_dimension.AlternateDimensionMod;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModItems;
import com.mojang.serialization.MapCodec;
import net.minecraft.ChatFormatting;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.CustomModelData;
import net.minecraft.world.item.component.ItemLore;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;

public class ScrollRecipe extends CustomRecipe {
    public static final ScrollRecipe INSTANCE = new ScrollRecipe();
    public static final MapCodec<ScrollRecipe> MAP_CODEC;
    public static final StreamCodec<RegistryFriendlyByteBuf, ScrollRecipe> STREAM_CODEC;
    public static final RecipeSerializer<ScrollRecipe> SERIALIZER;
    public ArrayList<ItemStack> ingredients = new ArrayList<>();
    private boolean disenchant = false;

    public static ItemStack createScroll(Identifier spellID) {
        ItemStack result = AlternateDimensionModItems.SCROLL.toStack();
        Spell spell = SpellLoader.SPELLS.getByID(spellID);
        List<Float> floats = List.of(750001f);
        List<Boolean> booleans = List.of();
        List<String> strings = List.of(spellID.toString());
        List<Integer> integers = List.of();

        assert spell != null;
        result.set(DataComponents.CUSTOM_NAME, Component.literal("Scroll (" + spell.displayName + ") (" + spell.manaCost + " mana)").copy().withStyle(ChatFormatting.RESET, ChatFormatting.LIGHT_PURPLE));
        result.set(DataComponents.CUSTOM_MODEL_DATA, new CustomModelData(floats, booleans, strings, integers));
        result.set(DataComponents.LORE, new ItemLore(List.of(
                Component.literal("A magical scroll that can cast a certain spell!"),
                Component.literal("One-Time use"),
                Component.literal("Spell: "),
                Component.literal(spell.displayName + " (costs " + spell.manaCost + " mana)"),
                Component.literal(spell.description)
        )));
        return result;
    }

    public static ItemStackTemplate createScrollTemplate(Identifier spellID) {
        DataComponentPatch.Builder compPatch = DataComponentPatch.builder();
        Spell spell = SpellLoader.SPELLS.getByID(spellID);
        List<Float> floats = List.of(750001f);
        List<Boolean> booleans = List.of();
        List<String> strings = List.of(spellID.toString());
        List<Integer> integers = List.of();

        assert spell != null;
        compPatch.set(DataComponents.CUSTOM_NAME, Component.literal("Scroll (" + spell.displayName + ") (" + spell.manaCost + " mana)").copy().withStyle(ChatFormatting.RESET, ChatFormatting.LIGHT_PURPLE));
        compPatch.set(DataComponents.CUSTOM_MODEL_DATA, new CustomModelData(floats, booleans, strings, integers));
        compPatch.set(DataComponents.LORE, new ItemLore(List.of(
                Component.literal("A magical scroll that can cast a certain spell!"),
                Component.literal("One-Time use"),
                Component.literal("Spell: "),
                Component.literal(spell.displayName + " (costs " + spell.manaCost + " mana)"),
                Component.literal(spell.description)
        )));
        return new ItemStackTemplate(AlternateDimensionModItems.SCROLL, compPatch.build());
    }

    public static ItemStack tryCreateScroll(Identifier spellID) throws IllegalArgumentException {
        ItemStack result = AlternateDimensionModItems.SCROLL.toStack();
        Spell spell = SpellLoader.SPELLS.getByID(spellID);
        List<Float> floats = List.of(750001f);
        List<Boolean> booleans = List.of();
        List<String> strings = List.of(spellID.toString());
        List<Integer> integers = List.of();

        if (spell != null) {
            result.set(DataComponents.CUSTOM_NAME, Component.literal("Scroll (" + spell.displayName + ") (" + spell.manaCost + " mana)").copy().withStyle(ChatFormatting.RESET, ChatFormatting.LIGHT_PURPLE));
            result.set(DataComponents.CUSTOM_MODEL_DATA, new CustomModelData(floats, booleans, strings, integers));
            result.set(DataComponents.LORE, new ItemLore(List.of(
                    Component.literal("A magical scroll that can cast a certain spell!"),
                    Component.literal("One-Time use"),
                    Component.literal("Spell: "),
                    Component.literal(spell.displayName + " (costs " + spell.manaCost + " mana)"),
                    Component.literal(spell.description)
            )));
            return result;
        } else {
            throw new IllegalArgumentException();
        }
    }

    private void updateIngredients(RecipeInput input) {
        ingredients.clear();
        for (int i = 0; i < input.size(); i++) {
            ItemStack stack = input.getItem(i);
            if (stack.getItem() != Items.AIR && stack.getItem() != AlternateDimensionModItems.CLOTH.get()) {
                ingredients.add(stack);
            }
        }
    }

    @Override
    public boolean matches(CraftingInput input, Level world) {
        boolean hasPaper = false;
        boolean hasIngredient = false;
        updateIngredients(input);
        ItemStack stick = ItemStack.EMPTY;
        for (int i = 0; i < input.size(); i++) {
            ItemStack stack = input.getItem(i);
            if (stack.getItem() == AlternateDimensionModItems.CLOTH.get()) {
                hasPaper = true;
                stick = stack;
            } else if (stack.getItem() != Items.AIR) {
                hasIngredient = true;
            }
        }
        if (!hasIngredient) {
            disenchant = MagicManager.checkIfMagicalItem(stick);
        } else {
            disenchant = false;
        }
        return hasPaper && (hasIngredient || disenchant);
    }

    @Override
    public ItemStack assemble(CraftingInput input) {
        if (!disenchant) {
            updateIngredients(input);
            ArrayList<Identifier> ingredientIDs = new ArrayList<>();
            for (ItemStack ingredient : ingredients) {
                ingredientIDs.add(BuiltInRegistries.ITEM.getKey(ingredient.getItem()));
            }
            Spell spell = SpellLoader.SPELLS.getByIngredients(ingredientIDs);
            if (spell == null) {
                AlternateDimensionMod.LOGGER.warn("Invalid ingredients, ignoring");
                return ItemStack.EMPTY;
            }
            Identifier spellID = spell.id;
            return createScroll(spellID);
        } else {
            ItemStack paper = AlternateDimensionModItems.CLOTH.toStack();
            paper.set(DataComponents.CUSTOM_MODEL_DATA, new CustomModelData(List.of(), List.of(), List.of("disenchanted"), List.of()));
            return paper;
        }
    }

    static {
        MAP_CODEC = MapCodec.unit(INSTANCE);
        STREAM_CODEC = StreamCodec.unit(INSTANCE);
        SERIALIZER = new RecipeSerializer<>(MAP_CODEC, STREAM_CODEC);
    }

    @Override
    public RecipeSerializer<? extends CustomRecipe> getSerializer() {
        return SERIALIZER;
    }

    public static final class Type implements RecipeType<ScrollRecipe> {
        private Type() {}
        public static final Type INSTANCE = new Type();
    }
}
