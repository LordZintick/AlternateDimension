package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;

import com.lordkittycat.alternate_dimension.init.AlternateDimensionModItems;

public class CheckForSodiumProcedure {
	public static void execute(LevelAccessor world, Entity entity, Entity sourceentity) {
		if (entity == null || sourceentity == null)
			return;
		if ((sourceentity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem() == AlternateDimensionModItems.SODIUM_RAPIER.get()) {
			entity.hurt(new DamageSource(world.holderOrThrow(DamageTypes.MOB_ATTACK)), 10);
		}
	}
}