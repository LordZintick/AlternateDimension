package com.github.lordzintick.item.backpack;

import com.github.lordzintick.init.AlternateDimensionModComponents;
import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.ContainerUser;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.function.Supplier;

public class BackpackContainer implements Container {
    private final Supplier<ItemStack> stackSupplier;
    private final NonNullList<ItemStack> items;
    private final int size;

    public BackpackContainer(Supplier<ItemStack> stackSupplier, int expectedSlots) {
        this.stackSupplier = stackSupplier;
        this.size = expectedSlots;

        ItemStack stack = stackSupplier.get();
        BackpackInventoryComponent comp = stack.get(AlternateDimensionModComponents.BACKPACK_INVENTORY);
        if (comp != null) {
            this.items = comp.copyItemsToList();
        } else {
            this.items = NonNullList.withSize(expectedSlots, ItemStack.EMPTY);
        }
    }

    private void saveToStack() {
        ItemStack stack = stackSupplier.get();
        if (stack.isEmpty()) return;

        BackpackInventoryComponent current = stack.get(AlternateDimensionModComponents.BACKPACK_INVENTORY);
        int capacity = current != null ? current.slots() : size;
        BackpackInventoryComponent updated = new BackpackInventoryComponent(capacity).withItems(items);
        stack.set(AlternateDimensionModComponents.BACKPACK_INVENTORY, updated);
    }

    @Override
    public int getContainerSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack stack : items) {
            if (!stack.isEmpty()) return false;
        }
        return true;
    }

    @Override
    public ItemStack getItem(int slot) {
        return slot >= 0 && slot < size ? items.get(slot) : ItemStack.EMPTY;
    }

    @Override
    public ItemStack removeItem(int slot, int amount) {
        ItemStack result = ContainerHelper.removeItem(items, slot, amount);
        if (!result.isEmpty()) {
            setChanged();
        }
        return result;
    }

    @Override
    public ItemStack removeItemNoUpdate(int slot) {
        return ContainerHelper.takeItem(items, slot);
    }

    @Override
    public void setItem(int slot, ItemStack stack) {
        if (slot >= 0 && slot < size) {
            items.set(slot, stack);
            stack.limitSize(getMaxStackSize(stack));
            setChanged();
        }
    }

    @Override
    public void setChanged() {
        saveToStack();
    }

    @Override
    public boolean stillValid(Player player) {
        ItemStack stack = stackSupplier.get();
        return !stack.isEmpty() && stack.has(AlternateDimensionModComponents.BACKPACK_INVENTORY);
    }

    @Override
    public void stopOpen(ContainerUser containerUser) {
        saveToStack();
    }

    @Override
    public void clearContent() {
        items.clear();
        setChanged();
    }
}
