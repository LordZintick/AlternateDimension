package com.lordkittycat.alternate_dimension.item;

import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.InteractionResult;
import net.minecraft.network.chat.Component;

import java.util.List;

import com.lordkittycat.alternate_dimension.procedures.CreateVerticalTunnelProcedure;

public class InstavatorItem extends Item {
	public InstavatorItem() {
		super(new Item.Properties());
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack itemstack, Item.TooltipContext context, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, context, list, flag);
		list.add(Component.translatable("item.alternate_dimension.instavator.description_0"));
		list.add(Component.translatable("item.alternate_dimension.instavator.description_1"));
		list.add(Component.translatable("item.alternate_dimension.instavator.description_2"));
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		super.useOn(context);
		CreateVerticalTunnelProcedure.execute(context.getLevel(), context.getClickedPos().getX(), context.getClickedPos().getY(), context.getClickedPos().getZ(), context.getClickedFace());
		return InteractionResult.SUCCESS;
	}
}