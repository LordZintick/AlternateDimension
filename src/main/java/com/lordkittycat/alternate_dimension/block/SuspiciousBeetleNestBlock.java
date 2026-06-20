package com.lordkittycat.alternate_dimension.block;

import net.neoforged.neoforge.common.util.DeferredSoundType;

import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

import com.lordkittycat.alternate_dimension.procedures.SummonBeetleQueenProcedure;

public class SuspiciousBeetleNestBlock extends Block {
	public SuspiciousBeetleNestBlock() {
		super(BlockBehaviour.Properties.of()
				.sound(new DeferredSoundType(1.0f, 1.0f, () -> BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.generic.explode")), () -> BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.rooted_dirt.step")),
						() -> BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.rooted_dirt.place")), () -> BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.rooted_dirt.hit")),
						() -> BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.rooted_dirt.fall"))))
				.strength(10f, 36000000f));
	}

	@Override
	public boolean onDestroyedByPlayer(BlockState blockstate, Level world, BlockPos pos, Player entity, boolean willHarvest, FluidState fluid) {
		boolean retval = super.onDestroyedByPlayer(blockstate, world, pos, entity, willHarvest, fluid);
		SummonBeetleQueenProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ(), entity);
		return retval;
	}
}