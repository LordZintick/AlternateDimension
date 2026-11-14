package com.lordkittycat.alternate_dimension.fluid;

import org.apache.logging.log4j.core.util.Source;

import net.neoforged.neoforge.fluids.BaseFlowingFluid;

import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.ParticleOptions;

import com.lordkittycat.alternate_dimension.init.AlternateDimensionModItems;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModFluids;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModFluidTypes;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModBlocks;

public abstract class LiquidPlasmaFluid extends BaseFlowingFluid {
	public static final BaseFlowingFluid.Properties PROPERTIES = new BaseFlowingFluid.Properties(() -> AlternateDimensionModFluidTypes.LIQUID_PLASMA_TYPE.get(), () -> AlternateDimensionModFluids.LIQUID_PLASMA.get(),
			() -> AlternateDimensionModFluids.FLOWING_LIQUID_PLASMA.get()).explosionResistance(100f).tickRate(10).slopeFindDistance(1).bucket(() -> AlternateDimensionModItems.LIQUID_PLASMA_BUCKET.get())
			.block(() -> (LiquidBlock) AlternateDimensionModBlocks.LIQUID_PLASMA.get());

	private LiquidPlasmaFluid() {
		super(PROPERTIES);
	}

	@Override
	public ParticleOptions getDripParticle() {
		return ParticleTypes.FLAME;
	}

	public static class Source extends LiquidPlasmaFluid {
		public int getAmount(FluidState state) {
			return 8;
		}

		public boolean isSource(FluidState state) {
			return true;
		}
	}

	public static class Flowing extends LiquidPlasmaFluid {
		protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> builder) {
			super.createFluidStateDefinition(builder);
			builder.add(LEVEL);
		}

		public int getAmount(FluidState state) {
			return state.getValue(LEVEL);
		}

		public boolean isSource(FluidState state) {
			return false;
		}
	}
}