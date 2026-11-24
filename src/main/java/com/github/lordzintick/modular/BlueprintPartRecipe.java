package com.github.lordzintick.modular;

import com.github.lordzintick.init.AlternateDimensionModComponents;
import com.github.lordzintick.init.AlternateDimensionModRecipeSerializers;
import com.github.lordzintick.init.AlternateDimensionModRegistries;
import com.lordkittycat.alternate_dimension.AlternateDimensionMod;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModItems;
import net.minecraft.core.*;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.server.ServerLifecycleHooks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BlueprintPartRecipe extends CustomRecipe {
    private RegistryAccess registryAccess;

    @Override
    public boolean matches(CraftingInput input, Level level) {
        registryAccess = level.registryAccess();
        return findMatch(input, level.registryAccess()).isPresent();
    }

    @Override
    public ItemStack assemble(CraftingInput input) {
        HolderLookup.Provider registries = this.registryAccess != null
                ? this.registryAccess
                : (ServerLifecycleHooks.getCurrentServer() != null
                ? ServerLifecycleHooks.getCurrentServer().registryAccess()
                : null);

        Optional<MatchResult> match = findMatch(input, registries);
        if (match.isEmpty()) return ItemStack.EMPTY;

        ItemStack result = new ItemStack(AlternateDimensionModItems.MODULAR_ITEM.get());
        GearPartsComponent parts = new GearPartsComponent(Map.of(match.get().partType(), match.get().material()));
        result.set(AlternateDimensionModComponents.GEAR_PARTS.get(), parts);
        return result;
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(CraftingInput input) {
        NonNullList<ItemStack> remaining = super.getRemainingItems(input);

        for (int i = 0; i < input.size(); i++) {
            ItemStack stack = input.getItem(i);
            if (stack.has(AlternateDimensionModComponents.BLUEPRINT_DATA.get())) {
                remaining.set(i, stack.copyWithCount(1));
            }
        }
        return remaining;
    }

    private Optional<MatchResult> findMatch(CraftingInput input, HolderLookup.Provider registries) {
        BlueprintDataComponent blueprint = null;
        List<ItemStack> materialStacks = new ArrayList<>();

        for (int i = 0; i < input.size(); i++) {
            ItemStack stack = input.getItem(i);
            if (stack.isEmpty()) continue;

            BlueprintDataComponent bp = stack.get(AlternateDimensionModComponents.BLUEPRINT_DATA.get());
            if (bp != null) {
                if (blueprint != null) return Optional.empty();
                blueprint = bp;
            } else {
                materialStacks.add(stack);
            }
        }

        if (blueprint == null || materialStacks.size() != blueprint.materialCost()) {
            return Optional.empty();
        }

        Registry<GearMaterial> materialRegistry = registries.getOrThrow(AlternateDimensionModRegistries.MATERIAL_KEY).value();
        Holder<GearMaterial> matchedMaterial = null;

        for (ItemStack matStack : materialStacks) {
            Optional<Holder.Reference<GearMaterial>> found = materialRegistry.listElements()
                    .filter(holder -> holder.value().repairIngredient().test(matStack))
                    .findFirst();

            if (found.isEmpty()) return Optional.empty();
            if (matchedMaterial == null) {
                matchedMaterial = found.get();
            } else if (!matchedMaterial.equals(found.get())) {
                return Optional.empty();
            }
        }

        return matchedMaterial != null
                ? Optional.of(new MatchResult(blueprint.partType(), matchedMaterial))
                : Optional.empty();
    }

    @Override
    public RecipeSerializer<? extends CustomRecipe> getSerializer() {
        return AlternateDimensionModRecipeSerializers.BLUEPRINT_PART.get();
    }

    private record MatchResult(Holder<GearPartType> partType, Holder<GearMaterial> material) {}
}
