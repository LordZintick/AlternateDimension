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
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.Container;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import java.util.stream.Collectors;
import java.util.List;
import java.util.ArrayList;

import com.lordkittycat.alternate_dimension.jei_recipes.RefineryRecipeTypeRecipe;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModRecipeTypes;

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
				List<RefineryRecipeTypeRecipe> recipes = null;
				if (world instanceof ServerLevel _lvl) {
					recipes = _lvl.recipeAccess().recipeMap().byType(RefineryRecipeTypeRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
				} else {
					recipes = AlternateDimensionModRecipeTypes.recipes.byType(RefineryRecipeTypeRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
				}
				for (RefineryRecipeTypeRecipe recipe : recipes) {
					List<Ingredient> ingredients = recipe.getIngredients();
					if (!ingredients.get(0).test((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy())))
						continue;
					if (!ingredients.get(1).test((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 1).copy())))
						continue;
					return recipe.getResultItems().get(0).copy();
				}
				return ItemStack.EMPTY;
			}
		}.getResult()).copy();
		if (resultTest.getItem() == ItemStack.EMPTY.getItem()) {
			ingredient1 = (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 1).copy()).copy();
			ingredient2 = (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy()).copy();
			resultTest = (new Object() {
				public ItemStack getResult() {
					List<RefineryRecipeTypeRecipe> recipes = null;
					if (world instanceof ServerLevel _lvl) {
						recipes = _lvl.recipeAccess().recipeMap().byType(RefineryRecipeTypeRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
					} else {
						recipes = AlternateDimensionModRecipeTypes.recipes.byType(RefineryRecipeTypeRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
					}
					for (RefineryRecipeTypeRecipe recipe : recipes) {
						List<Ingredient> ingredients = recipe.getIngredients();
						if (!ingredients.get(0).test((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 1).copy())))
							continue;
						if (!ingredients.get(1).test((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy())))
							continue;
						return recipe.getResultItems().get(0).copy();
					}
					return ItemStack.EMPTY;
				}
			}.getResult()).copy();
		}
		if (!(resultTest.getItem() == ItemStack.EMPTY.getItem())) {
			{
				List<RefineryRecipeTypeRecipe> recipes = null;
				if (world instanceof ServerLevel _lvl) {
					recipes = _lvl.recipeAccess().recipeMap().byType(RefineryRecipeTypeRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
				} else {
					recipes = AlternateDimensionModRecipeTypes.recipes.byType(RefineryRecipeTypeRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
				}
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
			check = getEnergyStored(world, BlockPos.containing(x, y, z), null) >= 500 && getBlockNBTLogic(world, BlockPos.containing(x, y, z), "on");
			if (check) {
				i = 0;
				{
					List<RefineryRecipeTypeRecipe> recipes = null;
					if (world instanceof ServerLevel _lvl) {
						recipes = _lvl.recipeAccess().recipeMap().byType(RefineryRecipeTypeRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
					} else {
						recipes = AlternateDimensionModRecipeTypes.recipes.byType(RefineryRecipeTypeRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
					}
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
						if (_ext.getCapability(Capabilities.Energy.BLOCK, BlockPos.containing(x, y, z), null) instanceof EnergyHandler _energyHandler) {
							try (var _tx = Transaction.openRoot()) {
								_energyHandler.extract(500, _tx);
								_tx.commit();
							}
						}
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
						List<RefineryRecipeTypeRecipe> recipes = null;
						if (world instanceof ServerLevel _lvl) {
							recipes = _lvl.recipeAccess().recipeMap().byType(RefineryRecipeTypeRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
						} else {
							recipes = AlternateDimensionModRecipeTypes.recipes.byType(RefineryRecipeTypeRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
						}
						for (RefineryRecipeTypeRecipe recipe : recipes) {
							List<Ingredient> ingredients = recipe.getIngredients();
							if (!ingredients.get(0).test(ingredient1))
								continue;
							if (!ingredients.get(1).test(ingredient2))
								continue;
							List<ItemStack> reciperesult = recipe.getResultItems();
							for (String stringiterator : recipe.strings()) {
								if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "rpu") != parseDouble(stringiterator)) {
									powerCheck = false;
									break;
								}
							}
							break;
						}
					}
					if (powerCheck) {
						if (world instanceof Level _level) {
							if (!_level.isClientSide()) {
								_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("block.redstone_torch.burnout")), SoundSource.BLOCKS, 1, 1);
							} else {
								_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("block.redstone_torch.burnout")), SoundSource.BLOCKS, 1, 1, false);
							}
						}
						i = 2;
						for (ItemStack itemstack : result) {
							ItemStack itemstackiterator = itemstack.copy();
							if (world instanceof ServerLevel _serverLevel) {
								BlockEntity _be = _serverLevel.getBlockEntity(BlockPos.containing(x, y, z));
								if (_be instanceof Container _container) {
									ItemStack _setstack = itemstackiterator.copy();
									_setstack.setCount((itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) i).copy()).getCount() + itemstackiterator.getCount());
									_container.setItem((int) i, _setstack);
								}
							}
							i = i + 1;
						}
					} else {
						if (world instanceof Level _level) {
							if (!_level.isClientSide()) {
								_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("entity.dragon_fireball.explode")), SoundSource.BLOCKS, 1, 1);
							} else {
								_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("entity.dragon_fireball.explode")), SoundSource.BLOCKS, 1, 1, false);
							}
						}
					}
					if (world instanceof ServerLevel _level)
						_level.sendParticles(ParticleTypes.SMOKE, x, y, z, 15, 0, 0, 0, 1);
					if (world instanceof ServerLevel _serverLevel) {
						BlockEntity _be = _serverLevel.getBlockEntity(BlockPos.containing(x, y, z));
						if (_be instanceof Container _container) {
							_container.getItem(0).shrink((int) count0);
						}
					}
					if (world instanceof ServerLevel _serverLevel) {
						BlockEntity _be = _serverLevel.getBlockEntity(BlockPos.containing(x, y, z));
						if (_be instanceof Container _container) {
							_container.getItem(1).shrink((int) count1);
						}
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

	private static boolean getBlockNBTLogic(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getBooleanOr(tag, false);
		return false;
	}

	private static double getBlockNBTNumber(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getDoubleOr(tag, 0);
		return -1;
	}

	private static double parseDouble(String s) {
		try {
			return Double.parseDouble(s.trim());
		} catch (Exception e) {
			return 0;
		}
	}
}