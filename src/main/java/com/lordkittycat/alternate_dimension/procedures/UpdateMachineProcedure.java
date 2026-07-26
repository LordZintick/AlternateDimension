package com.lordkittycat.alternate_dimension.procedures;

import net.neoforged.neoforge.transfer.transaction.Transaction;
import net.neoforged.neoforge.transfer.item.ItemUtil;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.energy.EnergyHandler;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.common.extensions.ILevelExtension;
import net.neoforged.neoforge.capabilities.Capabilities;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.Container;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import java.util.ArrayList;

public class UpdateMachineProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, ArrayList inputCounts, ArrayList inputs, Direction outputSide, ItemStack recipeResult, double operationMana, double speed) {
		double i;
		boolean countCheck;
		if (world.isClientSide()) {
			return;
		}
		if (!getBlockNBTLogic(world, BlockPos.containing(x, y, z), "importDisabled")) {
			i = 0;
			for (Object arraylistiterator : inputs) {
				ImportItemsFromConveyorProcedure.execute(world, x, y, z, arraylistiterator instanceof Direction _direction3 ? _direction3 : Direction.UP, i);
				i = i + 1;
			}
		}
		countCheck = true;
		if (inputCounts != null && !inputCounts.isEmpty()) {
			for (int slot = 0; slot < inputs.size(); slot++) {
				int itemCount = itemFromBlockInventory(world, BlockPos.containing(x, y, z), slot).getCount();
				int requiredCount = ((Double) inputCounts.get(slot)).intValue();
				if (itemCount < requiredCount) {
					countCheck = false;
					break;
				}
			}
		}
		if (recipeResult.getItem() == ItemStack.EMPTY.getItem() || !countCheck || getEnergyStored(world, BlockPos.containing(x, y, z), null) < operationMana) {
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
			{
				BlockPos _pos = BlockPos.containing(x, y, z);
				BlockState _bs = world.getBlockState(_pos);
				if (_bs.getBlock().getStateDefinition().getProperty("running") instanceof BooleanProperty _booleanProp)
					world.setBlock(_pos, _bs.setValue(_booleanProp, false), 3);
			}
		} else if (canInsertInBlockInventory(world, BlockPos.containing(x, y, z), inputs.size(), recipeResult.getCount(), recipeResult)) {
			if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "progress") >= 100) {
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
				if (world instanceof ILevelExtension _ext) {
					if (_ext.getCapability(Capabilities.Energy.BLOCK, BlockPos.containing(x, y, z), null) instanceof EnergyHandler _energyHandler) {
						try (var _tx = Transaction.openRoot()) {
							_energyHandler.extract((int) operationMana, _tx);
							_tx.commit();
						}
					}
				}
				i = 0;
				if (inputCounts != null) {
					for (Object arraylistiterator : inputCounts) {
						if (world instanceof ServerLevel _serverLevel) {
							BlockEntity _be = _serverLevel.getBlockEntity(BlockPos.containing(x, y, z));
							if (_be instanceof Container _container) {
								_container.getItem((int) i).shrink((int) (arraylistiterator instanceof Double _doub21 ? _doub21 : 0.0D));
							}
						}
						i = i + 1;
					}
				} else {
					for (Object arraylistiterator : inputs) {
						if (world instanceof ServerLevel _serverLevel) {
							BlockEntity _be = _serverLevel.getBlockEntity(BlockPos.containing(x, y, z));
							if (_be instanceof Container _container) {
								_container.getItem((int) i).shrink(1);
							}
						}
						i = i + 1;
					}
				}
				insertInBlockInventory(world, BlockPos.containing(x, y, z), inputs.size(), recipeResult.getCount(), recipeResult, false);
			} else {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putDouble("progress", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "progress") + speed));
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			}
			{
				BlockPos _pos = BlockPos.containing(x, y, z);
				BlockState _bs = world.getBlockState(_pos);
				if (_bs.getBlock().getStateDefinition().getProperty("running") instanceof BooleanProperty _booleanProp)
					world.setBlock(_pos, _bs.setValue(_booleanProp, true), 3);
			}
		}
		if (!getBlockNBTLogic(world, BlockPos.containing(x, y, z), "exportDisabled")) {
			ExportItemsToConveyorProcedure.execute(world, x, y, z, outputSide, inputs.size());
		}
	}

	private static boolean getBlockNBTLogic(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getBooleanOr(tag, false);
		return false;
	}

	private static ItemStack itemFromBlockInventory(LevelAccessor world, BlockPos pos, int slot) {
		if (world instanceof ILevelExtension ext) {
			ResourceHandler<ItemResource> itemHandler = ext.getCapability(Capabilities.Item.BLOCK, pos, null);
			if (itemHandler != null)
				return ItemUtil.getStack(itemHandler, slot);
		}
		return ItemStack.EMPTY;
	}

	public static int getEnergyStored(LevelAccessor level, BlockPos pos, Direction direction) {
		if (level instanceof ILevelExtension levelExtension) {
			if (levelExtension.getCapability(Capabilities.Energy.BLOCK, pos, direction) instanceof EnergyHandler energyHandler)
				return energyHandler.getAmountAsInt();
		}
		return 0;
	}

	public static boolean canInsertInBlockInventory(LevelAccessor world, BlockPos pos, int slotId, int amount, ItemStack itemstack) {
		if (world instanceof ILevelExtension ext) {
			ResourceHandler<ItemResource> itemHandler = ext.getCapability(Capabilities.Item.BLOCK, pos, null);
			if (itemHandler != null && slotId >= 0 && slotId < itemHandler.size()) {
				ItemStack inserted = itemstack.copy();
				inserted.setCount(amount);
				return ItemUtil.insertItemReturnRemaining(itemHandler, slotId, inserted, true, null).getCount() == 0;
			}
		}
		return false;
	}

	private static double getBlockNBTNumber(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getDoubleOr(tag, 0);
		return -1;
	}

	private static int insertInBlockInventory(LevelAccessor world, BlockPos pos, int slotId, int amount, ItemStack itemstack, boolean simulate) {
		if (world instanceof ILevelExtension ext) {
			ResourceHandler<ItemResource> itemHandler = ext.getCapability(Capabilities.Item.BLOCK, pos, null);
			if (itemHandler != null && slotId >= 0 && slotId < itemHandler.size()) {
				ItemStack inserted = itemstack.copy();
				inserted.setCount(amount);
				return ItemUtil.insertItemReturnRemaining(itemHandler, slotId, inserted, simulate, null).getCount();
			}
		}
		return itemstack.getCount();
	}
}