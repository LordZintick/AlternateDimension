/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package com.lordkittycat.alternate_dimension.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.core.registries.Registries;

import com.lordkittycat.alternate_dimension.potion.*;
import com.lordkittycat.alternate_dimension.AlternateDimensionMod;

public class AlternateDimensionModMobEffects {
	public static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(Registries.MOB_EFFECT, AlternateDimensionMod.MODID);
	public static final DeferredHolder<MobEffect, MobEffect> CHARGED = REGISTRY.register("charged", ChargedMobEffect::new);
	public static final DeferredHolder<MobEffect, MobEffect> ACROBATIC_EFFECT = REGISTRY.register("acrobatic_effect", AcrobaticEffectMobEffect::new);
	public static final DeferredHolder<MobEffect, MobEffect> OSMIUM_HEART_EFFECT = REGISTRY.register("osmium_heart_effect", OsmiumHeartEffectMobEffect::new);
	public static final DeferredHolder<MobEffect, MobEffect> HELIUM_HEART_EFFECT = REGISTRY.register("helium_heart_effect", HeliumHeartEffectMobEffect::new);
	public static final DeferredHolder<MobEffect, MobEffect> LIGHT_FOOTED_EFFECT = REGISTRY.register("light_footed_effect", LightFootedEffectMobEffect::new);
	public static final DeferredHolder<MobEffect, MobEffect> REACHING_EFFECT = REGISTRY.register("reaching_effect", ReachingEffectMobEffect::new);
	public static final DeferredHolder<MobEffect, MobEffect> POWER_EFFECT = REGISTRY.register("power_effect", PowerEffectMobEffect::new);
	public static final DeferredHolder<MobEffect, MobEffect> CURSED_EFFECT = REGISTRY.register("cursed_effect", CursedEffectMobEffect::new);
	public static final DeferredHolder<MobEffect, MobEffect> MAGNETISM = REGISTRY.register("magnetism", MagnetismMobEffect::new);
	public static final DeferredHolder<MobEffect, MobEffect> BLESSED = REGISTRY.register("blessed", BlessedMobEffect::new);
	public static final DeferredHolder<MobEffect, MobEffect> IMMUNITY = REGISTRY.register("immunity", ImmunityMobEffect::new);
	public static final DeferredHolder<MobEffect, MobEffect> JUMPY = REGISTRY.register("jumpy", JumpyMobEffect::new);
	public static final DeferredHolder<MobEffect, MobEffect> BLINDING_LIGHT = REGISTRY.register("blinding_light", BlindingLightMobEffect::new);
	public static final DeferredHolder<MobEffect, MobEffect> SHROOMY_INFECTION = REGISTRY.register("shroomy_infection", ShroomyInfectionMobEffect::new);
}