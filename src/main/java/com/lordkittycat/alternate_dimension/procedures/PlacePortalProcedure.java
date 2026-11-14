package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;

import com.lordkittycat.alternate_dimension.init.AlternateDimensionModBlocks;

public class PlacePortalProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, boolean oneUse, double targetX, double targetY, double targetZ) {
		world.setBlock(BlockPos.containing(x, y, z), AlternateDimensionModBlocks.PORTAL_BLOCK.get().defaultBlockState(), 3);
		if (!world.isClientSide()) {
			BlockPos _bp = BlockPos.containing(x, y, z);
			BlockEntity _blockEntity = world.getBlockEntity(_bp);
			BlockState _bs = world.getBlockState(_bp);
			if (_blockEntity != null) {
				_blockEntity.getPersistentData().putString("owner", (entity.getStringUUID()));
				_blockEntity.getPersistentData().putDouble("targetX", targetX);
				_blockEntity.getPersistentData().putDouble("targetY", targetY);
				_blockEntity.getPersistentData().putDouble("targetZ", targetZ);
				_blockEntity.getPersistentData().putBoolean("oneUse", oneUse);
			}
			if (world instanceof Level _level)
				_level.sendBlockUpdated(_bp, _bs, _bs, 3);
		}
	}
}