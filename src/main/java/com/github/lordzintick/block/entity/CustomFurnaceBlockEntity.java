package com.github.lordzintick.block.entity;

import com.github.lordzintick.block.CustomFurnaceBlock;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.FuelValues;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.TagValueOutput;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.common.CommonHooks;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class CustomFurnaceBlockEntity extends BlockEntity implements WorldlyContainer {
    private final RecipeManager.CachedCheck<SingleRecipeInput, SmeltingRecipe> quickCheck =
            RecipeManager.createCheck(RecipeType.SMELTING);

    // 3 slots: 0 = Input, 1 = Fuel, 2 = Output
    private final NonNullList<ItemStack> items = NonNullList.withSize(3, ItemStack.EMPTY);

    // Vanilla furnace tracking values
    public int litTime;
    int litDuration;
    int cookingProgress;
    int cookingTotalTime = 200; // Default 10 seconds cooking time

    // Connects internal data properties directly to the Vanilla Menu sync array
    public final ContainerData dataAccess = new ContainerData() {
        @Override
        public int get(int index) {
            return switch (index) {
                case 0 -> CustomFurnaceBlockEntity.this.litTime;
                case 1 -> CustomFurnaceBlockEntity.this.litDuration;
                case 2 -> CustomFurnaceBlockEntity.this.cookingProgress;
                case 3 -> CustomFurnaceBlockEntity.this.cookingTotalTime;
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0 -> CustomFurnaceBlockEntity.this.litTime = value;
                case 1 -> CustomFurnaceBlockEntity.this.litDuration = value;
                case 2 -> CustomFurnaceBlockEntity.this.cookingProgress = value;
                case 3 -> CustomFurnaceBlockEntity.this.cookingTotalTime = value;
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    };

    public CustomFurnaceBlockEntity(BlockEntityType<?> bet, BlockPos pos, BlockState state) {
        super(bet, pos, state);
    }

    // --- Container Methods ---
    @Override
    public int getContainerSize() { return 3; }

    @Override
    public boolean isEmpty() {
        for (ItemStack itemstack : this.items) {
            if (!itemstack.isEmpty()) return false;
        }
        return true;
    }

    @Override
    public ItemStack getItem(int slot) { return this.items.get(slot); }

    @Override
    public ItemStack removeItem(int slot, int amount) {
        return ContainerHelper.removeItem(this.items, slot, amount);
    }

    @Override
    public ItemStack removeItemNoUpdate(int slot) {
        return ContainerHelper.takeItem(this.items, slot);
    }

    @Override
    public void setItem(int slot, ItemStack stack) {
        this.items.set(slot, stack);
        if (stack.getCount() > this.getMaxStackSize()) {
            stack.setCount(this.getMaxStackSize());
        }
        this.setChanged();
    }

    @Override
    public boolean stillValid(Player player) {
        return Container.stillValidBlockEntity(this, player);
    }

    // --- Hopper & Automation Support (WorldlyContainer) ---
    @Override
    public int[] getSlotsForFace(Direction side) {
        if (side == Direction.DOWN) return new int[]{2}; // Extract from bottom
        if (side == Direction.UP) return new int[]{0};   // Insert to top
        return new int[]{1};                             // Fuel from sides
    }

    @Override
    public boolean canPlaceItemThroughFace(int i, ItemStack itemStack, @Nullable Direction direction) {
        return this.canPlaceItem(i, itemStack);
    }

    @Override
    public boolean canPlaceItem(int slot, ItemStack stack) {
        if (slot == 2) return false;
        if (slot == 1 && level != null) return stack.getBurnTime(null, level.fuelValues()) > 0;
        return true;
    }

    @Override
    public boolean canTakeItemThroughFace(int slot, ItemStack stack, Direction side) {
        return slot == 2;
    }

    @Override
    public void clearContent() { this.items.clear(); }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        ContainerHelper.loadAllItems(input, this.items);
        this.litTime = input.getIntOr("BurnTime", 0);
        this.cookingProgress = input.getIntOr("CookTime", 0);
        this.cookingTotalTime = input.getIntOr("CookTimeTotal", 0);
        this.litDuration = input.getIntOr("LitDuration", 0);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        ContainerHelper.saveAllItems(output, this.items);
        output.putInt("BurnTime", this.litTime);
        output.putInt("CookTime", this.cookingProgress);
        output.putInt("CookTimeTotal", this.cookingTotalTime);
        output.putInt("LitDuration", this.litDuration);
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return this.saveWithFullMetadata(registries);
    }

    public static void serverTick(ServerLevel level, BlockPos pos, BlockState state, CustomFurnaceBlockEntity furnace) {
        boolean wasLit = furnace.litTime > 0;
        boolean changed = false;

        // 1. Tick down remaining burn time if the furnace is currently on fire
        if (furnace.litTime > 0) {
            furnace.litTime--;
        }

        ItemStack inputStack = furnace.getItem(0); // Slot 0 = Input
        ItemStack fuelStack = furnace.getItem(1);  // Slot 1 = Fuel

        // 2. Check if we have an item to cook and if it matches a valid smelting recipe
        boolean hasInput = !inputStack.isEmpty();
        boolean hasFuel = !fuelStack.isEmpty();

        // Wrap our current input stack into a SingleRecipeInput container format for the 1.21.1 recipe engine
        SingleRecipeInput recipeInput = new SingleRecipeInput(inputStack);

        // Query the recipe system for a valid result
        var optionalRecipe = hasInput ? furnace.quickCheck.getRecipeFor(recipeInput, level) : java.util.Optional.<RecipeHolder<SmeltingRecipe>>empty();

        if (furnace.litTime > 0 || (hasFuel && optionalRecipe.isPresent())) {
            if (optionalRecipe.isPresent()) {
                SmeltingRecipe recipe = optionalRecipe.get().value();
                ItemStack recipeResult = recipe.assemble(recipeInput);

                // Check if the recipe output can safely merge into our output slot (Slot 2)
                if (canSmelt(recipeResult, furnace.getItem(2), furnace.getMaxStackSize())) {

                    // If furnace is not lit but has fuel, consume fuel to ignite it
                    if (furnace.litTime <= 0) {
                        furnace.litTime = fuelStack.getBurnTime(null, level.fuelValues());
                        furnace.litDuration = furnace.litTime;

                        if (furnace.litTime > 0) {
                            changed = true;
                            if (hasFuel) {
                                // Safely shrink the fuel item or turn lava buckets into empty buckets
                                Item item = fuelStack.getItem();
                                fuelStack.shrink(1);
                                if (fuelStack.isEmpty()) {
                                    furnace.setItem(1, item.getCraftingRemainder(item.getDefaultInstance()) != null ? Objects.requireNonNull(item.getCraftingRemainder(item.getDefaultInstance())).create() : ItemStack.EMPTY);
                                }
                            }
                        }
                    }

                    // If it's now successfully burning, progress the cook progress bar
                    if (furnace.litTime > 0 && state.getBlock() instanceof CustomFurnaceBlock furnaceBlock) {
                        furnace.cookingTotalTime = recipe.cookingTime();
                        furnace.cookingProgress = (int) Math.floor(Math.min(furnace.cookingProgress + furnaceBlock.getSmeltSpeed(), furnace.cookingTotalTime));

                        // If cooking finishes, output the item
                        if (furnace.cookingProgress >= furnace.cookingTotalTime) {
                            furnace.cookingProgress = 0;
                            smeltItem(furnace, inputStack, furnace.getItem(2), recipeResult);
                            grantXP(level, pos, recipe.experience());
                            changed = true;
                        }
                    }
                } else {
                    furnace.cookingProgress = 0; // Reset progress if output slot is completely blocked/full
                }
            } else {
                furnace.cookingProgress = 0; // Reset progress if input was suddenly removed
            }
        } else if (furnace.litTime <= 0 && furnace.cookingProgress > 0) {
            // Slowly decay cooking progress if out of fuel (just like vanilla behavior)
            furnace.cookingProgress = Mth.clamp(furnace.cookingProgress - 2, 0, furnace.cookingTotalTime);
        }

        // 3. Update the visual blockstate if the furnace changes from active to inactive
        boolean isLit = furnace.litTime > 0;
        if (wasLit != isLit) {
            changed = true;
            state = state.setValue(CustomFurnaceBlock.LIT, isLit);
            level.setBlock(pos, state, 3);
        }

        if (changed) {
            furnace.setChanged();
        }
    }

    // Helper: Verifies if the output slot can accommodate the crafted item
    private static boolean canSmelt(ItemStack recipeOutput, ItemStack outputSlot, int maxStackSize) {
        if (recipeOutput.isEmpty()) return false;
        if (outputSlot.isEmpty()) return true;
        if (!ItemStack.isSameItemSameComponents(outputSlot, recipeOutput)) return false;
        int resultingCount = outputSlot.getCount() + recipeOutput.getCount();
        return resultingCount <= maxStackSize && resultingCount <= outputSlot.getMaxStackSize();
    }

    // Helper: Deducts from input slot and places/merges the item into the output slot
    private static void smeltItem(CustomFurnaceBlockEntity furnace, ItemStack inputSlot, ItemStack outputSlot, ItemStack recipeOutput) {
        if (outputSlot.isEmpty()) {
            furnace.setItem(2, recipeOutput.copy());
        } else if (ItemStack.isSameItemSameComponents(outputSlot, recipeOutput)) {
            outputSlot.grow(recipeOutput.getCount()); // Merge stack size
        }
        inputSlot.shrink(1);

        if (inputSlot.isEmpty()) {
            furnace.setItem(0, ItemStack.EMPTY);
        }
    }

    private static void grantXP(Level level, BlockPos pos, float amount) {
        for (int i = 0; i < (int) amount; i++) {
            ExperienceOrb orb = new ExperienceOrb(level, pos.getX(), pos.getY(), pos.getZ(), 1);
            level.addFreshEntity(orb);
        }
    }
}
