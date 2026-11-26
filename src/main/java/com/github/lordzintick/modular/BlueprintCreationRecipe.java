package com.github.lordzintick.modular;

import com.github.lordzintick.init.AlternateDimensionModComponents;
import com.github.lordzintick.init.AlternateDimensionModRecipeSerializers;
import com.github.lordzintick.init.AlternateDimensionModRegistries;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModItems;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.server.ServerLifecycleHooks;

import java.util.Optional;

public class BlueprintCreationRecipe extends CustomRecipe {
    public static final BlueprintCreationRecipe INSTANCE = new BlueprintCreationRecipe();
    public static final MapCodec<BlueprintCreationRecipe> MAP_CODEC = MapCodec.unit(() -> INSTANCE);
    public static final StreamCodec<RegistryFriendlyByteBuf, BlueprintCreationRecipe> STREAM_CODEC = StreamCodec.unit(INSTANCE);
    private RegistryAccess registryAccess;

    @Override
    public boolean matches(CraftingInput input, Level level) {
        registryAccess = level.registryAccess();
        return findMatchingPart(input, level.registryAccess()).isPresent();
    }

    @Override
    public ItemStack assemble(CraftingInput input) {
        if (registryAccess == null && ServerLifecycleHooks.getCurrentServer() == null) return ItemStack.EMPTY;
        RegistryAccess registries = registryAccess != null ? registryAccess :
                ServerLifecycleHooks.getCurrentServer().registryAccess();

        Optional<Holder.Reference<GearPartType>> match = findMatchingPart(input, registries);
        if (match.isEmpty()) return ItemStack.EMPTY;

        Holder<GearPartType> partHolder = match.get();
        int cost = partHolder.value().pattern().map(BlueprintPattern::materialCost).orElse(1);

        ItemStack result = new ItemStack(AlternateDimensionModItems.BLUEPRINT.get());
        result.set(AlternateDimensionModComponents.BLUEPRINT_DATA.get(), new BlueprintDataComponent(partHolder, cost));
        return result;
    }

    private Optional<Holder.Reference<GearPartType>> findMatchingPart(CraftingInput input, RegistryAccess registries) {
        Registry<GearPartType> partRegistry = registries.lookupOrThrow(AlternateDimensionModRegistries.PART_TYPE_KEY);
        return partRegistry.listElements()
                .filter(holder -> holder.value().pattern().map(bp -> bp.matches(input)).orElse(false))
                .findFirst();
    }

    @Override
    public RecipeSerializer<? extends CustomRecipe> getSerializer() {
        return AlternateDimensionModRecipeSerializers.BLUEPRINT_CREATION.get();
    }
}
