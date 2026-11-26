package com.github.lordzintick.modular;

import com.github.lordzintick.init.AlternateDimensionModComponents;
import com.github.lordzintick.init.AlternateDimensionModRecipeSerializers;
import com.github.lordzintick.init.AlternateDimensionModRegistries;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModItems;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.*;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
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
    public static final BlueprintPartRecipe INSTANCE = new BlueprintPartRecipe();
    public static final MapCodec<BlueprintPartRecipe> MAP_CODEC = MapCodec.unit(() -> INSTANCE);
    public static final StreamCodec<RegistryFriendlyByteBuf, BlueprintPartRecipe> STREAM_CODEC = StreamCodec.unit(INSTANCE);

    private RegistryAccess registryAccess;

    @Override
    public boolean matches(CraftingInput input, Level level) {
        registryAccess = level.registryAccess();
        return findMatch(input, registryAccess).isPresent();
    }

    @Override
    public ItemStack assemble(CraftingInput input) {
        RegistryAccess registries = this.registryAccess != null
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

    private Optional<MatchResult> findMatch(CraftingInput input, RegistryAccess registries) {
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

        Registry<GearMaterial> materialRegistry = registries.lookupOrThrow(AlternateDimensionModRegistries.MATERIAL_KEY);
        Holder<GearMaterial> matchedMaterial = null;

        for (ItemStack matStack : materialStacks) {
            Optional<Holder.Reference<GearMaterial>> found = materialRegistry.listElements()
                    .filter(holder -> holder.value().ingredient().test(matStack))
                    .findFirst();

            if (found.isEmpty()) {
                System.out.println("No material with ingredient found!");
                return Optional.empty();
            }
            if (!blueprint.partType().value().allowsMaterial(found.get())) {
                System.out.println("No allowed materials found!");
                continue;
            }
            if (matchedMaterial == null) {
                matchedMaterial = found.get();
            } else if (!matchedMaterial.equals(found.get())) {
                return Optional.empty();
            }
        }

        return matchedMaterial != null
                ? Optional.of(new MatchResult(blueprint.partType(), new GearPartsComponent.GearPart(matchedMaterial,
                    (int) (blueprint.partType().value().baseDurability() * matchedMaterial.value().durabilityMultiplier()))))
                : Optional.empty();
    }

    @Override
    public RecipeSerializer<? extends CustomRecipe> getSerializer() {
        return AlternateDimensionModRecipeSerializers.BLUEPRINT_PART.get();
    }

    private record MatchResult(Holder<GearPartType> partType, GearPartsComponent.GearPart material) {}
}
