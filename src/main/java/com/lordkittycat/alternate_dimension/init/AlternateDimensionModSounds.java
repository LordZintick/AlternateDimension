/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com.lordkittycat.alternate_dimension.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;

import com.lordkittycat.alternate_dimension.AlternateDimensionMod;

public class AlternateDimensionModSounds {
	public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(Registries.SOUND_EVENT, AlternateDimensionMod.MODID);
	public static final DeferredHolder<SoundEvent, SoundEvent> DRILL_USE = REGISTRY.register("drill_use", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("alternate_dimension", "drill_use")));
	public static final DeferredHolder<SoundEvent, SoundEvent> MAGNETIC_LORD_INTRO = REGISTRY.register("magnetic_lord_intro",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("alternate_dimension", "magnetic_lord_intro")));
	public static final DeferredHolder<SoundEvent, SoundEvent> MAGNETIC_LORD_MUSIC = REGISTRY.register("magnetic_lord_music",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("alternate_dimension", "magnetic_lord_music")));
	public static final DeferredHolder<SoundEvent, SoundEvent> NEW_WASTELAND_MUSIC = REGISTRY.register("new_wasteland_music",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("alternate_dimension", "new_wasteland_music")));
	public static final DeferredHolder<SoundEvent, SoundEvent> NEW_FOREST_MUSIC = REGISTRY.register("new_forest_music", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("alternate_dimension", "new_forest_music")));
	public static final DeferredHolder<SoundEvent, SoundEvent> NEW_CANDY_MUSIC = REGISTRY.register("new_candy_music", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("alternate_dimension", "new_candy_music")));
	public static final DeferredHolder<SoundEvent, SoundEvent> NEW_HIGHLANDS_MUSIC = REGISTRY.register("new_highlands_music",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("alternate_dimension", "new_highlands_music")));
	public static final DeferredHolder<SoundEvent, SoundEvent> BOSS_BATTLE = REGISTRY.register("boss_battle", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("alternate_dimension", "boss_battle")));
	public static final DeferredHolder<SoundEvent, SoundEvent> CANDY_MUSIC = REGISTRY.register("candy_music", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("alternate_dimension", "candy_music")));
	public static final DeferredHolder<SoundEvent, SoundEvent> CREDITS = REGISTRY.register("credits", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("alternate_dimension", "credits")));
	public static final DeferredHolder<SoundEvent, SoundEvent> DARK_REALM = REGISTRY.register("dark_realm", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("alternate_dimension", "dark_realm")));
	public static final DeferredHolder<SoundEvent, SoundEvent> FRUITY_GARDENS = REGISTRY.register("fruity_gardens", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("alternate_dimension", "fruity_gardens")));
	public static final DeferredHolder<SoundEvent, SoundEvent> GELATIN_SWAMP = REGISTRY.register("gelatin_swamp", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("alternate_dimension", "gelatin_swamp")));
	public static final DeferredHolder<SoundEvent, SoundEvent> ICE_CREAM_HILLS = REGISTRY.register("ice_cream_hills", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("alternate_dimension", "ice_cream_hills")));
	public static final DeferredHolder<SoundEvent, SoundEvent> REDWOOD_FOREST = REGISTRY.register("redwood_forest", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("alternate_dimension", "redwood_forest")));
	public static final DeferredHolder<SoundEvent, SoundEvent> WASTELAND_MUSIC = REGISTRY.register("wasteland_music", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("alternate_dimension", "wasteland_music")));
	public static final DeferredHolder<SoundEvent, SoundEvent> MIDLANDS = REGISTRY.register("midlands", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("alternate_dimension", "midlands")));
	public static final DeferredHolder<SoundEvent, SoundEvent> REDWOOD_FOREST_QUIET = REGISTRY.register("redwood_forest_quiet",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("alternate_dimension", "redwood_forest_quiet")));
	public static final DeferredHolder<SoundEvent, SoundEvent> CALM_OCEAN = REGISTRY.register("calm_ocean", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("alternate_dimension", "calm_ocean")));
	public static final DeferredHolder<SoundEvent, SoundEvent> ENCHANTED_FOREST = REGISTRY.register("enchanted_forest", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("alternate_dimension", "enchanted_forest")));
	public static final DeferredHolder<SoundEvent, SoundEvent> MUSHROOM_CAVE = REGISTRY.register("mushroom_cave", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("alternate_dimension", "mushroom_cave")));
	public static final DeferredHolder<SoundEvent, SoundEvent> FROSTBITTEN_PLAINS = REGISTRY.register("frostbitten_plains",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("alternate_dimension", "frostbitten_plains")));
	public static final DeferredHolder<SoundEvent, SoundEvent> MUSHROOM_INFECTION = REGISTRY.register("mushroom_infection",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("alternate_dimension", "mushroom_infection")));
}