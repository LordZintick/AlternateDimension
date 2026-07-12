package com.lordkittycat.alternate_dimension.item;

import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.resources.Identifier;
import net.minecraft.network.chat.Component;

import java.util.function.Consumer;

import com.lordkittycat.alternate_dimension.AlternateDimensionMod;

public class AdventurersPetItem extends Item {
	public AdventurersPetItem(Item.Properties properties) {
		super(properties.stacksTo(1)
				.attributes(ItemAttributeModifiers.builder()
						.add(Attributes.MOVEMENT_SPEED, new AttributeModifier(Identifier.fromNamespaceAndPath(AlternateDimensionMod.MODID, "adventurers_pet_0"), 0.5, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), EquipmentSlotGroup.ANY)
						.add(Attributes.STEP_HEIGHT, new AttributeModifier(Identifier.fromNamespaceAndPath(AlternateDimensionMod.MODID, "adventurers_pet_1"), 1, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.ANY).build()));
	}

	@Override
	public ItemStackTemplate getCraftingRemainder(ItemInstance itemInstance) {
		return new ItemStackTemplate(this);
	}

	@Override
	public void appendHoverText(ItemStack itemstack, Item.TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> componentConsumer, TooltipFlag flag) {
		super.appendHoverText(itemstack, context, tooltipDisplay, componentConsumer, flag);
		componentConsumer.accept(Component.translatable("item.alternate_dimension.adventurers_pet.description_0"));
		componentConsumer.accept(Component.translatable("item.alternate_dimension.adventurers_pet.description_1"));
	}

	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return slotChanged && !oldStack.equals(newStack);
	}
}