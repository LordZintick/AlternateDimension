package com.lordkittycat.alternate_dimension.item;

import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.tags.TagKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.Identifier;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;

import java.util.function.Consumer;

import com.lordkittycat.alternate_dimension.procedures.BeetleSwordAbilityProcedure;
import com.lordkittycat.alternate_dimension.AlternateDimensionMod;

public class BeetleSwordItem extends Item {
	private static final ToolMaterial TOOL_MATERIAL = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 2256, 8f, 0, 10, TagKey.create(Registries.ITEM, Identifier.parse("alternate_dimension:beetle_sword_repair_items")));

	public BeetleSwordItem(Item.Properties properties) {
		super(properties.sword(TOOL_MATERIAL, 6.5f, -2.4f).rarity(Rarity.RARE).fireResistant()
				.attributes(ItemAttributeModifiers.builder().add(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_ID, 6.5, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
						.add(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_ID, -2.4, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
						.add(Attributes.ARMOR, new AttributeModifier(Identifier.fromNamespaceAndPath(AlternateDimensionMod.MODID, "beetle_sword_0"), 2, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.ANY)
						.add(Attributes.MAX_HEALTH, new AttributeModifier(Identifier.fromNamespaceAndPath(AlternateDimensionMod.MODID, "beetle_sword_1"), 4, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.ANY)
						.add(Attributes.MOVEMENT_SPEED, new AttributeModifier(Identifier.fromNamespaceAndPath(AlternateDimensionMod.MODID, "beetle_sword_2"), 0.03, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.ANY).build()));
	}

	@Override
	public void hurtEnemy(ItemStack itemstack, LivingEntity entity, LivingEntity sourceentity) {
		super.hurtEnemy(itemstack, entity, sourceentity);
		BeetleSwordAbilityProcedure.execute(entity.level(), entity.getX(), entity.getY(), entity.getZ(), sourceentity);
	}

	@Override
	public void appendHoverText(ItemStack itemstack, Item.TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> componentConsumer, TooltipFlag flag) {
		super.appendHoverText(itemstack, context, tooltipDisplay, componentConsumer, flag);
		componentConsumer.accept(Component.translatable("item.alternate_dimension.beetle_sword.description_0"));
		componentConsumer.accept(Component.translatable("item.alternate_dimension.beetle_sword.description_1"));
		componentConsumer.accept(Component.translatable("item.alternate_dimension.beetle_sword.description_2"));
	}
}