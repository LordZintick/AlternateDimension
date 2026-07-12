/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com.lordkittycat.alternate_dimension.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.Registries;

import com.lordkittycat.alternate_dimension.AlternateDimensionMod;

public class AlternateDimensionModSounds {
	public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(Registries.SOUND_EVENT, AlternateDimensionMod.MODID);
	public static final DeferredHolder<SoundEvent, SoundEvent> DRILL_USE = REGISTRY.register("drill_use", () -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("alternate_dimension", "drill_use")));
	public static final DeferredHolder<SoundEvent, SoundEvent> BOSS_BATTLE = REGISTRY.register("boss_battle", () -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("alternate_dimension", "boss_battle")));
	public static final DeferredHolder<SoundEvent, SoundEvent> CREDITS = REGISTRY.register("credits", () -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("alternate_dimension", "credits")));
	public static final DeferredHolder<SoundEvent, SoundEvent> ETHEREAL_LAUGHING = REGISTRY.register("ethereal_laughing", () -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("alternate_dimension", "ethereal_laughing")));
	public static final DeferredHolder<SoundEvent, SoundEvent> ETHEREAL_HURT = REGISTRY.register("ethereal_hurt", () -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("alternate_dimension", "ethereal_hurt")));
	public static final DeferredHolder<SoundEvent, SoundEvent> ETHEREAL_DEATH = REGISTRY.register("ethereal_death", () -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("alternate_dimension", "ethereal_death")));
	public static final DeferredHolder<SoundEvent, SoundEvent> ALTERNATE_DIMENSION_MUSIC = REGISTRY.register("alternate_dimension_music",
			() -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("alternate_dimension", "alternate_dimension_music")));
}