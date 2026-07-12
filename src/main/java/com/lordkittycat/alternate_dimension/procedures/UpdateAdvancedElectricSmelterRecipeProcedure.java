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
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.Container;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class UpdateAdvancedElectricSmelterRecipeProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (world instanceof ServerLevel _level1 && _level1.recipeAccess().getRecipeFor(RecipeType.SMELTING, new SingleRecipeInput((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy())), _level1).isPresent()
				&& getEnergyStored(world, BlockPos.containing(x, y, z), null) >= 250
				&& ((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 1).copy()).getItem() == ItemStack.EMPTY.getItem()
						|| (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 1).copy()).getItem() == (getItemStackSmeltingResult(world, (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy()))).getItem()
								&& (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 1).copy()).getCount() <= (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 1).copy()).getMaxStackSize()
										- (getItemStackSmeltingResult(world, (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy()))).getCount())) {
			{
				BlockPos _pos = BlockPos.containing(x, y, z);
				BlockState _bs = world.getBlockState(_pos);
				if (_bs.getBlock().getStateDefinition().getProperty("running") instanceof BooleanProperty _booleanProp)
					world.setBlock(_pos, _bs.setValue(_booleanProp, true), 3);
			}
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putDouble("progress", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "progress") + 8));
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
			if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "progress") > 100 && getEnergyStored(world, BlockPos.containing(x, y, z), null) >= 250) {
				if (world instanceof ILevelExtension _ext) {
					if (_ext.getCapability(Capabilities.Energy.BLOCK, BlockPos.containing(x, y, z), null) instanceof EnergyHandler _energyHandler) {
						try (var _tx = Transaction.openRoot()) {
							_energyHandler.extract(250, _tx);
							_tx.commit();
						}
					}
				}
				{
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("running") instanceof BooleanProperty _booleanProp)
						world.setBlock(_pos, _bs.setValue(_booleanProp, false), 3);
				}
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
				if (world instanceof ServerLevel _serverLevel) {
					BlockEntity _be = _serverLevel.getBlockEntity(BlockPos.containing(x, y, z));
					if (_be instanceof Container _container) {
						ItemStack _setstack = (getItemStackSmeltingResult(world, (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy()))).copy();
						_setstack.setCount((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 1).copy()).getCount() + (getItemStackSmeltingResult(world, (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy()))).getCount());
						_container.setItem(1, _setstack);
					}
				}
				if (world instanceof ServerLevel _serverLevel) {
					BlockEntity _be = _serverLevel.getBlockEntity(BlockPos.containing(x, y, z));
					if (_be instanceof Container _container) {
						_container.getItem(0).shrink(1);
					}
				}
			}
		} else {
			{
				BlockPos _pos = BlockPos.containing(x, y, z);
				BlockState _bs = world.getBlockState(_pos);
				if (_bs.getBlock().getStateDefinition().getProperty("running") instanceof BooleanProperty _booleanProp)
					world.setBlock(_pos, _bs.setValue(_booleanProp, false), 3);
			}
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

	public static int getEnergyStored(LevelAccessor level, BlockPos pos, Direction direction) {
		if (level instanceof ILevelExtension levelExtension) {
			if (levelExtension.getCapability(Capabilities.Energy.BLOCK, pos, direction) instanceof EnergyHandler energyHandler)
				return energyHandler.getAmountAsInt();
		}
		return 0;
	}

	private static ItemStack getItemStackSmeltingResult(LevelAccessor level, ItemStack input) {
		SingleRecipeInput recipeInput = new SingleRecipeInput(input);
		if (level instanceof ServerLevel serverLevel) {
			return serverLevel.recipeAccess().getRecipeFor(RecipeType.SMELTING, recipeInput, serverLevel).map(recipe -> recipe.value().assemble(recipeInput).copy()).orElse(ItemStack.EMPTY);
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