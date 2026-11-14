package com.lordkittycat.alternate_dimension.potion;

import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.particles.ParticleTypes;

import com.lordkittycat.alternate_dimension.AlternateDimensionMod;

public class ShroomyInfectionMobEffect extends MobEffect {
	public ShroomyInfectionMobEffect() {
		super(MobEffectCategory.HARMFUL, -16776961, mobEffectInstance -> ParticleTypes.CLOUD);
		this.withSoundOnAdded(BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("block.shroomlight.place")));
		this.addAttributeModifier(Attributes.MOVEMENT_SPEED, Identifier.fromNamespaceAndPath(AlternateDimensionMod.MODID, "effect.shroomy_infection_0"), -0.2, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
		this.addAttributeModifier(Attributes.ATTACK_DAMAGE, Identifier.fromNamespaceAndPath(AlternateDimensionMod.MODID, "effect.shroomy_infection_1"), -0.2, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
	}
}