package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;

import com.lordkittycat.alternate_dimension.init.AlternateDimensionModMenus;

public class LoadRefineryGUIProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof Player _player && _player.containerMenu instanceof AlternateDimensionModMenus.MenuAccessor _menu)
			_menu.sendMenuStateUpdate(_player, 1, "onoff_switch", (getBlockNBTLogic(world, BlockPos.containing(x, y, z), "on")), true);
		if (entity instanceof Player _player && _player.containerMenu instanceof AlternateDimensionModMenus.MenuAccessor _menu)
			_menu.sendMenuStateUpdate(_player, 2, "power", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "rpu")), true);
	}

	private static boolean getBlockNBTLogic(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getBooleanOr(tag, false);
		return false;
	}

	private static double getBlockNBTNumber(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getDoubleOr(tag, 0);
		return -1;
	}
}