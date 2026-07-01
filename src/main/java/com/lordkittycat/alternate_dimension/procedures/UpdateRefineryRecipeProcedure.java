package com.lordkittycat.alternate_dimension.procedures;

import org.checkerframework.checker.units.qual.s;

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
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import java.util.stream.Collectors;
import java.util.List;
import java.util.ArrayList;

import com.lordkittycat.alternate_dimension.jei_recipes.RefineryRecipeTypeRecipe;

public class UpdateRefineryRecipeProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		List<ItemStack> result = new ArrayList<>();
		ItemStack resultTest = ItemStack.EMPTY;
		ItemStack ingredient1 = ItemStack.EMPTY;
		ItemStack ingredient2 = ItemStack.EMPTY;
		double i = 0;
		double count0 = 0;
		double count1 = 0;
		boolean check = false;
		boolean powerCheck = false;
		ingredient1 = (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy()).copy();
		ingredient2 = (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 1).copy()).copy();
		resultTest = (new Object() {
			public ItemStack getResult() {
				if (world instanceof Level _lvl) {
					net.minecraft.world.item.crafting.RecipeManager rm = _lvl.getRecipeManager();
					List<RefineryRecipeTypeRecipe> recipes = rm.getAllRecipesFor(RefineryRecipeTypeRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
					for (RefineryRecipeTypeRecipe recipe : recipes) {
						NonNullList<Ingredient> ingredients = recipe.getIngredients();
						if (!ingredients.get(0).test((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy())))
							continue;
						if (!ingredients.get(1).test((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 1).copy())))
							continue;
						return recipe.getResultItems().get(0).copy();
					}
				}
				return ItemStack.EMPTY;
			}
		}.getResult()).copy();
		if (resultTest.getItem() == ItemStack.EMPTY.getItem()) {
			ingredient1 = (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 1).copy()).copy();
			ingredient2 = (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy()).copy();
			resultTest = (new Object() {
				public ItemStack getResult() {
					if (world instanceof Level _lvl) {
						net.minecraft.world.item.crafting.RecipeManager rm = _lvl.getRecipeManager();
						List<RefineryRecipeTypeRecipe> recipes = rm.getAllRecipesFor(RefineryRecipeTypeRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
						for (RefineryRecipeTypeRecipe recipe : recipes) {
							NonNullList<Ingredient> ingredients = recipe.getIngredients();
							if (!ingredients.get(0).test((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 1).copy())))
								continue;
							if (!ingredients.get(1).test((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy())))
								continue;
							return recipe.getResultItems().get(0).copy();
						}
					}
					return ItemStack.EMPTY;
				}
			}.getResult()).copy();
		}
		if (!(resultTest.getItem() == ItemStack.EMPTY.getItem())) {
			{
				if (world instanceof Level _lvl) {
					net.minecraft.world.item.crafting.RecipeManager rm = _lvl.getRecipeManager();
					List<RefineryRecipeTypeRecipe> recipes = rm.getAllRecipesFor(RefineryRecipeTypeRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
					for (RefineryRecipeTypeRecipe recipe : recipes) {
						List<Ingredient> ingredients = recipe.getIngredients();
						if (!ingredients.get(0).test(ingredient1))
							continue;
						if (!ingredients.get(1).test(ingredient2))
							continue;
						List<ItemStack> reciperesult = recipe.getResultItems();
						result = reciperesult;
						break;
					}
				}
			}
			check = getEnergyStored(world, BlockPos.containing(x, y, z), null) >= 500 && getBlockNBTLogic(world, BlockPos.containing(x, y, z), "on");
			if (check) {
				i = 0;
				{
					if (world instanceof Level _lvl) {
						net.minecraft.world.item.crafting.RecipeManager rm = _lvl.getRecipeManager();
						List<RefineryRecipeTypeRecipe> recipes = rm.getAllRecipesFor(RefineryRecipeTypeRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
						for (RefineryRecipeTypeRecipe recipe : recipes) {
							List<Ingredient> ingredients = recipe.getIngredients();
							if (!ingredients.get(0).test(ingredient1))
								continue;
							if (!ingredients.get(1).test(ingredient2))
								continue;
							List<ItemStack> reciperesult = recipe.getResultItems();
							for (int num : recipe.integers()) {
								double numberiterator = (double) num;
								if (i == 0) {
									count0 = numberiterator;
								} else if (i == 1) {
									count1 = numberiterator;
								}
								if ((itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) i).copy()).getCount() < numberiterator) {
									check = false;
									break;
								}
								i = i + 1;
							}
							break;
						}
					}
				}
			}
			if (check) {
				i = 2;
				for (ItemStack itemstack : result) {
					ItemStack itemstackiterator = itemstack.copy();
					if (!((itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) i).copy()).getItem() == itemstackiterator.getItem())
							&& !((itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) i).copy()).getItem() == ItemStack.EMPTY.getItem())
							|| (itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) i).copy()).getItem() == itemstackiterator.getItem() && (itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) i).copy())
									.getCount() > (itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) i).copy()).getMaxStackSize() - itemstackiterator.getCount()) {
						check = false;
						break;
					}
					i = i + 1;
				}
			}
			if (check) {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putDouble("progress", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "progress") + 0.25));
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
				{
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("running") instanceof BooleanProperty _booleanProp)
						world.setBlock(_pos, _bs.setValue(_booleanProp, true), 3);
				}
				if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "progress") > 100 && getEnergyStored(world, BlockPos.containing(x, y, z), null) >= 500) {
					if (world instanceof ILevelExtension _ext) {
						IEnergyStorage _entityStorage = _ext.getCapability(Capabilities.EnergyStorage.BLOCK, BlockPos.containing(x, y, z), null);
						if (_entityStorage != null)
							_entityStorage.extractEnergy(500, false);
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
					{
						BlockPos _pos = BlockPos.containing(x, y, z);
						BlockState _bs = world.getBlockState(_pos);
						if (_bs.getBlock().getStateDefinition().getProperty("running") instanceof BooleanProperty _booleanProp)
							world.setBlock(_pos, _bs.setValue(_booleanProp, false), 3);
					}
					powerCheck = true;
					{
						if (world instanceof Level _lvl) {
							net.minecraft.world.item.crafting.RecipeManager rm = _lvl.getRecipeManager();
							List<RefineryRecipeTypeRecipe> recipes = rm.getAllRecipesFor(RefineryRecipeTypeRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
							for (RefineryRecipeTypeRecipe recipe : recipes) {
								List<Ingredient> ingredients = recipe.getIngredients();
								if (!ingredients.get(0).test(ingredient1))
									continue;
								if (!ingredients.get(1).test(ingredient2))
									continue;
								List<ItemStack> reciperesult = recipe.getResultItems();
								for (String stringiterator : recipe.strings()) {
									if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "rpu") != new Object() {
										double convert(String s) {
											try {
												return Double.parseDouble(s.trim());
											} catch (Exception e) {
											}
											return 0;
										}
									}.convert(stringiterator)) {
										powerCheck = false;
										break;
									}
								}
								break;
							}
						}
					}
					if (powerCheck) {
						if (world instanceof Level _level) {
							if (!_level.isClientSide()) {
								_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.redstone_torch.burnout")), SoundSource.BLOCKS, 1, 1);
							} else {
								_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.redstone_torch.burnout")), SoundSource.BLOCKS, 1, 1, false);
							}
						}
						i = 2;
						for (ItemStack itemstack : result) {
							ItemStack itemstackiterator = itemstack.copy();
							if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
								ItemStack _setstack = itemstackiterator.copy();
								_setstack.setCount((itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) i).copy()).getCount() + itemstackiterator.getCount());
								_itemHandlerModifiable.setStackInSlot((int) i, _setstack);
							}
							i = i + 1;
						}
					} else {
						if (world instanceof Level _level) {
							if (!_level.isClientSide()) {
								_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.dragon_fireball.explode")), SoundSource.BLOCKS, 1, 1);
							} else {
								_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.dragon_fireball.explode")), SoundSource.BLOCKS, 1, 1, false);
							}
						}
					}
					if (world instanceof ServerLevel _level)
						_level.sendParticles(ParticleTypes.SMOKE, x, y, z, 15, 0, 0, 0, 1);
					if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
						int _slotid = 0;
						ItemStack _stk = _itemHandlerModifiable.getStackInSlot(_slotid).copy();
						_stk.shrink((int) count0);
						_itemHandlerModifiable.setStackInSlot(_slotid, _stk);
					}
					if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
						int _slotid = 1;
						ItemStack _stk = _itemHandlerModifiable.getStackInSlot(_slotid).copy();
						_stk.shrink((int) count1);
						_itemHandlerModifiable.setStackInSlot(_slotid, _stk);
					}
				}
			} else {
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

	private static boolean getBlockNBTLogic(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getBoolean(tag);
		return false;
	}

	private static double getBlockNBTNumber(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getDouble(tag);
		return -1;
	}
}