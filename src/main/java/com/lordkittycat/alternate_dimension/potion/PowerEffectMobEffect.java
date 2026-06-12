package com.lordkittycat.alternate_dimension.potion;

import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.resources.ResourceLocation;

import com.lordkittycat.alternate_dimension.AlternateDimensionMod;

public class PowerEffectMobEffect extends MobEffect {
	public PowerEffectMobEffect() {
		super(MobEffectCategory.BENEFICIAL, -65536);
		this.addAttributeModifier(Attributes.ATTACK_KNOCKBACK, ResourceLocation.fromNamespaceAndPath(AlternateDimensionMod.MODID, "effect.power_effect_0"), 0.5, AttributeModifier.Operation.ADD_VALUE);
	}
}