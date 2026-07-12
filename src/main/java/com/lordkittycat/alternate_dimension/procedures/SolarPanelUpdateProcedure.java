package com.lordkittycat.alternate_dimension.procedures;

import net.neoforged.neoforge.transfer.transaction.Transaction;
import net.neoforged.neoforge.transfer.energy.EnergyHandler;
import net.neoforged.neoforge.common.extensions.ILevelExtension;
import net.neoforged.neoforge.capabilities.Capabilities;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.core.BlockPos;

public class SolarPanelUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double efficiency = 0;
		if (world.canSeeSkyFromBelowWater(BlockPos.containing(x, y, z)) && world instanceof Level _lvl1 && _lvl1.isBrightOutside()) {
			efficiency = 1;
			if (world instanceof Level _lvl2 && _lvl2.isThundering()) {
				efficiency = 0.25;
			} else if (world instanceof Level _lvl3 && _lvl3.isRaining()) {
				efficiency = 0.5;
			}
			if (world instanceof ILevelExtension _ext) {
				if (_ext.getCapability(Capabilities.Energy.BLOCK, BlockPos.containing(x, y, z), null) instanceof EnergyHandler _energyHandler) {
					try (var _tx = Transaction.openRoot()) {
						_energyHandler.insert((int) (2 * efficiency), _tx);
						_tx.commit();
					}
				}
			}
			PushEnergyProcedure.execute(world, x, y, z);
		}
	}
}