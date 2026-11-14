package com.github.lordzintick.init;

import com.github.lordzintick.item.ring.RingEffectComponent;
import com.github.lordzintick.item.ring.RingItem;
import com.github.lordzintick.magic.ScrollRecipe;
import com.github.lordzintick.magic.WandRecipe;
import com.github.lordzintick.magic.loader.SpellLoader;
import com.lordkittycat.alternate_dimension.AlternateDimensionMod;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModItems;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModJeiPlugin;
import com.lordkittycat.alternate_dimension.jei_recipes.EmptyIngredient;
import com.lordkittycat.alternate_dimension.jei_recipes.ManaMystifierRecipeTypeRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Holder;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.ArrayList;
import java.util.List;

@JeiPlugin
public class AlternateDimensionDynamicRecipesJEIPlugin implements IModPlugin {
    @Override
    public Identifier getPluginUid() {
        return Identifier.fromNamespaceAndPath(AlternateDimensionMod.MODID, "dynamic_recipes_plugin");
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        List<ManaMystifierRecipeTypeRecipe> ringRecipes = new ArrayList<>();
        List<RecipeHolder<CraftingRecipe>> wandAndScrollRecipes = new ArrayList<>();
        final List<DeferredItem<Item>> rings = List.of(AlternateDimensionModItems.WOODEN_RING, AlternateDimensionModItems.ALUMINUM_RING, AlternateDimensionModItems.TITANIUM_RING, AlternateDimensionModItems.MYTHRIL_RING);

        rings.forEach(ring -> RingItem.RING_INGREDIENTS.forEach((ingredientID, effectID) -> {
            Item ingredientItem = BuiltInRegistries.ITEM.getOrThrow(ResourceKey.create(BuiltInRegistries.ITEM.key(), ingredientID)).value();
            Holder<MobEffect> effect = BuiltInRegistries.MOB_EFFECT.getOrThrow(ResourceKey.create(BuiltInRegistries.MOB_EFFECT.key(), effectID));
            ItemStackTemplate outputRing = new ItemStackTemplate(ring, DataComponentPatch.builder()
                        .set(AlternateDimensionModComponents.RING_EFFECT.get(), new RingEffectComponent(effect))
                    .build()
            );
            ringRecipes.add(new ManaMystifierRecipeTypeRecipe(List.of(outputRing), List.of(
                    Ingredient.of(ring.get()),
                    Ingredient.of(ingredientItem),
                    EmptyIngredient.create()
            ),
            List.of(1, 1)));
        }));

        SpellLoader.SPELLS.forEach(spell -> {
            List<Ingredient> wandIngredients = new ArrayList<>(spell.ingredients);
            List<Ingredient> scrollIngredients = new ArrayList<>(spell.ingredients);
            wandIngredients.add(Ingredient.of(AlternateDimensionModItems.TOOL_ROD));
            scrollIngredients.add(Ingredient.of(AlternateDimensionModItems.CLOTH));

            wandAndScrollRecipes.add(
                    new RecipeHolder<>(ResourceKey.create(Registries.RECIPE, Identifier.fromNamespaceAndPath(spell.id.getNamespace(), "wand_recipe_" + spell.id.getPath())),
                            new ShapelessRecipe(new Recipe.CommonInfo(false),
                                    new CraftingRecipe.CraftingBookInfo(CraftingBookCategory.EQUIPMENT, "wands"),
                                    WandRecipe.createWandTemplate(spell.id),
                                    wandIngredients
                            )
                    ));
            wandAndScrollRecipes.add(
                    new RecipeHolder<>(ResourceKey.create(Registries.RECIPE, Identifier.fromNamespaceAndPath(spell.id.getNamespace(), "scroll_recipe_" + spell.id.getPath())),
                            new ShapelessRecipe(new Recipe.CommonInfo(false),
                                    new CraftingRecipe.CraftingBookInfo(CraftingBookCategory.EQUIPMENT, "scrolls"),
                                    ScrollRecipe.createScrollTemplate(spell.id),
                                    scrollIngredients
                            )
                    )
            );
        });

        registration.addRecipes(RecipeTypes.CRAFTING, wandAndScrollRecipes);
        registration.addRecipes(AlternateDimensionModJeiPlugin.ManaMystifierRecipeType_Type, ringRecipes);
    }
}
