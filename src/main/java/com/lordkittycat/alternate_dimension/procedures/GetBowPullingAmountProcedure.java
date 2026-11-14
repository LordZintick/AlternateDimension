package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

public class GetBowPullingAmountProcedure {
	public static double execute(Entity entity, ItemStack itemstack) {
		if (entity == null)
			return 0;
		return !((entity instanceof LivingEntity _entUseItem0 ? _entUseItem0.getUseItem() : ItemStack.EMPTY).getItem() == itemstack.getItem())
				? 0
				: entity instanceof LivingEntity living ? ((float) (itemstack.getUseDuration(living) - living.getUseItemRemainingTicks()) / 20f) : 0;
	}
}