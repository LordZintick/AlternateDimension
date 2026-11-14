/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package com.lordkittycat.alternate_dimension.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.core.registries.Registries;

import com.lordkittycat.alternate_dimension.procedures.RemoveInvulnerabilityProcedure;
import com.lordkittycat.alternate_dimension.potion.ShroomyInfectionMobEffect;
import com.lordkittycat.alternate_dimension.potion.LightFootedEffectMobEffect;
import com.lordkittycat.alternate_dimension.potion.GraceEffectMobEffect;
import com.lordkittycat.alternate_dimension.potion.BlindingLightMobEffect;
import com.lordkittycat.alternate_dimension.potion.AcrobaticEffectMobEffect;
import com.lordkittycat.alternate_dimension.AlternateDimensionMod;

@EventBusSubscriber
public class AlternateDimensionModMobEffects {
	public static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(Registries.MOB_EFFECT, AlternateDimensionMod.MODID);
	public static final DeferredHolder<MobEffect, MobEffect> ACROBATIC_EFFECT = REGISTRY.register("acrobatic_effect", AcrobaticEffectMobEffect::new);
	public static final DeferredHolder<MobEffect, MobEffect> LIGHT_FOOTED_EFFECT = REGISTRY.register("light_footed_effect", LightFootedEffectMobEffect::new);
	public static final DeferredHolder<MobEffect, MobEffect> BLINDING_LIGHT = REGISTRY.register("blinding_light", BlindingLightMobEffect::new);
	public static final DeferredHolder<MobEffect, MobEffect> SHROOMY_INFECTION = REGISTRY.register("shroomy_infection", ShroomyInfectionMobEffect::new);
	public static final DeferredHolder<MobEffect, MobEffect> GRACE_EFFECT = REGISTRY.register("grace_effect", GraceEffectMobEffect::new);

	@SubscribeEvent
	public static void onEffectRemoved(MobEffectEvent.Remove event) {
		MobEffectInstance effectInstance = event.getEffectInstance();
		if (effectInstance != null) {
			expireEffects(event.getEntity(), effectInstance);
		}
	}

	@SubscribeEvent
	public static void onEffectExpired(MobEffectEvent.Expired event) {
		MobEffectInstance effectInstance = event.getEffectInstance();
		if (effectInstance != null) {
			expireEffects(event.getEntity(), effectInstance);
		}
	}

	private static void expireEffects(Entity entity, MobEffectInstance effectInstance) {
		if (effectInstance.is(GRACE_EFFECT)) {
			RemoveInvulnerabilityProcedure.execute(entity);
		}
	}
}