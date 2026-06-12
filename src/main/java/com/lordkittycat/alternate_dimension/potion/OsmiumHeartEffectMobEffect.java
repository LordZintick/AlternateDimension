package com.lordkittycat.alternate_dimension.potion;

import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.resources.ResourceLocation;

import com.lordkittycat.alternate_dimension.AlternateDimensionMod;

public class OsmiumHeartEffectMobEffect extends MobEffect {
	public OsmiumHeartEffectMobEffect() {
		super(MobEffectCategory.NEUTRAL, -13421773);
		this.addAttributeModifier(Attributes.GRAVITY, ResourceLocation.fromNamespaceAndPath(AlternateDimensionMod.MODID, "effect.osmium_heart_effect_0"), 0.03, AttributeModifier.Operation.ADD_VALUE);
	}
}