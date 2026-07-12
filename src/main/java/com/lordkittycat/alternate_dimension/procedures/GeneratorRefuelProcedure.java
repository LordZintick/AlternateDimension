package com.lordkittycat.alternate_dimension.procedures;

import net.neoforged.neoforge.transfer.item.ItemUtil;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.common.extensions.ILevelExtension;
import net.neoforged.neoforge.capabilities.Capabilities;

import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.Container;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;

public class GeneratorRefuelProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double previousRecipe = 0;
		if ((world instanceof Level _levelFV1 ? (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy()).getBurnTime(null, _levelFV1.fuelValues()) : 0) > 0 && getBlockNBTNumber(world, BlockPos.containing(x, y, z), "burnTime") <= 0) {
			{
				int _value = 1;
				BlockPos _pos = BlockPos.containing(x, y, z);
				BlockState _bs = world.getBlockState(_pos);
				if (_bs.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
					world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
			}
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putDouble("burnTime", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "burnTime")
							+ (world instanceof Level _levelFV6 ? (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy()).getBurnTime(null, _levelFV6.fuelValues()) : 0) / 4));
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
			if (world instanceof ServerLevel _serverLevel) {
				BlockEntity _be = _serverLevel.getBlockEntity(BlockPos.containing(x, y, z));
				if (_be instanceof Container _container) {
					_container.getItem(0).shrink(1);
				}
			}
		}
	}

	private static ItemStack itemFromBlockInventory(LevelAccessor world, BlockPos pos, int slot) {
		if (world instanceof ILevelExtension ext) {
			ResourceHandler<ItemResource> itemHandler = ext.getCapability(Capabilities.Item.BLOCK, pos, null);
			if (itemHandler != null)
				return ItemUtil.getStack(itemHandler, slot);
		}
		return ItemStack.EMPTY;
	}

	private static double getBlockNBTNumber(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getDoubleOr(tag, 0);
		return -1;
	}
}