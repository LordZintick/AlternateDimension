package com.lordkittycat.alternate_dimension.item;

import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.item.Item;
import net.minecraft.network.chat.Component;
import net.minecraft.core.component.DataComponents;

import java.util.function.Consumer;

public class MortarAndPestleItem extends Item {
	public MortarAndPestleItem(Item.Properties properties) {
		super(properties.durability(100).setNoCombineRepair());
	}

	@Override
	public ItemStackTemplate getCraftingRemainder(ItemInstance itemInstance) {
		ItemStack retval = new ItemStack(this);
		retval.setDamageValue(itemInstance.getOrDefault(DataComponents.DAMAGE, 0) + 1);
		if (retval.getDamageValue() >= retval.getMaxDamage()) {
			return null;
		}
		return ItemStackTemplate.fromNonEmptyStack(retval);
	}

	@Override
	public void appendHoverText(ItemStack itemstack, Item.TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> componentConsumer, TooltipFlag flag) {
		super.appendHoverText(itemstack, context, tooltipDisplay, componentConsumer, flag);
		componentConsumer.accept(Component.translatable("item.alternate_dimension.mortar_and_pestle.description_0"));
		componentConsumer.accept(Component.translatable("item.alternate_dimension.mortar_and_pestle.description_1"));
	}

	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return slotChanged && !oldStack.equals(newStack);
	}
}