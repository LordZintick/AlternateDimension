package com.github.lordzintick.modular;

import com.github.lordzintick.init.AlternateDimensionModComponents;
import com.github.lordzintick.init.AlternateDimensionModRecipeSerializers;
import com.github.lordzintick.init.AlternateDimensionModRegistries;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModItems;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.server.ServerLifecycleHooks;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class ModularAssemblyRecipe extends CustomRecipe {
    private RegistryAccess registryAccess;

    @Override
    public boolean matches(CraftingInput input, Level level) {
        registryAccess = level.registryAccess();
        return mergeInputs(input, level.registryAccess()).isPresent();
    }

    @Override
    public ItemStack assemble(CraftingInput input) {
        HolderLookup.Provider registries = this.registryAccess != null
                ? this.registryAccess
                : (ServerLifecycleHooks.getCurrentServer() != null
                ? ServerLifecycleHooks.getCurrentServer().registryAccess()
                : null);

        Optional<AssemblyResult> merged = mergeInputs(input, registries);
        if (merged.isEmpty()) return ItemStack.EMPTY;

        ItemStack result = merged.get().existingTool() != null
                ? merged.get().existingTool().copyWithCount(1)
                : new ItemStack(AlternateDimensionModItems.MODULAR_ITEM.get());

        // Clear previously synthesized components before re-merging
        result.remove(DataComponents.TOOL);
        result.remove(DataComponents.ATTRIBUTE_MODIFIERS);
        result.remove(DataComponents.EQUIPPABLE);
        result.remove(DataComponents.FOOD);
        result.remove(DataComponents.DAMAGE_RESISTANT);
        result.remove(DataComponents.GLIDER);

        ComponentMerger.applyMergedComponents(result, new GearPartsComponent(merged.get().combinedParts()));
        return result;
    }

    private Optional<AssemblyResult> mergeInputs(CraftingInput input, HolderLookup.Provider registries) {
        Map<Holder<GearPartType>, Holder<GearMaterial>> combinedMap = new LinkedHashMap<>();
        ItemStack existingAssembledTool = null;
        int modularItemCount = 0;

        for (int i = 0; i < input.size(); i++) {
            ItemStack stack = input.getItem(i);
            if (stack.isEmpty()) continue;

            if (!stack.is(AlternateDimensionModItems.MODULAR_ITEM.get())) return Optional.empty();

            GearPartsComponent partsComp = stack.get(AlternateDimensionModComponents.GEAR_PARTS.get());
            if (partsComp == null || partsComp.parts().isEmpty()) return Optional.empty();

            modularItemCount++;

            if (partsComp.parts().size() > 1) {
                if (existingAssembledTool != null) return Optional.empty();
                existingAssembledTool = stack;
                combinedMap.putAll(partsComp.parts());
            } else {
                var entry = partsComp.parts().entrySet().iterator().next();
                if (existingAssembledTool == null && combinedMap.containsKey(entry.getKey())) {
                    return Optional.empty();
                }
                combinedMap.put(entry.getKey(), entry.getValue());
            }
        }

        if (modularItemCount < 2) return Optional.empty();

        Set<Holder<GearPartType>> presentPartTypes = combinedMap.keySet();

        // 1. Validate part-level requires_any and incompatible_with rules
        if (!GearPartType.isValidPartCombination(presentPartTypes)) {
            return Optional.empty();
        }

        // 2. Validate that the parts satisfy at least one GearArchetype
        Registry<GearArchetype> archetypeRegistry = registries.getOrThrow(AlternateDimensionModRegistries.ARCHETYPE_KEY).value();
        boolean matchesArchetype = archetypeRegistry.listElements()
                .anyMatch(holder -> holder.value().matches(presentPartTypes));

        if (!matchesArchetype) return Optional.empty();

        return Optional.of(new AssemblyResult(combinedMap, existingAssembledTool));
    }

    @Override
    public RecipeSerializer<? extends CustomRecipe> getSerializer() {
        return AlternateDimensionModRecipeSerializers.MODULAR_ASSEMBLY.get();
    }

    private record AssemblyResult(
            Map<Holder<GearPartType>, Holder<GearMaterial>> combinedParts,
            @Nullable ItemStack existingTool
    ) {}
}
