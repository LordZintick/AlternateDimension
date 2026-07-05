package com.github.lordzintick.init;

import com.github.lordzintick.item.ring.RingEffectComponent;
import com.github.lordzintick.item.ring.RingItem;
import com.lordkittycat.alternate_dimension.AlternateDimensionMod;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModItems;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModJeiPlugin;
import com.lordkittycat.alternate_dimension.jei_recipes.ImbunementTableRecipeRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.core.Holder;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.ArrayList;
import java.util.List;

@JeiPlugin
public class AlternateDimensionDynamicRecipesJEIPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(AlternateDimensionMod.MODID, "dynamic_recipes_plugin");
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        List<ImbunementTableRecipeRecipe> ringRecipes = new ArrayList<>();
        final List<DeferredItem<Item>> rings = List.of(AlternateDimensionModItems.WOODEN_RING, AlternateDimensionModItems.ALUMINUM_RING, AlternateDimensionModItems.TITANIUM_RING, AlternateDimensionModItems.MYTHRIL_RING);

        rings.forEach(ring -> RingItem.RING_INGREDIENTS.forEach((ingredientID, effect) -> {
            ItemStack ingredientItem = BuiltInRegistries.ITEM.getOrThrow(ResourceKey.create(BuiltInRegistries.ITEM.key(), ingredientID)).getDefaultInstance();
            ItemStack outputRing = ring.toStack();
            outputRing.set(AlternateDimensionModComponents.RING_EFFECT, new RingEffectComponent(effect));
            ringRecipes.add(new ImbunementTableRecipeRecipe(List.of(outputRing), NonNullList.of(
                    Ingredient.EMPTY,
                    Ingredient.of(ingredientItem),
                    Ingredient.of(ring.get().getDefaultInstance())
            )));
        }));

        registration.addRecipes(AlternateDimensionModJeiPlugin.ImbunementTableRecipe_Type, ringRecipes);
    }
}
