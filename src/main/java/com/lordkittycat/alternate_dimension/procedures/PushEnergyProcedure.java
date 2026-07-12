package com.lordkittycat.alternate_dimension.procedures;

import net.neoforged.neoforge.transfer.transaction.Transaction;
import net.neoforged.neoforge.transfer.energy.EnergyHandler;
import net.neoforged.neoforge.common.extensions.ILevelExtension;
import net.neoforged.neoforge.capabilities.Capabilities;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class PushEnergyProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double transferamnt = 0;
		for (Direction directioniterator : Direction.values()) {
			if (canReceiveEnergy(world, BlockPos.containing(x + directioniterator.getStepX(), y + directioniterator.getStepY(), z + directioniterator.getStepZ()), (directioniterator.getOpposite()))
					&& getEnergyStored(world, BlockPos.containing(x + directioniterator.getStepX(), y + directioniterator.getStepY(), z + directioniterator.getStepZ()), null) < getMaxEnergyStored(world,
							BlockPos.containing(x + directioniterator.getStepX(), y + directioniterator.getStepY(), z + directioniterator.getStepZ()), null)) {
				transferamnt = extractEnergySimulate(world, BlockPos.containing(x, y, z), 10000, directioniterator);
				if (world instanceof ILevelExtension _ext) {
					if (_ext.getCapability(Capabilities.Energy.BLOCK, BlockPos.containing(x, y, z), directioniterator) instanceof EnergyHandler _energyHandler) {
						try (var _tx = Transaction.openRoot()) {
							_energyHandler.extract((int) transferamnt, _tx);
							_tx.commit();
						}
					}
				}
				if (world instanceof ILevelExtension _ext) {
					if (_ext.getCapability(Capabilities.Energy.BLOCK, BlockPos.containing(x + directioniterator.getStepX(), y + directioniterator.getStepY(), z + directioniterator.getStepZ()),
							(directioniterator.getOpposite())) instanceof EnergyHandler _energyHandler) {
						try (var _tx = Transaction.openRoot()) {
							_energyHandler.insert((int) transferamnt, _tx);
							_tx.commit();
						}
					}
				}
			}
		}
	}

	private static boolean canReceiveEnergy(LevelAccessor level, BlockPos pos, Direction direction) {
		if (level instanceof ILevelExtension levelExtension) {
			if (levelExtension.getCapability(Capabilities.Energy.BLOCK, pos, direction) instanceof EnergyHandler energyHandler)
				return energyHandler.getCapacityAsLong() > 0;
		}
		return false;
	}

	public static int getEnergyStored(LevelAccessor level, BlockPos pos, Direction direction) {
		if (level instanceof ILevelExtension levelExtension) {
			if (levelExtension.getCapability(Capabilities.Energy.BLOCK, pos, direction) instanceof EnergyHandler energyHandler)
				return energyHandler.getAmountAsInt();
		}
		return 0;
	}

	public static int getMaxEnergyStored(LevelAccessor level, BlockPos pos, Direction direction) {
		if (level instanceof ILevelExtension levelExtension) {
			if (levelExtension.getCapability(Capabilities.Energy.BLOCK, pos, direction) instanceof EnergyHandler energyHandler)
				return energyHandler.getCapacityAsInt();
		}
		return 0;
	}

	private static int extractEnergySimulate(LevelAccessor level, BlockPos pos, int amount, Direction direction) {
		if (level instanceof ILevelExtension levelExtension) {
			if (levelExtension.getCapability(Capabilities.Energy.BLOCK, pos, direction) instanceof EnergyHandler energyHandler) {
				try (var tx = Transaction.openRoot()) {
					return energyHandler.extract(amount, tx);
				}
			}
		}
		return 0;
	}
}