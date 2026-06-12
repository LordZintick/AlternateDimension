package com.lordkittycat.alternate_dimension.potion;

import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.resources.ResourceLocation;

import com.lordkittycat.alternate_dimension.AlternateDimensionMod;

public class AcrobaticEffectMobEffect extends MobEffect {
	public AcrobaticEffectMobEffect() {
		super(MobEffectCategory.BENEFICIAL, -16773442);
		this.addAttributeModifier(Attributes.STEP_HEIGHT, ResourceLocation.fromNamespaceAndPath(AlternateDimensionMod.MODID, "effect.acrobatic_effect_0"), 2, AttributeModifier.Operation.ADD_VALUE);
	}
}