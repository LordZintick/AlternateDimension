package com.lordkittycat.alternate_dimension.procedures;

import net.neoforged.neoforge.transfer.transaction.Transaction;
import net.neoforged.neoforge.transfer.energy.EnergyHandler;
import net.neoforged.neoforge.common.extensions.ILevelExtension;
import net.neoforged.neoforge.capabilities.Capabilities;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class TransferManaFromTargetToSideProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		double amount = 0;
		if (world.isClientSide()) {
			return;
		}
		if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "targetX") == 0 || getBlockNBTNumber(world, BlockPos.containing(x, y, z), "targetY") == 0 || getBlockNBTNumber(world, BlockPos.containing(x, y, z), "targetZ") == 0
				|| getBlockNBTNumber(world, BlockPos.containing(x, y, z), "targetX") == -1 || getBlockNBTNumber(world, BlockPos.containing(x, y, z), "targetY") == -1 || getBlockNBTNumber(world, BlockPos.containing(x, y, z), "targetZ") == -1) {
			return;
		}
		if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "progress") >= 20) {
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putDouble("progress", 0);
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
			amount = Math.min(
					extractEnergySimulate(world,
							BlockPos.containing(getBlockNBTNumber(world, BlockPos.containing(x, y, z), "targetX"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "targetY"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "targetZ")),
							1, null),
					receiveEnergySimulate(world, BlockPos.containing(x + ((getDirectionFromBlockState(blockstate)).getOpposite()).getStepX(), y + ((getDirectionFromBlockState(blockstate)).getOpposite()).getStepY(),
							z + ((getDirectionFromBlockState(blockstate)).getOpposite()).getStepZ()), 1, null));
			if (amount > 0) {
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("block.enchantment_table.use")), SoundSource.BLOCKS, 1, (float) 1.1);
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("block.enchantment_table.use")), SoundSource.BLOCKS, 1, (float) 1.1, false);
					}
				}
				ParticleLineProcedure.execute(world, getBlockNBTNumber(world, BlockPos.containing(x, y, z), "targetX") + 0.5, getBlockNBTNumber(world, BlockPos.containing(x, y, z), "targetY") + 0.5,
						getBlockNBTNumber(world, BlockPos.containing(x, y, z), "targetZ") + 0.5, new Vec3((x + 0.5), (y + 0.5), (z + 0.5)));
				if (world instanceof ILevelExtension _ext) {
					if (_ext.getCapability(Capabilities.Energy.BLOCK,
							BlockPos.containing(getBlockNBTNumber(world, BlockPos.containing(x, y, z), "targetX"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "targetY"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "targetZ")),
							null) instanceof EnergyHandler _energyHandler) {
						try (var _tx = Transaction.openRoot()) {
							_energyHandler.extract((int) amount, _tx);
							_tx.commit();
						}
					}
				}
				if (world instanceof ILevelExtension _ext) {
					if (_ext.getCapability(Capabilities.Energy.BLOCK, BlockPos.containing(x + ((getDirectionFromBlockState(blockstate)).getOpposite()).getStepX(), y + ((getDirectionFromBlockState(blockstate)).getOpposite()).getStepY(),
							z + ((getDirectionFromBlockState(blockstate)).getOpposite()).getStepZ()), null) instanceof EnergyHandler _energyHandler) {
						try (var _tx = Transaction.openRoot()) {
							_energyHandler.insert((int) amount, _tx);
							_tx.commit();
						}
					}
				}
			}
		} else {
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putDouble("progress", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "progress") + 1));
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
		}
	}

	private static double getBlockNBTNumber(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getDoubleOr(tag, 0);
		return -1;
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

	private static Direction getDirectionFromBlockState(BlockState blockState) {
		if (getPropertyByName(blockState, "facing") instanceof EnumProperty ep && ep.getValueClass() == Direction.class)
			return (Direction) blockState.getValue(ep);
		if (getPropertyByName(blockState, "axis") instanceof EnumProperty ep && ep.getValueClass() == Direction.Axis.class)
			return Direction.fromAxisAndDirection((Direction.Axis) blockState.getValue(ep), Direction.AxisDirection.POSITIVE);
		return Direction.NORTH;
	}

	private static int receiveEnergySimulate(LevelAccessor level, BlockPos pos, int amount, Direction direction) {
		if (level instanceof ILevelExtension levelExtension) {
			if (levelExtension.getCapability(Capabilities.Energy.BLOCK, pos, direction) instanceof EnergyHandler energyHandler) {
				try (var tx = Transaction.openRoot()) {
					return energyHandler.insert(amount, tx);
				}
			}
		}
		return 0;
	}

	private static Property<?> getPropertyByName(BlockState state, String name) {
		for (Property<?> property : state.getProperties()) {
			if (property.getName().equals(name)) {
				return property;
			}
		}
		return null;
	}
}