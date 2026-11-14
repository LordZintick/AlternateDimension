package com.github.lordzintick.menu;

import com.lordkittycat.alternate_dimension.init.AlternateDimensionModBlocks;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public class CustomCraftingMenu extends CraftingMenu {
    private final ContainerLevelAccess levelAccess;
    private final Supplier<Block> target;

    public CustomCraftingMenu(int id, Inventory inventory, ContainerLevelAccess access, Supplier<Block> target) {
        super(id, inventory, access);
        this.levelAccess = access;
        this.target = target;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(levelAccess, player, target.get());
    }
}
