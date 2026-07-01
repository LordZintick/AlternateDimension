package com.lordkittycat.alternate_dimension.item;

import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;

import java.util.List;

import com.lordkittycat.alternate_dimension.AlternateDimensionMod;

public class MinersPetItem extends Item {
	public MinersPetItem() {
		super(new Item.Properties().stacksTo(1).attributes(ItemAttributeModifiers.builder()
				.add(Attributes.BLOCK_BREAK_SPEED, new AttributeModifier(ResourceLocation.fromNamespaceAndPath(AlternateDimensionMod.MODID, "miners_pet_0"), 0.5, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), EquipmentSlotGroup.ANY).build()));
	}

	@Override
	public boolean hasCraftingRemainingItem(ItemStack stack) {
		return true;
	}

	@Override
	public ItemStack getCraftingRemainingItem(ItemStack itemstack) {
		return new ItemStack(this);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack itemstack, Item.TooltipContext context, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, context, list, flag);
		list.add(Component.translatable("item.alternate_dimension.miners_pet.description_0"));
		list.add(Component.translatable("item.alternate_dimension.miners_pet.description_1"));
	}
}