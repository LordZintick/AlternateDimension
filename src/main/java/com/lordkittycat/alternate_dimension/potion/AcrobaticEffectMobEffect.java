package com.lordkittycat.alternate_dimension.potion;

import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.resources.Identifier;

import com.lordkittycat.alternate_dimension.AlternateDimensionMod;

public class AcrobaticEffectMobEffect extends MobEffect {
	public AcrobaticEffectMobEffect() {
		super(MobEffectCategory.BENEFICIAL, -16773442);
		this.addAttributeModifier(Attributes.STEP_HEIGHT, Identifier.fromNamespaceAndPath(AlternateDimensionMod.MODID, "effect.acrobatic_effect_0"), 2, AttributeModifier.Operation.ADD_VALUE);
	}
}