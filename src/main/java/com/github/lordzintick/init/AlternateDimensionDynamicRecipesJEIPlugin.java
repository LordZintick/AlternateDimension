package com.github.lordzintick.init;

import com.github.lordzintick.item.ring.RingEffectComponent;
import com.github.lordzintick.item.ring.RingItem;
import com.github.lordzintick.magic.ScrollRecipe;
import com.github.lordzintick.magic.WandRecipe;
import com.github.lordzintick.magic.loader.SpellLoader;
import com.github.lordzintick.modular.BlueprintDataComponent;
import com.github.lordzintick.modular.GearMaterial;
import com.github.lordzintick.modular.GearPartType;
import com.github.lordzintick.modular.GearPartsComponent;
import com.lordkittycat.alternate_dimension.AlternateDimensionMod;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModItems;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModJeiPlugin;
import com.lordkittycat.alternate_dimension.jei_recipes.EmptyIngredient;
import com.lordkittycat.alternate_dimension.jei_recipes.ManaMystifierRecipeTypeRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.ingredients.subtypes.ISubtypeInterpreter;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.ISubtypeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.Holder;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
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
import net.neoforged.neoforge.common.crafting.DataComponentIngredient;
import net.neoforged.neoforge.registries.DeferredItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@JeiPlugin
public class AlternateDimensionDynamicRecipesJEIPlugin implements IModPlugin {
    private static Logger LOGGER = LoggerFactory.getLogger("Alternate Dimension x JEI Plugin");

    @Override
    public Identifier getPluginUid() {
        return Identifier.fromNamespaceAndPath(AlternateDimensionMod.MODID, "dynamic_recipes_plugin");
    }

    @Override
    public void registerItemSubtypes(ISubtypeRegistration registration) {
        registration.registerSubtypeInterpreter(
                VanillaTypes.ITEM_STACK,
                AlternateDimensionModItems.BLUEPRINT.get(),
                (stack, context) -> {
                    BlueprintDataComponent bp = stack.get(AlternateDimensionModComponents.BLUEPRINT_DATA.get());
                    if (bp == null) return null;
                    return bp.partType().unwrapKey().map(k -> k.identifier().toString()).orElse("unknown")
                            + ":" + bp.materialCost();
                }
        );

        registration.registerSubtypeInterpreter(
                VanillaTypes.ITEM_STACK,
                AlternateDimensionModItems.MODULAR_ITEM.get(),
                (stack, context) -> {
                    GearPartsComponent parts = stack.get(AlternateDimensionModComponents.GEAR_PARTS.get());
                    if (parts == null || parts.parts().isEmpty()) return null;

                    return parts.sortedByZIndex().stream()
                            .map(e -> e.getKey().unwrapKey().map(k -> k.identifier().toString()).orElse("?")
                                    + "=" + e.getValue().unwrapKey().map(k -> k.identifier().toString()).orElse("?"))
                            .collect(Collectors.joining(";"));
                }
        );
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

        ClientLevel level = Minecraft.getInstance().level;
        if (level == null) return;

        RegistryAccess registries = level.registryAccess();
        var partRegistryOpt = registries.lookup(AlternateDimensionModRegistries.PART_TYPE_KEY);
        var matRegistryOpt = registries.lookup(AlternateDimensionModRegistries.MATERIAL_KEY);
        if (partRegistryOpt.isEmpty() || matRegistryOpt.isEmpty()) return;

        List<Holder.Reference<GearPartType>> allParts = partRegistryOpt.get().listElements().toList();
        List<Holder.Reference<GearMaterial>> allMaterials = matRegistryOpt.get().listElements().toList();

        List<RecipeHolder<CraftingRecipe>> jeiCraftingRecipes = new ArrayList<>();

        for (Holder.Reference<GearPartType> partHolder : allParts) {
            GearPartType part = partHolder.value();
            Identifier partId = partHolder.key().identifier();

            part.pattern().ifPresent(bpPattern -> {
                ItemStack blueprintOutput = new ItemStack(AlternateDimensionModItems.BLUEPRINT.get());
                blueprintOutput.set(
                        AlternateDimensionModComponents.BLUEPRINT_DATA.get(),
                        new BlueprintDataComponent(partHolder, bpPattern.materialCost())
                );

                Map<Character, Ingredient> charKey = new LinkedHashMap<>();
                for (var keyEntry : bpPattern.key().entrySet()) {
                    if (!keyEntry.getKey().isEmpty()) {
                        charKey.put(keyEntry.getKey().charAt(0), keyEntry.getValue());
                    }
                }

                ShapedRecipePattern shapedPattern = ShapedRecipePattern.of(
                        charKey,
                        bpPattern.pattern()
                );

                ShapedRecipe shapedRecipe = new ShapedRecipe(
                        new Recipe.CommonInfo(true),
                        new CraftingRecipe.CraftingBookInfo(CraftingBookCategory.EQUIPMENT, "blueprint_creation"),
                        shapedPattern,
                        ItemStackTemplate.fromNonEmptyStack(blueprintOutput)
                );

                ResourceKey<Recipe<?>> recipeKey = ResourceKey.create(
                        Registries.RECIPE,
                        Identifier.fromNamespaceAndPath(AlternateDimensionMod.MODID, "jei_blueprint_" + partId.getNamespace() + "_" + partId.getPath())
                );
                jeiCraftingRecipes.add(new RecipeHolder<>(recipeKey, shapedRecipe));

                ItemStack blueprintInputStack = blueprintOutput.copy();
                Ingredient blueprintIngredient = DataComponentIngredient.of(true, blueprintInputStack);

                for (Holder.Reference<GearMaterial> matHolder : allMaterials) {
                    if (!part.allowsMaterial(matHolder)) continue;

                    GearMaterial mat = matHolder.value();
                    Identifier matId = matHolder.key().identifier();

                    List<Ingredient> inputs = new ArrayList<>(1 + bpPattern.materialCost());
                    inputs.add(blueprintIngredient);
                    for (int i = 0; i < bpPattern.materialCost(); i++) {
                        inputs.add(mat.ingredient());
                    }

                    ItemStack partOutput = new ItemStack(AlternateDimensionModItems.MODULAR_ITEM.get());
                    partOutput.set(
                            AlternateDimensionModComponents.GEAR_PARTS.get(),
                            new GearPartsComponent(Map.of(partHolder, matHolder))
                    );

                    ShapelessRecipe shapelessRecipe = new ShapelessRecipe(
                            new Recipe.CommonInfo(true),
                            new CraftingRecipe.CraftingBookInfo(CraftingBookCategory.EQUIPMENT, "part_creation"),
                            ItemStackTemplate.fromNonEmptyStack(partOutput),
                            inputs
                    );

                    ResourceKey<Recipe<?>> partRecipeKey = ResourceKey.create(
                            Registries.RECIPE,
                            Identifier.fromNamespaceAndPath(
                                    AlternateDimensionMod.MODID,
                                    "jei_part_" + partId.getPath() + "_" + matId.getNamespace() + "_" + matId.getPath()
                            )
                    );
                    jeiCraftingRecipes.add(new RecipeHolder<>(partRecipeKey, shapelessRecipe));
                }
            });
        }

        LOGGER.info("Registering {} JEI modular recipes", jeiCraftingRecipes.size());
        registration.addRecipes(RecipeTypes.CRAFTING, jeiCraftingRecipes);
    }
}
