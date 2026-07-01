package com.lordkittycat.alternate_dimension.procedures;

import net.neoforged.neoforge.items.IItemHandlerModifiable;
import net.neoforged.neoforge.items.IItemHandler;
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
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.NonNullList;
import net.minecraft.core.BlockPos;

import java.util.stream.Collectors;
import java.util.List;

import com.lordkittycat.alternate_dimension.jei_recipes.ElementalInfuserRecipeTypeRecipe;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModItems;

public class UpdateElementalInscriberRecipeProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		ItemStack result = ItemStack.EMPTY;
		double i = 0;
		boolean countCheck = false;
		if ((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 6).copy()).getItem() == AlternateDimensionModItems.ELEMENTIUM_INGOT.get() && getBlockNBTNumber(world, BlockPos.containing(x, y, z), "burnTime") < 10) {
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putDouble("burnTime", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "burnTime") + 1));
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
			if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
				int _slotid = 6;
				ItemStack _stk = _itemHandlerModifiable.getStackInSlot(_slotid).copy();
				_stk.shrink(1);
				_itemHandlerModifiable.setStackInSlot(_slotid, _stk);
			}
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.amethyst_block.resonate")), SoundSource.BLOCKS, 1, 1);
				} else {
					_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.amethyst_block.resonate")), SoundSource.BLOCKS, 1, 1, false);
				}
			}
		}
		result = (new Object() {
			public ItemStack getResult() {
				if (world instanceof Level _lvl) {
					net.minecraft.world.item.crafting.RecipeManager rm = _lvl.getRecipeManager();
					List<ElementalInfuserRecipeTypeRecipe> recipes = rm.getAllRecipesFor(ElementalInfuserRecipeTypeRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
					for (ElementalInfuserRecipeTypeRecipe recipe : recipes) {
						NonNullList<Ingredient> ingredients = recipe.getIngredients();
						if (!ingredients.get(0).test((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy())))
							continue;
						if (!ingredients.get(1).test((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 1).copy())))
							continue;
						if (!ingredients.get(2).test((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 2).copy())))
							continue;
						if (!ingredients.get(3).test((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 3).copy())))
							continue;
						if (!ingredients.get(4).test((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 4).copy())))
							continue;
						return recipe.getResultItems().get(0).copy();
					}
				}
				return ItemStack.EMPTY;
			}
		}.getResult()).copy();
		countCheck = true;
		{
			if (world instanceof Level _lvl) {
				net.minecraft.world.item.crafting.RecipeManager rm = _lvl.getRecipeManager();
				List<ElementalInfuserRecipeTypeRecipe> recipes = rm.getAllRecipesFor(ElementalInfuserRecipeTypeRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
				for (ElementalInfuserRecipeTypeRecipe recipe : recipes) {
					List<Ingredient> ingredients = recipe.getIngredients();
					if (!ingredients.get(0).test((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy())))
						continue;
					if (!ingredients.get(1).test((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 1).copy())))
						continue;
					if (!ingredients.get(2).test((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 2).copy())))
						continue;
					if (!ingredients.get(3).test((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 3).copy())))
						continue;
					if (!ingredients.get(4).test((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 4).copy())))
						continue;
					List<ItemStack> reciperesult = recipe.getResultItems();
					i = 0;
					for (int num : recipe.integers()) {
						double numberiterator = (double) num;
						if (itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) i).getCount() < numberiterator) {
							countCheck = false;
							break;
						}
						i = i + 1;
					}
					break;
				}
			}
		}
		if (!(result.getItem() == ItemStack.EMPTY.getItem()) && countCheck && getBlockNBTNumber(world, BlockPos.containing(x, y, z), "burnTime") > 0
				&& ((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 5).copy()).getItem() == ItemStack.EMPTY.getItem() || (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 5).copy()).getItem() == result.getItem()
						&& (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 5).copy()).getCount() <= (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 5).copy()).getMaxStackSize() - result.getCount())) {
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
					_blockEntity.getPersistentData().putDouble("progress", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "progress") + 0.05));
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
			if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "progress") > 100) {
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
					_setstack.setCount((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 5).copy()).getCount() + result.getCount());
					_itemHandlerModifiable.setStackInSlot(5, _setstack);
				}
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.enchantment_table.use")), SoundSource.BLOCKS, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.enchantment_table.use")), SoundSource.BLOCKS, 1, 1, false);
					}
				}
				{
					if (world instanceof Level _lvl) {
						net.minecraft.world.item.crafting.RecipeManager rm = _lvl.getRecipeManager();
						List<ElementalInfuserRecipeTypeRecipe> recipes = rm.getAllRecipesFor(ElementalInfuserRecipeTypeRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
						for (ElementalInfuserRecipeTypeRecipe recipe : recipes) {
							List<Ingredient> ingredients = recipe.getIngredients();
							if (!ingredients.get(0).test((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy())))
								continue;
							if (!ingredients.get(1).test((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 1).copy())))
								continue;
							if (!ingredients.get(2).test((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 2).copy())))
								continue;
							if (!ingredients.get(3).test((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 3).copy())))
								continue;
							if (!ingredients.get(4).test((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 4).copy())))
								continue;
							List<ItemStack> reciperesult = recipe.getResultItems();
							i = 0;
							for (int num : recipe.integers()) {
								double numberiterator = (double) num;
								if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
									int _slotid = (int) i;
									ItemStack _stk = _itemHandlerModifiable.getStackInSlot(_slotid).copy();
									_stk.shrink((int) numberiterator);
									_itemHandlerModifiable.setStackInSlot(_slotid, _stk);
								}
								i = i + 1;
							}
							break;
						}
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
			IItemHandler itemHandler = ext.getCapability(Capabilities.ItemHandler.BLOCK, pos, null);
			if (itemHandler != null)
				return itemHandler.getStackInSlot(slot);
		}
		return ItemStack.EMPTY;
	}

	private static double getBlockNBTNumber(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getDouble(tag);
		return -1;
	}
}