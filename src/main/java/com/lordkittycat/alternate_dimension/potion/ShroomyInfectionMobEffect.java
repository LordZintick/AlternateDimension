package com.lordkittycat.alternate_dimension.potion;

import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.ParticleOptions;

import com.lordkittycat.alternate_dimension.AlternateDimensionMod;

public class ShroomyInfectionMobEffect extends MobEffect {
	public ShroomyInfectionMobEffect() {
		super(MobEffectCategory.HARMFUL, -16776961);
		this.withSoundOnAdded(BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.shroomlight.place")));
		this.addAttributeModifier(Attributes.MOVEMENT_SPEED, ResourceLocation.fromNamespaceAndPath(AlternateDimensionMod.MODID, "effect.shroomy_infection_0"), -0.2, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
		this.addAttributeModifier(Attributes.ATTACK_DAMAGE, ResourceLocation.fromNamespaceAndPath(AlternateDimensionMod.MODID, "effect.shroomy_infection_1"), -0.2, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
	}

	@Override
	public ParticleOptions createParticleOptions(MobEffectInstance mobEffectInstance) {
		return ParticleTypes.CLOUD;
	}
}