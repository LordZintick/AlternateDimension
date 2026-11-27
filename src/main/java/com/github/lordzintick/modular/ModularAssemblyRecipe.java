package com.github.lordzintick.modular;

import com.github.lordzintick.init.AlternateDimensionModComponents;
import com.github.lordzintick.init.AlternateDimensionModRecipeSerializers;
import com.github.lordzintick.init.AlternateDimensionModRegistries;
import com.github.lordzintick.modular.parts.GearArchetype;
import com.github.lordzintick.modular.parts.GearPartType;
import com.github.lordzintick.modular.parts.GearPartsComponent;
import com.github.lordzintick.modular.util.ComponentMerger;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModItems;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.*;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
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
    public static final ModularAssemblyRecipe INSTANCE = new ModularAssemblyRecipe();
    public static final MapCodec<ModularAssemblyRecipe> MAP_CODEC = MapCodec.unit(() -> INSTANCE);
    public static final StreamCodec<RegistryFriendlyByteBuf, ModularAssemblyRecipe> STREAM_CODEC = StreamCodec.unit(INSTANCE);
    private RegistryAccess registryAccess;

    @Override
    public boolean matches(CraftingInput input, Level level) {
        registryAccess = level.registryAccess();
        return mergeInputs(input, level.registryAccess()).isPresent();
    }

    @Override
    public ItemStack assemble(CraftingInput input) {
        RegistryAccess registries = this.registryAccess != null
                ? this.registryAccess
                : (ServerLifecycleHooks.getCurrentServer() != null
                ? ServerLifecycleHooks.getCurrentServer().registryAccess()
                : null);

        Optional<AssemblyResult> merged = mergeInputs(input, registries);
        if (merged.isEmpty()) return ItemStack.EMPTY;

        ItemStack result = merged.get().existingTool() != null
                ? merged.get().existingTool().copyWithCount(1)
                : new ItemStack(AlternateDimensionModItems.MODULAR_ITEM.get());

        result.remove(DataComponents.TOOL);
        result.remove(DataComponents.ATTRIBUTE_MODIFIERS);
        result.remove(DataComponents.EQUIPPABLE);
        result.remove(DataComponents.FOOD);
        result.remove(DataComponents.ENCHANTMENTS);
        result.remove(DataComponents.DAMAGE_RESISTANT);
        result.remove(DataComponents.GLIDER);
        result.remove(AlternateDimensionModComponents.CURIO_SLOT);

        ComponentMerger.applyMergedComponents(result, new GearPartsComponent(merged.get().combinedParts()));
        return result;
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(CraftingInput input) {
        NonNullList<ItemStack> remaining = super.getRemainingItems(input);

        RegistryAccess registries = registryAccess;
        if (registries == null) return remaining;

        Optional<AssemblyResult> mergedOpt = mergeInputs(input, registries);
        if (mergedOpt.isEmpty()) return remaining;

        for (var refundEntry : mergedOpt.get().overriddenPartsBySlot().entrySet()) {
            int slotIndex = refundEntry.getKey();
            Map.Entry<Holder<GearPartType>, GearPartsComponent.GearPart> oldPart = refundEntry.getValue();

            ItemStack refundedPartStack = new ItemStack(AlternateDimensionModItems.MODULAR_ITEM.get());
            refundedPartStack.set(
                    AlternateDimensionModComponents.GEAR_PARTS,
                    new GearPartsComponent(Map.of(oldPart.getKey(), oldPart.getValue()))
            );
            remaining.set(slotIndex, refundedPartStack);
        }

        return remaining;
    }

    private Optional<AssemblyResult> mergeInputs(CraftingInput input, HolderLookup.Provider registries) {
        Map<Holder<GearPartType>, GearPartsComponent.GearPart> combinedMap = new LinkedHashMap<>();
        ItemStack existingAssembledTool = null;
        int modularItemCount = 0;

        Map<Integer, Map.Entry<Holder<GearPartType>, GearPartsComponent.GearPart>> incomingSingleParts = new LinkedHashMap<>();

        for (int i = 0; i < input.size(); i++) {
            ItemStack stack = input.getItem(i);
            if (stack.isEmpty()) continue;

            if (!stack.is(AlternateDimensionModItems.MODULAR_ITEM.get())) return Optional.empty();

            GearPartsComponent partsComp = stack.get(AlternateDimensionModComponents.GEAR_PARTS);
            if (partsComp == null || partsComp.parts().isEmpty()) return Optional.empty();

            modularItemCount++;

            if (partsComp.parts().size() > 1) {
                if (existingAssembledTool != null) return Optional.empty();
                existingAssembledTool = stack;
                combinedMap.putAll(partsComp.parts());
            } else {
                var entry = partsComp.parts().entrySet().iterator().next();
                // Forbid duplicate part types in the same crafting grid
                if (incomingSingleParts.values().stream().anyMatch(e -> e.getKey().equals(entry.getKey()))) {
                    return Optional.empty();
                }
                incomingSingleParts.put(i, entry);
            }
        }

        if (modularItemCount < 2) return Optional.empty();

        Map<Integer, Map.Entry<Holder<GearPartType>, GearPartsComponent.GearPart>> overriddenBySlot = new LinkedHashMap<>();
        for (var slotEntry : incomingSingleParts.entrySet()) {
            int slotIndex = slotEntry.getKey();
            var incomingPart = slotEntry.getValue();

            GearPartsComponent.GearPart replacedPart = combinedMap.put(incomingPart.getKey(), incomingPart.getValue());
            if (replacedPart != null) {
                if (replacedPart.material().is(incomingPart.getValue().material())
                        && replacedPart.durability() >= incomingPart.getValue().durability()) {
                    return Optional.empty();
                }
                overriddenBySlot.put(slotIndex, Map.entry(incomingPart.getKey(), replacedPart));
            }
        }

        Set<Holder<GearPartType>> presentPartTypes = combinedMap.keySet();

        if (!GearPartType.isValidPartCombination(presentPartTypes)) {
            return Optional.empty();
        }

        Registry<GearArchetype> archetypeRegistry = (Registry<GearArchetype>) registries.lookupOrThrow(AlternateDimensionModRegistries.ARCHETYPE_KEY);
        boolean matchesArchetype = archetypeRegistry.listElements()
                .anyMatch(holder -> holder.value().matches(presentPartTypes));

        if (!matchesArchetype) return Optional.empty();

        return Optional.of(new AssemblyResult(combinedMap, existingAssembledTool, overriddenBySlot));
    }

    @Override
    public RecipeSerializer<? extends CustomRecipe> getSerializer() {
        return AlternateDimensionModRecipeSerializers.MODULAR_ASSEMBLY.get();
    }

    private record AssemblyResult(
            Map<Holder<GearPartType>, GearPartsComponent.GearPart> combinedParts,
            @Nullable ItemStack existingTool,
            Map<Integer, Map.Entry<Holder<GearPartType>, GearPartsComponent.GearPart>> overriddenPartsBySlot
    ) {}
}
