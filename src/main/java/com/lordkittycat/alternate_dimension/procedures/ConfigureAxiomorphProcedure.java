package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;

public class ConfigureAxiomorphProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof LivingEntity _livingEntity1 && _livingEntity1.getAttributes().hasAttribute(Attributes.ATTACK_DAMAGE))
			_livingEntity1.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue((Mth.nextDouble(RandomSource.create(), 1, 10)));
		if (entity instanceof LivingEntity _livingEntity3 && _livingEntity3.getAttributes().hasAttribute(Attributes.MAX_HEALTH))
			_livingEntity3.getAttribute(Attributes.MAX_HEALTH).setBaseValue((Mth.nextDouble(RandomSource.create(), 5, 50)));
		if (entity instanceof LivingEntity _livingEntity5 && _livingEntity5.getAttributes().hasAttribute(Attributes.ARMOR))
			_livingEntity5.getAttribute(Attributes.ARMOR).setBaseValue((Mth.nextDouble(RandomSource.create(), 0, 5)));
		if (entity instanceof LivingEntity _livingEntity7 && _livingEntity7.getAttributes().hasAttribute(Attributes.ARMOR_TOUGHNESS))
			_livingEntity7.getAttribute(Attributes.ARMOR_TOUGHNESS).setBaseValue((Mth.nextDouble(RandomSource.create(), 0, 1)));
		if (entity instanceof LivingEntity _livingEntity9 && _livingEntity9.getAttributes().hasAttribute(Attributes.ATTACK_KNOCKBACK))
			_livingEntity9.getAttribute(Attributes.ATTACK_KNOCKBACK).setBaseValue((Mth.nextDouble(RandomSource.create(), 0, 1)));
		if (entity instanceof LivingEntity _livingEntity11 && _livingEntity11.getAttributes().hasAttribute(Attributes.KNOCKBACK_RESISTANCE))
			_livingEntity11.getAttribute(Attributes.KNOCKBACK_RESISTANCE).setBaseValue((Mth.nextDouble(RandomSource.create(), 0, 1)));
		if (entity instanceof LivingEntity _livingEntity13 && _livingEntity13.getAttributes().hasAttribute(Attributes.MOVEMENT_SPEED))
			_livingEntity13.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue((Mth.nextDouble(RandomSource.create(), 0.15, 0.35)));
		if (entity instanceof LivingEntity _livingEntity15 && _livingEntity15.getAttributes().hasAttribute(Attributes.SCALE))
			_livingEntity15.getAttribute(Attributes.SCALE).setBaseValue((Mth.nextDouble(RandomSource.create(), 0.5, 2)));
		if (entity instanceof LivingEntity _entity)
			_entity.setHealth((float) (entity instanceof LivingEntity _livingEntity16 && _livingEntity16.getAttributes().hasAttribute(Attributes.MAX_HEALTH) ? _livingEntity16.getAttribute(Attributes.MAX_HEALTH).getValue() : 0));
	}
}