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

import com.lordkittycat.alternate_dimension.world.inventory.HuntersPouchGUIMenu;
import com.lordkittycat.alternate_dimension.world.inventory.FruitPouchGUIMenu;
import com.lordkittycat.alternate_dimension.world.inventory.EssencePouchGUIMenu;
import com.lordkittycat.alternate_dimension.world.inventory.ComboPouchGUIMenu;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModItems;
import com.lordkittycat.alternate_dimension.AlternateDimensionMod;

public class OpenClosePouchProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof Player _plr0 && _plr0.containerMenu instanceof EssencePouchGUIMenu || entity instanceof Player _plr1 && _plr1.containerMenu instanceof ComboPouchGUIMenu
				|| entity instanceof Player _plr2 && _plr2.containerMenu instanceof HuntersPouchGUIMenu || entity instanceof Player _plr3 && _plr3.containerMenu instanceof FruitPouchGUIMenu) {
			if (entity instanceof Player _player)
				_player.closeContainer();
		} else {
			if (entity instanceof Player player17) {
				ResourceHandler<ItemResource> inventory17 = AlternateDimensionMod.CuriosApiHelper.getCuriosInventory(player17);
				if (inventory17 != null) {
					for (int i = 0; i < inventory17.size(); i++) {
						ItemStack itemstackiterator = ItemUtil.getStack(inventory17, i);
						if (itemstackiterator.getItem() == AlternateDimensionModItems.ESSENCE_POUCH.get()) {
							if (entity instanceof ServerPlayer _ent) {
								BlockPos _bpos = BlockPos.containing(x, y, z);
								_ent.openMenu(new MenuProvider() {
									@Override
									public Component getDisplayName() {
										return Component.literal("EssencePouchGUI");
									}

									@Override
									public boolean shouldTriggerClientSideContainerClosingOnOpen() {
										return false;
									}

									@Override
									public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
										return new EssencePouchGUIMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
									}
								}, _bpos);
							}
							break;
						} else if (itemstackiterator.getItem() == AlternateDimensionModItems.HUNTERS_POUCH.get()) {
							if (entity instanceof ServerPlayer _ent) {
								BlockPos _bpos = BlockPos.containing(x, y, z);
								_ent.openMenu(new MenuProvider() {
									@Override
									public Component getDisplayName() {
										return Component.literal("HuntersPouchGUI");
									}

									@Override
									public boolean shouldTriggerClientSideContainerClosingOnOpen() {
										return false;
									}

									@Override
									public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
										return new HuntersPouchGUIMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
									}
								}, _bpos);
							}
							break;
						} else if (itemstackiterator.getItem() == AlternateDimensionModItems.FRUIT_POUCH.get()) {
							if (entity instanceof ServerPlayer _ent) {
								BlockPos _bpos = BlockPos.containing(x, y, z);
								_ent.openMenu(new MenuProvider() {
									@Override
									public Component getDisplayName() {
										return Component.literal("FruitPouchGUI");
									}

									@Override
									public boolean shouldTriggerClientSideContainerClosingOnOpen() {
										return false;
									}

									@Override
									public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
										return new FruitPouchGUIMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
									}
								}, _bpos);
							}
							break;
						} else if (itemstackiterator.getItem() == AlternateDimensionModItems.COMBO_POUCH.get()) {
							if (entity instanceof ServerPlayer _ent) {
								BlockPos _bpos = BlockPos.containing(x, y, z);
								_ent.openMenu(new MenuProvider() {
									@Override
									public Component getDisplayName() {
										return Component.literal("ComboPouchGUI");
									}

									@Override
									public boolean shouldTriggerClientSideContainerClosingOnOpen() {
										return false;
									}

									@Override
									public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
										return new ComboPouchGUIMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
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