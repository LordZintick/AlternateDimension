package com.lordkittycat.alternate_dimension.potion;

import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.resources.ResourceLocation;

import com.lordkittycat.alternate_dimension.AlternateDimensionMod;

public class ReachingEffectMobEffect extends MobEffect {
	public ReachingEffectMobEffect() {
		super(MobEffectCategory.BENEFICIAL, -3145984);
		this.addAttributeModifier(Attributes.BLOCK_INTERACTION_RANGE, ResourceLocation.fromNamespaceAndPath(AlternateDimensionMod.MODID, "effect.reaching_effect_0"), 4, AttributeModifier.Operation.ADD_VALUE);
		this.addAttributeModifier(Attributes.ENTITY_INTERACTION_RANGE, ResourceLocation.fromNamespaceAndPath(AlternateDimensionMod.MODID, "effect.reaching_effect_1"), 4, AttributeModifier.Operation.ADD_VALUE);
	}
}