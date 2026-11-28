package com.github.lordzintick.item.backpack;

import com.github.lordzintick.init.AlternateDimensionModComponents;
import com.github.lordzintick.init.AlternateDimensionModMenus;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.Container;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerInput;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class BackpackMenu extends AbstractContainerMenu {
    private final Container backpackContainer;
    private final int slotCount;
    private final int rows;

    public BackpackMenu(int containerId, Inventory playerInv, RegistryFriendlyByteBuf buf) {
        this(containerId, playerInv, buf.readVarInt());
    }

    public BackpackMenu(int containerId, Inventory playerInv, int slotCount) {
        super(AlternateDimensionModMenus.BACKPACK.get(), containerId);
        this.slotCount = slotCount;
        this.rows = Mth.clamp(Mth.ceil(slotCount / 9.0), 1, 6);

        this.backpackContainer = new BackpackContainer(
                () -> playerInv.player.getItemBySlot(EquipmentSlot.CHEST),
                slotCount
        );

        backpackContainer.startOpen(playerInv.player);

        for (int index = 0; index < slotCount; index++) {
            int row = index / 9;
            int col = index % 9;
            this.addSlot(new Slot(backpackContainer, index, 8 + col * 18, 18 + row * 18) {
                @Override
                public boolean mayPlace(ItemStack stack) {
                    return !stack.has(AlternateDimensionModComponents.BACKPACK_INVENTORY)
                            && !stack.has(DataComponents.CONTAINER);
                }
            });
        }

        int yOffset = (this.rows - 4) * 18;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(new Slot(playerInv, col + row * 9 + 9, 8 + col * 18, 103 + row * 18 + yOffset));
            }
        }
        for (int col = 0; col < 9; col++) {
            this.addSlot(new Slot(playerInv, col, 8 + col * 18, 161 + yOffset));
        }
    }

    public int getRows() {
        return rows;
    }

    @Override
    public void clicked(int slotId, int button, ContainerInput containerInput, Player player) {
        if (slotId >= 0 && slotId < this.slots.size()) {
            ItemStack target = this.slots.get(slotId).getItem();
            if (target == player.getItemBySlot(EquipmentSlot.CHEST)) {
                return;
            }
        }
        super.clicked(slotId, button, containerInput, player);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        Slot slot = this.slots.get(index);
        if (!slot.hasItem()) return ItemStack.EMPTY;

        ItemStack stack = slot.getItem();
        ItemStack copy = stack.copy();

        if (index < slotCount) {
            if (!this.moveItemStackTo(stack, slotCount, this.slots.size(), true)) {
                return ItemStack.EMPTY;
            }
        } else {
            if (!this.moveItemStackTo(stack, 0, slotCount, false)) {
                return ItemStack.EMPTY;
            }
        }

        if (stack.isEmpty()) {
            slot.setByPlayer(ItemStack.EMPTY);
        } else {
            slot.setChanged();
        }
        return copy;
    }

    @Override
    public boolean stillValid(Player player) {
        return backpackContainer.stillValid(player);
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        backpackContainer.stopOpen(player);
    }
}
