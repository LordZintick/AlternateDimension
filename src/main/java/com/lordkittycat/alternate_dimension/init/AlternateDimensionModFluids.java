/*
 * MCreator note: This file will be REGENERATED on each build.
 */
package com.lordkittycat.alternate_dimension.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.core.registries.BuiltInRegistries;

import com.lordkittycat.alternate_dimension.fluid.LiquidPlasmaFluid;
import com.lordkittycat.alternate_dimension.fluid.LiquidMyceliumFluid;
import com.lordkittycat.alternate_dimension.AlternateDimensionMod;

public class AlternateDimensionModFluids {
	public static final DeferredRegister<Fluid> REGISTRY = DeferredRegister.create(BuiltInRegistries.FLUID, AlternateDimensionMod.MODID);
	public static final DeferredHolder<Fluid, FlowingFluid> LIQUID_MYCELIUM = REGISTRY.register("liquid_mycelium", LiquidMyceliumFluid.Source::new);
	public static final DeferredHolder<Fluid, FlowingFluid> FLOWING_LIQUID_MYCELIUM = REGISTRY.register("flowing_liquid_mycelium", LiquidMyceliumFluid.Flowing::new);
	public static final DeferredHolder<Fluid, FlowingFluid> LIQUID_PLASMA = REGISTRY.register("liquid_plasma", LiquidPlasmaFluid.Source::new);
	public static final DeferredHolder<Fluid, FlowingFluid> FLOWING_LIQUID_PLASMA = REGISTRY.register("flowing_liquid_plasma", LiquidPlasmaFluid.Flowing::new);
}