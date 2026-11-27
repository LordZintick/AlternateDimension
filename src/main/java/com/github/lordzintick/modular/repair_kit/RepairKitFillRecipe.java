package com.github.lordzintick.modular.repair_kit;

import com.github.lordzintick.init.AlternateDimensionModComponents;
import com.github.lordzintick.init.AlternateDimensionModRecipeSerializers;
import com.github.lordzintick.init.AlternateDimensionModRegistries;
import com.github.lordzintick.modular.parts.GearMaterial;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RepairKitFillRecipe extends CustomRecipe {
    private static final RepairKitFillRecipe INSTANCE = new RepairKitFillRecipe();
    public static final MapCodec<RepairKitFillRecipe> MAP_CODEC = MapCodec.unit(INSTANCE);
    public static final StreamCodec<RegistryFriendlyByteBuf, RepairKitFillRecipe> STREAM_CODEC = StreamCodec.unit(INSTANCE);
    private RegistryAccess registryAccess;

    @Override
    public boolean matches(CraftingInput input, Level level) {
        registryAccess = level.registryAccess();
        return findFillMatch(input, level.registryAccess()).isPresent();
    }

    @Override
    public ItemStack assemble(CraftingInput input) {
        if (registryAccess == null) return ItemStack.EMPTY;

        var matchOpt = findFillMatch(input, registryAccess);
        if (matchOpt.isEmpty()) return ItemStack.EMPTY;

        FillMatch match = matchOpt.get();
        ItemStack result = match.kitStack().copyWithCount(1);
        result.set(
                AlternateDimensionModComponents.REPAIR_KIT_DATA,
                new RepairKitDataComponent(Optional.of(match.material()), match.newAmount())
        );
        return result;
    }

    private Optional<FillMatch> findFillMatch(CraftingInput input, HolderLookup.Provider registries) {
        ItemStack kitStack = ItemStack.EMPTY;
        List<ItemStack> matItems = new ArrayList<>();

        for (int i = 0; i < input.size(); i++) {
            ItemStack stack = input.getItem(i);
            if (stack.isEmpty()) continue;

            if (stack.getItem() instanceof RepairKitItem) {
                if (!kitStack.isEmpty()) return Optional.empty();
                kitStack = stack;
            } else {
                matItems.add(stack);
            }
        }

        if (kitStack.isEmpty() || matItems.isEmpty()) return Optional.empty();

        RepairKitItem kitItem = (RepairKitItem) kitStack.getItem();
        RepairKitDataComponent currentData = kitStack.getOrDefault(AlternateDimensionModComponents.REPAIR_KIT_DATA, RepairKitDataComponent.EMPTY);

        if (currentData.amount() + matItems.size() > kitItem.getMaxCapacity()) {
            return Optional.empty();
        }

        Registry<GearMaterial> matRegistry = (Registry<GearMaterial>) registries.lookupOrThrow(AlternateDimensionModRegistries.MATERIAL_KEY);
        Holder<GearMaterial> targetMaterial = currentData.material().orElse(null);

        for (ItemStack matStack : matItems) {
            Optional<Holder.Reference<GearMaterial>> found = matRegistry.listElements()
                    .filter(h -> h.value().ingredient().test(matStack))
                    .findFirst();

            if (found.isEmpty()) return Optional.empty();
            if (targetMaterial == null) {
                targetMaterial = found.get();
            } else if (!targetMaterial.equals(found.get())) {
                return Optional.empty();
            }
        }

        return Optional.of(new FillMatch(kitStack, targetMaterial, currentData.amount() + matItems.size()));
    }

    @Override
    public RecipeSerializer<? extends CustomRecipe> getSerializer() {
        return AlternateDimensionModRecipeSerializers.REPAIR_KIT_FILL.get();
    }

    private record FillMatch(ItemStack kitStack, Holder<GearMaterial> material, int newAmount) {}
}
