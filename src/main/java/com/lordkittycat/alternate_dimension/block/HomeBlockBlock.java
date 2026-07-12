package com.lordkittycat.alternate_dimension.block;

import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResult;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import com.lordkittycat.alternate_dimension.procedures.SetHomePointProcedure;

public class HomeBlockBlock extends Block {
	public HomeBlockBlock(BlockBehaviour.Properties properties) {
		super(properties.sound(SoundType.WOOD).strength(3f, 36000000f).lightLevel(blockstate -> 15).postProcess((bs, br, bp) -> bp).emissiveRendering((bs, br, bp) -> true));
	}

	@Override
	public InteractionResult useWithoutItem(BlockState blockstate, Level world, BlockPos pos, Player entity, BlockHitResult hit) {
		super.useWithoutItem(blockstate, world, pos, entity, hit);
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		double hitX = hit.getLocation().x;
		double hitY = hit.getLocation().y;
		double hitZ = hit.getLocation().z;
		Direction direction = hit.getDirection();
		SetHomePointProcedure.execute(world, x, y, z, entity);
		return InteractionResult.SUCCESS;
	}
}