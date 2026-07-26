package com.lordkittycat.alternate_dimension.item;

import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.chat.Component;

import java.util.function.Consumer;

import com.lordkittycat.alternate_dimension.procedures.PlayEnchantSoundProcedure;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModItems;

public class ActivatedManaStoneItem extends Item {
	public ActivatedManaStoneItem(Item.Properties properties) {
		super(properties.rarity(Rarity.UNCOMMON));
	}

	@Override
	public ItemStackTemplate getCraftingRemainder(ItemInstance itemInstance) {
		return new ItemStackTemplate(AlternateDimensionModItems.DORMANT_MANA_STONE.get());
	}

	@Override
	public void appendHoverText(ItemStack itemstack, Item.TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> componentConsumer, TooltipFlag flag) {
		super.appendHoverText(itemstack, context, tooltipDisplay, componentConsumer, flag);
		componentConsumer.accept(Component.translatable("item.alternate_dimension.activated_mana_stone.description_0"));
	}

	@Override
	public void onCraftedBy(ItemStack itemstack, Player entity) {
		super.onCraftedBy(itemstack, entity);
		PlayEnchantSoundProcedure.execute(entity.level(), entity.getX(), entity.getY(), entity.getZ());
	}

	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return slotChanged && !oldStack.equals(newStack);
	}
}