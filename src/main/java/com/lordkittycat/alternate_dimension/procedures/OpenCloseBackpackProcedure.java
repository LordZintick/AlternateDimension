package com.lordkittycat.alternate_dimension.procedures;

import net.neoforged.neoforge.transfer.item.ItemUtil;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.ResourceHandler;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.MenuProvider;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import io.netty.buffer.Unpooled;

import com.lordkittycat.alternate_dimension.world.inventory.EnchantedBackpackGUIMenu;
import com.lordkittycat.alternate_dimension.world.inventory.CheesyBackpackGUIMenu;
import com.lordkittycat.alternate_dimension.world.inventory.BackpackGUIMenu;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModItems;
import com.lordkittycat.alternate_dimension.AlternateDimensionMod;

public class OpenCloseBackpackProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof Player _plr0 && _plr0.containerMenu instanceof BackpackGUIMenu || entity instanceof Player _plr1 && _plr1.containerMenu instanceof CheesyBackpackGUIMenu
				|| entity instanceof Player _plr2 && _plr2.containerMenu instanceof EnchantedBackpackGUIMenu) {
			if (entity instanceof Player _player)
				_player.closeContainer();
		} else {
			if (entity instanceof Player player13) {
				ResourceHandler<ItemResource> inventory13 = AlternateDimensionMod.CuriosApiHelper.getCuriosInventory(player13);
				if (inventory13 != null) {
					for (int i = 0; i < inventory13.size(); i++) {
						ItemStack itemstackiterator = ItemUtil.getStack(inventory13, i);
						if (itemstackiterator.getItem() == AlternateDimensionModItems.BACKPACK.get()) {
							if (entity instanceof ServerPlayer _ent) {
								BlockPos _bpos = BlockPos.containing(x, y, z);
								_ent.openMenu(new MenuProvider() {
									@Override
									public Component getDisplayName() {
										return Component.literal("BackpackGUI");
									}

									@Override
									public boolean shouldTriggerClientSideContainerClosingOnOpen() {
										return false;
									}

									@Override
									public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
										return new BackpackGUIMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
									}
								}, _bpos);
							}
							break;
						} else if (itemstackiterator.getItem() == AlternateDimensionModItems.CHEESY_BACKPACK.get()) {
							if (entity instanceof ServerPlayer _ent) {
								BlockPos _bpos = BlockPos.containing(x, y, z);
								_ent.openMenu(new MenuProvider() {
									@Override
									public Component getDisplayName() {
										return Component.literal("CheesyBackpackGUI");
									}

									@Override
									public boolean shouldTriggerClientSideContainerClosingOnOpen() {
										return false;
									}

									@Override
									public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
										return new CheesyBackpackGUIMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
									}
								}, _bpos);
							}
							break;
						} else if (itemstackiterator.getItem() == AlternateDimensionModItems.ENCHANTED_BACKPACK.get()) {
							if (entity instanceof ServerPlayer _ent) {
								BlockPos _bpos = BlockPos.containing(x, y, z);
								_ent.openMenu(new MenuProvider() {
									@Override
									public Component getDisplayName() {
										return Component.literal("EnchantedBackpackGUI");
									}

									@Override
									public boolean shouldTriggerClientSideContainerClosingOnOpen() {
										return false;
									}

									@Override
									public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
										return new EnchantedBackpackGUIMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
									}
								}, _bpos);
							}
							break;
						}
					}
				}
			}
		}
	}
}