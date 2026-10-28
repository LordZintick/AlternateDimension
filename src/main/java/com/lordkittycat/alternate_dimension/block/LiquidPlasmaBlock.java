package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;

import com.lordkittycat.alternate_dimension.procedures.BurnEntityProcedure;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModFluids;

public class LiquidPlasmaBlock extends LiquidBlock {
	public LiquidPlasmaBlock(BlockBehaviour.Properties properties) {
		super(AlternateDimensionModFluids.LIQUID_PLASMA.get(), properties.mapColor(MapColor.CRIMSON_STEM).strength(100f).postProcess((bs, br, bp) -> bp).emissiveRendering((bs, br, bp) -> true).lightLevel(state -> 15).noCollision().noLootTable()
				.liquid().pushReaction(PushReaction.DESTROY).sound(SoundType.EMPTY).replaceable());
	}

	@Override
	public void entityInside(BlockState blockstate, Level world, BlockPos pos, Entity entity, InsideBlockEffectApplier insideBlockEffectApplier, boolean isPrecise) {
		super.entityInside(blockstate, world, pos, entity, insideBlockEffectApplier, isPrecise);
		BurnEntityProcedure.execute(entity);
	}
}