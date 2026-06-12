package com.lordkittycat.alternate_dimension.procedures;

import net.neoforged.neoforge.items.IItemHandlerModifiable;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.energy.IEnergyStorage;
import net.neoforged.neoforge.common.extensions.ILevelExtension;
import net.neoforged.neoforge.capabilities.Capabilities;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import java.util.stream.Collectors;
import java.util.List;

import com.lordkittycat.alternate_dimension.jei_recipes.AlloySmelterRecipeTypeRecipe;

public class UpdateAlloySmelterRecipeProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		ItemStack result = ItemStack.EMPTY;
		result = (new Object() {
			public ItemStack getResult() {
				if (world instanceof Level _lvl) {
					net.minecraft.world.item.crafting.RecipeManager rm = _lvl.getRecipeManager();
					List<AlloySmelterRecipeTypeRecipe> recipes = rm.getAllRecipesFor(AlloySmelterRecipeTypeRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
					for (AlloySmelterRecipeTypeRecipe recipe : recipes) {
						NonNullList<Ingredient> ingredients = recipe.getIngredients();
						if (!ingredients.get(0).test((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy())))
							continue;
						if (!ingredients.get(1).test((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 2).copy())))
							continue;
						if (!ingredients.get(2).test((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 3).copy())))
							continue;
						return recipe.getResultItems().get(0).copy();
					}
				}
				return ItemStack.EMPTY;
			}
		}.getResult()).copy();
		if (result.getItem() == ItemStack.EMPTY.getItem()) {
			result = (new Object() {
				public ItemStack getResult() {
					if (world instanceof Level _lvl) {
						net.minecraft.world.item.crafting.RecipeManager rm = _lvl.getRecipeManager();
						List<AlloySmelterRecipeTypeRecipe> recipes = rm.getAllRecipesFor(AlloySmelterRecipeTypeRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
						for (AlloySmelterRecipeTypeRecipe recipe : recipes) {
							NonNullList<Ingredient> ingredients = recipe.getIngredients();
							if (!ingredients.get(0).test((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy())))
								continue;
							if (!ingredients.get(1).test((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 3).copy())))
								continue;
							if (!ingredients.get(2).test((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 2).copy())))
								continue;
							return recipe.getResultItems().get(0).copy();
						}
					}
					return ItemStack.EMPTY;
				}
			}.getResult()).copy();
		}
		if (result.getItem() == ItemStack.EMPTY.getItem()) {
			result = (new Object() {
				public ItemStack getResult() {
					if (world instanceof Level _lvl) {
						net.minecraft.world.item.crafting.RecipeManager rm = _lvl.getRecipeManager();
						List<AlloySmelterRecipeTypeRecipe> recipes = rm.getAllRecipesFor(AlloySmelterRecipeTypeRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
						for (AlloySmelterRecipeTypeRecipe recipe : recipes) {
							NonNullList<Ingredient> ingredients = recipe.getIngredients();
							if (!ingredients.get(0).test((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 2).copy())))
								continue;
							if (!ingredients.get(1).test((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy())))
								continue;
							if (!ingredients.get(2).test((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 3).copy())))
								continue;
							return recipe.getResultItems().get(0).copy();
						}
					}
					return ItemStack.EMPTY;
				}
			}.getResult()).copy();
		}
		if (result.getItem() == ItemStack.EMPTY.getItem()) {
			result = (new Object() {
				public ItemStack getResult() {
					if (world instanceof Level _lvl) {
						net.minecraft.world.item.crafting.RecipeManager rm = _lvl.getRecipeManager();
						List<AlloySmelterRecipeTypeRecipe> recipes = rm.getAllRecipesFor(AlloySmelterRecipeTypeRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
						for (AlloySmelterRecipeTypeRecipe recipe : recipes) {
							NonNullList<Ingredient> ingredients = recipe.getIngredients();
							if (!ingredients.get(0).test((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 2).copy())))
								continue;
							if (!ingredients.get(1).test((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 3).copy())))
								continue;
							if (!ingredients.get(2).test((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy())))
								continue;
							return recipe.getResultItems().get(0).copy();
						}
					}
					return ItemStack.EMPTY;
				}
			}.getResult()).copy();
		}
		if (result.getItem() == ItemStack.EMPTY.getItem()) {
			result = (new Object() {
				public ItemStack getResult() {
					if (world instanceof Level _lvl) {
						net.minecraft.world.item.crafting.RecipeManager rm = _lvl.getRecipeManager();
						List<AlloySmelterRecipeTypeRecipe> recipes = rm.getAllRecipesFor(AlloySmelterRecipeTypeRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
						for (AlloySmelterRecipeTypeRecipe recipe : recipes) {
							NonNullList<Ingredient> ingredients = recipe.getIngredients();
							if (!ingredients.get(0).test((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 3).copy())))
								continue;
							if (!ingredients.get(1).test((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy())))
								continue;
							if (!ingredients.get(2).test((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 2).copy())))
								continue;
							return recipe.getResultItems().get(0).copy();
						}
					}
					return ItemStack.EMPTY;
				}
			}.getResult()).copy();
		}
		if (result.getItem() == ItemStack.EMPTY.getItem()) {
			result = (new Object() {
				public ItemStack getResult() {
					if (world instanceof Level _lvl) {
						net.minecraft.world.item.crafting.RecipeManager rm = _lvl.getRecipeManager();
						List<AlloySmelterRecipeTypeRecipe> recipes = rm.getAllRecipesFor(AlloySmelterRecipeTypeRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
						for (AlloySmelterRecipeTypeRecipe recipe : recipes) {
							NonNullList<Ingredient> ingredients = recipe.getIngredients();
							if (!ingredients.get(0).test((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 3).copy())))
								continue;
							if (!ingredients.get(1).test((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 2).copy())))
								continue;
							if (!ingredients.get(2).test((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy())))
								continue;
							return recipe.getResultItems().get(0).copy();
						}
					}
					return ItemStack.EMPTY;
				}
			}.getResult()).copy();
		}
		if (!(result.getItem() == ItemStack.EMPTY.getItem()) && getEnergyStored(world, BlockPos.containing(x, y, z), null) >= 500) {
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
					_blockEntity.getPersistentData().putDouble("progress", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "progress") + 1));
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
			if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "progress") > 100 && getEnergyStored(world, BlockPos.containing(x, y, z), null) >= 500) {
				if (world instanceof ILevelExtension _ext) {
					IEnergyStorage _entityStorage = _ext.getCapability(Capabilities.EnergyStorage.BLOCK, BlockPos.containing(x, y, z), null);
					if (_entityStorage != null)
						_entityStorage.extractEnergy(500, false);
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
				if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
					ItemStack _setstack = result.copy();
					_setstack.setCount((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 1).copy()).getCount() + result.getCount());
					_itemHandlerModifiable.setStackInSlot(1, _setstack);
				}
				if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
					int _slotid = 0;
					ItemStack _stk = _itemHandlerModifiable.getStackInSlot(_slotid).copy();
					_stk.shrink(1);
					_itemHandlerModifiable.setStackInSlot(_slotid, _stk);
				}
				if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
					int _slotid = 2;
					ItemStack _stk = _itemHandlerModifiable.getStackInSlot(_slotid).copy();
					_stk.shrink(1);
					_itemHandlerModifiable.setStackInSlot(_slotid, _stk);
				}
				if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
					int _slotid = 3;
					ItemStack _stk = _itemHandlerModifiable.getStackInSlot(_slotid).copy();
					_stk.shrink(1);
					_itemHandlerModifiable.setStackInSlot(_slotid, _stk);
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
			IItemHandler itemHandler = ext.getCapability(Capabilities.ItemHandler.BLOCK, pos, null);
			if (itemHandler != null)
				return itemHandler.getStackInSlot(slot);
		}
		return ItemStack.EMPTY;
	}

	public static int getEnergyStored(LevelAccessor level, BlockPos pos, Direction direction) {
		if (level instanceof ILevelExtension levelExtension) {
			IEnergyStorage energyStorage = levelExtension.getCapability(Capabilities.EnergyStorage.BLOCK, pos, direction);
			if (energyStorage != null)
				return energyStorage.getEnergyStored();
		}
		return 0;
	}

	private static double getBlockNBTNumber(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getDouble(tag);
		return -1;
	}
}