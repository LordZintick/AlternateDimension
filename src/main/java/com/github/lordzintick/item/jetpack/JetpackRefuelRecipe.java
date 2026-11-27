package com.github.lordzintick.item.jetpack;

import com.github.lordzintick.init.AlternateDimensionModComponents;
import com.github.lordzintick.init.AlternateDimensionModRecipeSerializers;
import com.mojang.serialization.MapCodec;
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

public class JetpackRefuelRecipe extends CustomRecipe {
    private static final JetpackRefuelRecipe INSTANCE = new JetpackRefuelRecipe();
    public static final MapCodec<JetpackRefuelRecipe> MAP_CODEC = MapCodec.unit(INSTANCE);
    public static final StreamCodec<RegistryFriendlyByteBuf, JetpackRefuelRecipe> STREAM_CODEC = StreamCodec.unit(INSTANCE);

    @Override
    public boolean matches(CraftingInput input, Level level) {
        return computeRefuel(input).isPresent();
    }

    @Override
    public ItemStack assemble(CraftingInput input) {
        return computeRefuel(input).orElse(ItemStack.EMPTY);
    }

    private Optional<ItemStack> computeRefuel(CraftingInput input) {
        ItemStack chestStack = ItemStack.EMPTY;
        List<ItemStack> fuelItems = new ArrayList<>();

        for (int i = 0; i < input.size(); i++) {
            ItemStack stack = input.getItem(i);
            if (stack.isEmpty()) continue;

            if (stack.has(AlternateDimensionModComponents.JETPACK)) {
                if (!chestStack.isEmpty()) return Optional.empty();
                chestStack = stack;
            } else {
                fuelItems.add(stack);
            }
        }

        if (chestStack.isEmpty() || fuelItems.isEmpty()) return Optional.empty();

        JetpackComponent jetpack = chestStack.get(AlternateDimensionModComponents.JETPACK);
        int currentFuel = chestStack.getOrDefault(AlternateDimensionModComponents.JETPACK_FUEL, JetpackFuelComponent.EMPTY).fuel();

        if (currentFuel >= jetpack.fuelCapacity()) return Optional.empty();

        for (ItemStack fuel : fuelItems) {
            if (!fuel.is(jetpack.fuelTag())) return Optional.empty();
        }

        // Prevent adding more fuel items than needed to reach capacity
        int maxItemsNeeded = (int) Math.ceil((double) (jetpack.fuelCapacity() - currentFuel) / jetpack.fuelPerItem());
        if (fuelItems.size() > maxItemsNeeded) return Optional.empty();

        int newFuel = Math.min(jetpack.fuelCapacity(), currentFuel + (fuelItems.size() * jetpack.fuelPerItem()));
        ItemStack result = chestStack.copyWithCount(1);
        result.set(AlternateDimensionModComponents.JETPACK_FUEL, new JetpackFuelComponent(newFuel));
        return Optional.of(result);
    }

    @Override
    public RecipeSerializer<? extends CustomRecipe> getSerializer() {
        return AlternateDimensionModRecipeSerializers.JETPACK_REFUEL.get();
    }
}
