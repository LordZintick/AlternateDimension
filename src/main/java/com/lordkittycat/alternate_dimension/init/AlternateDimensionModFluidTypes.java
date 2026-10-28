/*
 * MCreator note: This file will be REGENERATED on each build.
 */
package com.lordkittycat.alternate_dimension.init;

import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.fluids.FluidType;

import com.lordkittycat.alternate_dimension.fluid.types.LiquidPlasmaFluidType;
import com.lordkittycat.alternate_dimension.fluid.types.LiquidMyceliumFluidType;
import com.lordkittycat.alternate_dimension.AlternateDimensionMod;

public class AlternateDimensionModFluidTypes {
	public static final DeferredRegister<FluidType> REGISTRY = DeferredRegister.create(NeoForgeRegistries.FLUID_TYPES, AlternateDimensionMod.MODID);
	public static final DeferredHolder<FluidType, FluidType> LIQUID_MYCELIUM_TYPE = REGISTRY.register("liquid_mycelium", LiquidMyceliumFluidType::new);
	public static final DeferredHolder<FluidType, FluidType> LIQUID_PLASMA_TYPE = REGISTRY.register("liquid_plasma", LiquidPlasmaFluidType::new);
}