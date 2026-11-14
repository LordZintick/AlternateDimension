package com.lordkittycat.alternate_dimension.item;

import com.github.lordzintick.magic.MagicManager;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class WandItem extends Item {
	public WandItem(Item.Properties properties) {
		super(properties);
	}

	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return slotChanged && !oldStack.equals(newStack);
	}

	@Override
	public InteractionResult use(Level level, Player player, InteractionHand hand) {
		return MagicManager.wandHandler(player, level, hand);
	}
}