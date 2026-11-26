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

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class RepairKitToolRepairRecipe extends CustomRecipe {
    private static final RepairKitToolRepairRecipe INSTANCE = new RepairKitToolRepairRecipe();
    public static final MapCodec<RepairKitToolRepairRecipe> MAP_CODEC = MapCodec.unit(INSTANCE);
    public static final StreamCodec<RegistryFriendlyByteBuf, RepairKitToolRepairRecipe> STREAM_CODEC = StreamCodec.unit(INSTANCE);

    @Override
    public boolean matches(CraftingInput input, Level level) {
        return computeRepair(input).isPresent();
    }

    @Override
    public ItemStack assemble(CraftingInput input) {
        return computeRepair(input).map(RepairResult::repairedTool).orElse(ItemStack.EMPTY);
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(CraftingInput input) {
        NonNullList<ItemStack> remaining = super.getRemainingItems(input);
        Optional<RepairResult> resultOpt = computeRepair(input);
        resultOpt.ifPresent(repairResult -> remaining.set(repairResult.kitSlotIndex(), repairResult.updatedKit()));
        return remaining;
    }

    private Optional<RepairResult> computeRepair(CraftingInput input) {
        ItemStack toolStack = ItemStack.EMPTY;
        ItemStack kitStack = ItemStack.EMPTY;
        int kitSlot = -1;

        for (int i = 0; i < input.size(); i++) {
            ItemStack stack = input.getItem(i);
            if (stack.isEmpty()) continue;

            if (stack.is(AlternateDimensionModItems.MODULAR_ITEM)) {
                if (!toolStack.isEmpty()) return Optional.empty();
                toolStack = stack;
            } else if (stack.getItem() instanceof RepairKitItem) {
                if (!kitStack.isEmpty()) return Optional.empty();
                kitStack = stack;
                kitSlot = i;
            } else {
                return Optional.empty();
            }
        }

        if (toolStack.isEmpty() || kitStack.isEmpty()) return Optional.empty();

        GearPartsComponent partsComp = toolStack.get(AlternateDimensionModComponents.GEAR_PARTS);
        RepairKitDataComponent kitData = kitStack.getOrDefault(AlternateDimensionModComponents.REPAIR_KIT_DATA, RepairKitDataComponent.EMPTY);
        if (partsComp == null || partsComp.parts().size() <= 1 || kitData.isEmpty()) {
            return Optional.empty();
        }

        Holder<GearMaterial> kitMaterial = kitData.material().get();
        RepairKitItem kitItem = (RepairKitItem) kitStack.getItem();

        int totalMissingDurability = partsComp.getTotalMaxDurability() - partsComp.getTotalDurability();

        if (totalMissingDurability <= 0) return Optional.empty();

        int repairPerUnit = Math.max(1, Math.round(100.0f * kitItem.getRepairEfficiency()));
        int unitsNeeded = (int) Math.ceil((double) totalMissingDurability / repairPerUnit);
        int unitsToConsume = Math.min(kitData.amount(), unitsNeeded);
        int totalPointsToRestore = unitsToConsume * repairPerUnit;

        Map<Holder<GearPartType>, GearPartsComponent.GearPart> updatedParts = new LinkedHashMap<>(partsComp.parts());
        int remainingPoints = totalPointsToRestore;

        for (var entry : updatedParts.entrySet()) {
            if (remainingPoints <= 0) break;

            GearPartType partType = entry.getKey().value();
            GearPartsComponent.GearPart partInstance = entry.getValue();

            if (partInstance.material().equals(kitMaterial)) {
                int maxPartDurability = Math.round(partType.baseDurability() * kitMaterial.value().durabilityMultiplier());
                int missing = Math.max(0, maxPartDurability - partInstance.durability());

                if (missing > 0) {
                    int restored = Math.min(missing, remainingPoints);
                    entry.setValue(new GearPartsComponent.GearPart(partInstance.material(), partInstance.durability() + restored));
                    remainingPoints -= restored;
                }
            }
        }

        ItemStack repairedTool = toolStack.copyWithCount(1);
        ComponentMerger.applyMergedComponents(repairedTool, new GearPartsComponent(updatedParts));

        int remainingAmount = kitData.amount() - unitsToConsume;
        ItemStack updatedKit = kitStack.copyWithCount(1);
        updatedKit.set(
                AlternateDimensionModComponents.REPAIR_KIT_DATA,
                remainingAmount > 0
                        ? new RepairKitDataComponent(Optional.of(kitMaterial), remainingAmount)
                        : RepairKitDataComponent.EMPTY
        );

        return Optional.of(new RepairResult(repairedTool, updatedKit, kitSlot));
    }

    @Override
    public RecipeSerializer<? extends CustomRecipe> getSerializer() {
        return AlternateDimensionModRecipeSerializers.REPAIR_KIT_REPAIR.get();
    }

    private record RepairResult(ItemStack repairedTool, ItemStack updatedKit, int kitSlotIndex) {}
}
