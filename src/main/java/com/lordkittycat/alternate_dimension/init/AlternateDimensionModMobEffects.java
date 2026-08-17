/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package com.lordkittycat.alternate_dimension.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.core.registries.Registries;

import com.lordkittycat.alternate_dimension.potion.ShroomyInfectionMobEffect;
import com.lordkittycat.alternate_dimension.potion.LightFootedEffectMobEffect;
import com.lordkittycat.alternate_dimension.potion.BlindingLightMobEffect;
import com.lordkittycat.alternate_dimension.potion.AcrobaticEffectMobEffect;
import com.lordkittycat.alternate_dimension.AlternateDimensionMod;

public class AlternateDimensionModMobEffects {
	public static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(Registries.MOB_EFFECT, AlternateDimensionMod.MODID);
	public static final DeferredHolder<MobEffect, MobEffect> ACROBATIC_EFFECT = REGISTRY.register("acrobatic_effect", AcrobaticEffectMobEffect::new);
	public static final DeferredHolder<MobEffect, MobEffect> LIGHT_FOOTED_EFFECT = REGISTRY.register("light_footed_effect", LightFootedEffectMobEffect::new);
	public static final DeferredHolder<MobEffect, MobEffect> BLINDING_LIGHT = REGISTRY.register("blinding_light", BlindingLightMobEffect::new);
	public static final DeferredHolder<MobEffect, MobEffect> SHROOMY_INFECTION = REGISTRY.register("shroomy_infection", ShroomyInfectionMobEffect::new);
}