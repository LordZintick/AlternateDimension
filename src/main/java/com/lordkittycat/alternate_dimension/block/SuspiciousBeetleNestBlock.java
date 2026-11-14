package com.lordkittycat.alternate_dimension.block;

import net.neoforged.neoforge.common.util.DeferredSoundType;

import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

import com.lordkittycat.alternate_dimension.procedures.SummonBeetleQueenProcedure;

public class SuspiciousBeetleNestBlock extends Block {
	public SuspiciousBeetleNestBlock(BlockBehaviour.Properties properties) {
		super(properties.sound(new DeferredSoundType(1.0f, 1.0f, () -> BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("entity.generic.explode")), () -> BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("block.rooted_dirt.step")),
				() -> BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("block.rooted_dirt.place")), () -> BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("block.rooted_dirt.hit")),
				() -> BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("block.rooted_dirt.fall")))).strength(4f, 36000000f));
	}

	@Override
	public boolean onDestroyedByPlayer(BlockState blockstate, Level world, BlockPos pos, Player entity, ItemStack toolStack, boolean willHarvest, FluidState fluid) {
		boolean retval = super.onDestroyedByPlayer(blockstate, world, pos, entity, toolStack, willHarvest, fluid);
		SummonBeetleQueenProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ(), entity);
		return retval;
	}
}