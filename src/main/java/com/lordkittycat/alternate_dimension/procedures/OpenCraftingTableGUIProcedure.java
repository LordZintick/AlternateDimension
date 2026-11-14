package com.lordkittycat.alternate_dimension.procedures;

import com.github.lordzintick.menu.CustomCraftingMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

public class OpenCraftingTableGUIProcedure {
	public static InteractionResult execute(Level world, Player player, BlockPos pos, Block block) {
		if (world.isClientSide()) {
			return InteractionResult.SUCCESS;
		} else {
			player.openMenu(new SimpleMenuProvider((windowId, inventory, p) ->
				new CustomCraftingMenu(windowId, inventory, ContainerLevelAccess.create(world, pos), () -> block), Component.translatable(block.getDescriptionId())));
			return InteractionResult.CONSUME;
		}
	}
}