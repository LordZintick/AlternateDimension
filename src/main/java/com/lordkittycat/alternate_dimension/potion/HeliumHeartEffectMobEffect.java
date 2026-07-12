package com.lordkittycat.alternate_dimension.potion;

import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.resources.Identifier;

import com.lordkittycat.alternate_dimension.AlternateDimensionMod;

public class HeliumHeartEffectMobEffect extends MobEffect {
	public HeliumHeartEffectMobEffect() {
		super(MobEffectCategory.NEUTRAL, -7017985);
		this.addAttributeModifier(Attributes.GRAVITY, Identifier.fromNamespaceAndPath(AlternateDimensionMod.MODID, "effect.helium_heart_effect_0"), -0.03, AttributeModifier.Operation.ADD_VALUE);
	}
}