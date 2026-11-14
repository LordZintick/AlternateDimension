package com.lordkittycat.alternate_dimension.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.InteractionResult;
import net.minecraft.network.chat.Component;

import java.util.function.Consumer;

import com.lordkittycat.alternate_dimension.procedures.SetStoredPositionToProcedure;

public class PositionCardItem extends Item {
	public PositionCardItem(Item.Properties properties) {
		super(properties.stacksTo(1));
	}

	@Override
	public void appendHoverText(ItemStack itemstack, Item.TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> componentConsumer, TooltipFlag flag) {
		super.appendHoverText(itemstack, context, tooltipDisplay, componentConsumer, flag);
		componentConsumer.accept(Component.translatable("item.alternate_dimension.position_card.description_0"));
		if (itemstack.has(DataComponents.CUSTOM_DATA) && itemstack.get(DataComponents.CUSTOM_DATA).contains("targetX")) {
			CompoundTag data = itemstack.get(DataComponents.CUSTOM_DATA).copyTag();
			componentConsumer.accept(Component.literal("Position: X: " + data.getDoubleOr("targetX", 0) + " Y: " + data.getDoubleOr("targetY", 0) + " Z: " + data.getDoubleOr("targetZ", 0)).withStyle(ChatFormatting.GRAY));
		}
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		super.useOn(context);
		SetStoredPositionToProcedure.execute(context.getLevel(), context.getClickedPos().getX(), context.getClickedPos().getY(), context.getClickedPos().getZ(), context.getPlayer(), context.getItemInHand());
		return InteractionResult.SUCCESS;
	}

	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return slotChanged && !oldStack.equals(newStack);
	}
}