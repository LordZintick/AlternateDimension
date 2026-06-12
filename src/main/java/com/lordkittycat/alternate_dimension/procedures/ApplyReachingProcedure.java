package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;

import com.lordkittycat.alternate_dimension.init.AlternateDimensionModMobEffects;

public class ApplyReachingProcedure {
	public static void execute(Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		itemstack.shrink(1);
		if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
			_entity.addEffect(new MobEffectInstance(AlternateDimensionModMobEffects.REACHING_EFFECT, 3600, 0));
	}
}