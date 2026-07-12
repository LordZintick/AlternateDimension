package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.server.level.ServerLevel;

import com.lordkittycat.alternate_dimension.init.AlternateDimensionModItems;

public class CheckForChlorineProcedure {
	public static void execute(LevelAccessor world, Entity entity, Entity sourceentity) {
		if (entity == null || sourceentity == null)
			return;
		if ((sourceentity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem() == AlternateDimensionModItems.CHLORINE_RAPIER.get()) {
			{
				Entity _ent = entity;
				if (_ent.level() instanceof ServerLevel _serverLevel) {
					_ent.hurtServer(_serverLevel, new DamageSource(world.holderOrThrow(DamageTypes.MOB_ATTACK)), 10);
				}
			}
		}
	}
}