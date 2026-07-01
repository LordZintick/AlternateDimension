package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.MenuProvider;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.BlockPos;

import io.netty.buffer.Unpooled;

import com.lordkittycat.alternate_dimension.world.inventory.HintBookGUIMenu;
import com.lordkittycat.alternate_dimension.configuration.MainConfigConfiguration;

public class OpenHintBookGUIProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		double totalHints = 0;
		double hint = 0;
		double i = 0;
		for (String stringiterator : MainConfigConfiguration.HINTS.get()) {
			totalHints = totalHints + 1;
		}
		hint = Mth.nextInt(RandomSource.create(), 0, (int) (totalHints - 1));
		for (String stringiterator : MainConfigConfiguration.HINTS.get()) {
			if (i == hint) {
				{
					final String _tagName = "hint";
					final String _tagValue = stringiterator;
					CustomData.update(DataComponents.CUSTOM_DATA, itemstack, tag -> tag.putString(_tagName, _tagValue));
				}
				break;
			}
			i = i + 1;
		}
		if (entity instanceof ServerPlayer _ent) {
			BlockPos _bpos = BlockPos.containing(x, y, z);
			_ent.openMenu(new MenuProvider() {
				@Override
				public Component getDisplayName() {
					return Component.literal("HintBookGUI");
				}

				@Override
				public boolean shouldTriggerClientSideContainerClosingOnOpen() {
					return false;
				}

				@Override
				public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
					return new HintBookGUIMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
				}
			}, _bpos);
		}
	}
}