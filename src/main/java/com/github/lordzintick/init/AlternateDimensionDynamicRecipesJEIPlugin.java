package com.github.lordzintick.init;

import com.github.lordzintick.item.ring.RingEffectComponent;
import com.github.lordzintick.item.ring.RingItem;
import com.lordkittycat.alternate_dimension.AlternateDimensionMod;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModItems;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModJeiPlugin;
import com.lordkittycat.alternate_dimension.jei_recipes.EmptyIngredient;
import com.lordkittycat.alternate_dimension.jei_recipes.ManaMystifierRecipeTypeRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.core.Holder;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
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
        final List<DeferredItem<Item>> rings = List.of(AlternateDimensionModItems.WOODEN_RING, AlternateDimensionModItems.ALUMINUM_RING, AlternateDimensionModItems.TITANIUM_RING, AlternateDimensionModItems.MYTHRIL_RING);

        rings.forEach(ring -> RingItem.RING_INGREDIENTS.forEach((ingredientID, effect) -> {
            Item ingredientItem = BuiltInRegistries.ITEM.getOrThrow(ResourceKey.create(BuiltInRegistries.ITEM.key(), ingredientID)).value();
            ItemStackTemplate outputRing = new ItemStackTemplate(ring);
            outputRing.apply(DataComponentPatch.builder()
                            .set(AlternateDimensionModComponents.RING_EFFECT.get(), new RingEffectComponent(effect))
                    .build());
            ringRecipes.add(new ManaMystifierRecipeTypeRecipe(List.of(outputRing), List.of(
                    Ingredient.of(ring.get()),
                    Ingredient.of(ingredientItem),
                    EmptyIngredient.create()
            ),
            List.of(1, 1)));
        }));

        registration.addRecipes(AlternateDimensionModJeiPlugin.ManaMystifierRecipeType_Type, ringRecipes);
    }
}
