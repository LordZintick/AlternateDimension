package com.github.lordzintick.modular;

import com.github.lordzintick.init.AlternateDimensionModComponents;
import com.github.lordzintick.init.AlternateDimensionModRecipeSerializers;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModItems;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.Holder;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

import java.util.Optional;

public class RepairKitEmptyRecipe extends CustomRecipe {
    private static final RepairKitEmptyRecipe INSTANCE = new RepairKitEmptyRecipe();
    public static final MapCodec<RepairKitEmptyRecipe> MAP_CODEC = MapCodec.unit(INSTANCE);
    public static final StreamCodec<RegistryFriendlyByteBuf, RepairKitEmptyRecipe> STREAM_CODEC = StreamCodec.unit(INSTANCE);

    @Override
    public boolean matches(CraftingInput input, Level level) {
        return computeEmpty(input).isPresent();
    }

    @Override
    public ItemStack assemble(CraftingInput input) {
        return computeEmpty(input).map(EmptyResult::resultingKit).orElse(ItemStack.EMPTY);
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(CraftingInput input) {
        NonNullList<ItemStack> remaining = super.getRemainingItems(input);
        Optional<EmptyResult> resultOpt = computeEmpty(input);
        if (resultOpt.isPresent()) {
            remaining.set(resultOpt.get().kitSlotIndex(), resultOpt.get().refundedItems());
        }
        return remaining;
    }

    private Optional<EmptyResult> computeEmpty(CraftingInput input) {
        ItemStack kitStack = ItemStack.EMPTY;
        int kitSlot = -1;

        for (int i = 0; i < input.size(); i++) {
            ItemStack stack = input.getItem(i);
            if (stack.isEmpty()) continue;

            if (stack.getItem() instanceof RepairKitItem) {
                if (!kitStack.isEmpty()) return Optional.empty();
                kitStack = stack;
                kitSlot = i;
            } else {
                return Optional.empty();
            }
        }

        if (kitStack.isEmpty()) return Optional.empty();

        RepairKitDataComponent data = kitStack.getOrDefault(AlternateDimensionModComponents.REPAIR_KIT_DATA, RepairKitDataComponent.EMPTY);
        if (data.isEmpty()) return Optional.empty();

        Holder<GearMaterial> matHolder = data.material().get();
        GearMaterial mat = matHolder.value();

        Optional<Holder<net.minecraft.world.item.Item>> firstItemOpt = mat.ingredient().items().findFirst();
        if (firstItemOpt.isEmpty()) return Optional.empty();

        ItemStack sampleRefund = new ItemStack(firstItemOpt.get());
        int maxStackSize = sampleRefund.getMaxStackSize();
        int amountToRefund = Math.min(data.amount(), maxStackSize);
        int leftoverInKit = data.amount() - amountToRefund;

        sampleRefund.setCount(amountToRefund);

        ItemStack outputKit = kitStack.copyWithCount(1);
        outputKit.set(
                AlternateDimensionModComponents.REPAIR_KIT_DATA,
                leftoverInKit > 0
                        ? new RepairKitDataComponent(Optional.of(matHolder), leftoverInKit)
                        : RepairKitDataComponent.EMPTY
        );

        return Optional.of(new EmptyResult(outputKit, sampleRefund, kitSlot));
    }

    @Override
    public RecipeSerializer<? extends CustomRecipe> getSerializer() {
        return AlternateDimensionModRecipeSerializers.REPAIR_KIT_EMPTY.get();
    }

    private record EmptyResult(ItemStack resultingKit, ItemStack refundedItems, int kitSlotIndex) {}
}
