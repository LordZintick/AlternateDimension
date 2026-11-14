package com.lordkittycat.alternate_dimension.fluid;

import org.apache.logging.log4j.core.util.Source;

import net.neoforged.neoforge.fluids.BaseFlowingFluid;

import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.LiquidBlock;

import com.lordkittycat.alternate_dimension.init.AlternateDimensionModItems;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModFluids;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModFluidTypes;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModBlocks;

public abstract class LiquidMyceliumFluid extends BaseFlowingFluid {
	public static final BaseFlowingFluid.Properties PROPERTIES = new BaseFlowingFluid.Properties(() -> AlternateDimensionModFluidTypes.LIQUID_MYCELIUM_TYPE.get(), () -> AlternateDimensionModFluids.LIQUID_MYCELIUM.get(),
			() -> AlternateDimensionModFluids.FLOWING_LIQUID_MYCELIUM.get()).explosionResistance(100f).tickRate(10).bucket(() -> AlternateDimensionModItems.LIQUID_MYCELIUM_BUCKET.get())
			.block(() -> (LiquidBlock) AlternateDimensionModBlocks.LIQUID_MYCELIUM.get());

	private LiquidMyceliumFluid() {
		super(PROPERTIES);
	}

	public static class Source extends LiquidMyceliumFluid {
		public int getAmount(FluidState state) {
			return 8;
		}

		public boolean isSource(FluidState state) {
			return true;
		}
	}

	public static class Flowing extends LiquidMyceliumFluid {
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