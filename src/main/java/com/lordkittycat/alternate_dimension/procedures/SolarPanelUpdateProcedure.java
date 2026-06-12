package com.lordkittycat.alternate_dimension.procedures;

import net.neoforged.neoforge.energy.IEnergyStorage;
import net.neoforged.neoforge.common.extensions.ILevelExtension;
import net.neoforged.neoforge.capabilities.Capabilities;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.core.BlockPos;

public class SolarPanelUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double efficiency = 0;
		if (world.canSeeSkyFromBelowWater(BlockPos.containing(x, y, z)) && world instanceof Level _lvl1 && _lvl1.isDay()) {
			efficiency = 1;
			if (world.getLevelData().isThundering()) {
				efficiency = 0.25;
			} else if (world.getLevelData().isRaining()) {
				efficiency = 0.5;
			}
			if (world instanceof ILevelExtension _ext) {
				IEnergyStorage _entityStorage = _ext.getCapability(Capabilities.EnergyStorage.BLOCK, BlockPos.containing(x, y, z), null);
				if (_entityStorage != null)
					_entityStorage.receiveEnergy((int) (2 * efficiency), false);
			}
			PushEnergyProcedure.execute(world, x, y, z);
		}
	}
}