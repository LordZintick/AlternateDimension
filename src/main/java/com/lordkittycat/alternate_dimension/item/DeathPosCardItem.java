package com.lordkittycat.alternate_dimension.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.chat.Component;

import java.util.function.Consumer;

import com.lordkittycat.alternate_dimension.procedures.ConfigureDeathPosCardProcedure;

public class DeathPosCardItem extends Item {
	public DeathPosCardItem(Item.Properties properties) {
		super(properties.stacksTo(1));
	}

	@Override
	public void appendHoverText(ItemStack itemstack, Item.TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> componentConsumer, TooltipFlag flag) {
		super.appendHoverText(itemstack, context, tooltipDisplay, componentConsumer, flag);
		componentConsumer.accept(Component.translatable("item.alternate_dimension.position_card.description_0"));
		if (itemstack.has(DataComponents.CUSTOM_DATA) && itemstack.get(DataComponents.CUSTOM_DATA).contains("posX")) {
			CompoundTag data = itemstack.get(DataComponents.CUSTOM_DATA).copyTag();
			componentConsumer.accept(Component.literal("Position: X: " + data.getDoubleOr("posX", 0) + " Y: " + data.getDoubleOr("posY", 0) + " Z: " + data.getDoubleOr("posZ", 0))
					.withStyle(ChatFormatting.GRAY));
			componentConsumer.accept(Component.literal("Death #" +  (int) Math.round(data.getDoubleOr("number", 1)))
					.withStyle(ChatFormatting.GRAY));
		} else {
			componentConsumer.accept(Component.literal("This only works if you have died. Craft this after you have died to set it to that death position.")
					.withStyle(ChatFormatting.GRAY));
		}
	}

	@Override
	public void onCraftedBy(ItemStack itemstack, Player entity) {
		super.onCraftedBy(itemstack, entity);
		ConfigureDeathPosCardProcedure.execute(entity, itemstack);
	}

	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return slotChanged && !oldStack.equals(newStack);
	}
}