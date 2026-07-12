package com.lordkittycat.alternate_dimension.procedures;

import net.neoforged.neoforge.transfer.transaction.Transaction;
import net.neoforged.neoforge.transfer.item.ItemUtil;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.energy.EnergyHandler;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.common.extensions.ILevelExtension;
import net.neoforged.neoforge.capabilities.Capabilities;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.Container;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.tags.ItemTags;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

import java.util.stream.Collectors;
import java.util.List;

import com.lordkittycat.alternate_dimension.jei_recipes.NuclearReactorRecipeTypeRecipe;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModRecipeTypes;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModBlocks;

public class UpdateNuclearReactorProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if ((world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == AlternateDimensionModBlocks.COOLANT_RODS.get()
				&& (world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == AlternateDimensionModBlocks.COOLANT_RODS.get()) {
			if ((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy()).is(ItemTags.create(Identifier.parse("alternate_dimension:fissile_fuel"))) && getBlockNBTNumber(world, BlockPos.containing(x, y, z), "burnTime") <= 0) {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putDouble("burnTime", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "burnTime")
								+ (world instanceof Level _levelFV9 ? (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy()).getBurnTime(null, _levelFV9.fuelValues()) : 0)));
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
			if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "burnTime") > 0 && !((new Object() {
				public ItemStack getResult() {
					List<NuclearReactorRecipeTypeRecipe> recipes = null;
					if (world instanceof ServerLevel _lvl) {
						recipes = _lvl.recipeAccess().recipeMap().byType(NuclearReactorRecipeTypeRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
					} else {
						recipes = AlternateDimensionModRecipeTypes.recipes.byType(NuclearReactorRecipeTypeRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
					}
					for (NuclearReactorRecipeTypeRecipe recipe : recipes) {
						List<Ingredient> ingredients = recipe.getIngredients();
						if (!ingredients.get(0).test((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 2).copy())))
							continue;
						return recipe.getResultItems().get(0).copy();
					}
					return ItemStack.EMPTY;
				}
			}.getResult()).getItem() == ItemStack.EMPTY.getItem())) {
				if ((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 1).copy()).getItem() == ItemStack.EMPTY.getItem() || (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 1).copy()).getItem() == (new Object() {
					public ItemStack getResult() {
						List<NuclearReactorRecipeTypeRecipe> recipes = null;
						if (world instanceof ServerLevel _lvl) {
							recipes = _lvl.recipeAccess().recipeMap().byType(NuclearReactorRecipeTypeRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
						} else {
							recipes = AlternateDimensionModRecipeTypes.recipes.byType(NuclearReactorRecipeTypeRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
						}
						for (NuclearReactorRecipeTypeRecipe recipe : recipes) {
							List<Ingredient> ingredients = recipe.getIngredients();
							if (!ingredients.get(0).test((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 2).copy())))
								continue;
							return recipe.getResultItems().get(0).copy();
						}
						return ItemStack.EMPTY;
					}
				}.getResult()).getItem() && (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 1).copy()).getCount() < (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 1).copy()).getMaxStackSize()) {
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
						if (world instanceof ServerLevel _serverLevel) {
							BlockEntity _be = _serverLevel.getBlockEntity(BlockPos.containing(x, y, z));
							if (_be instanceof Container _container) {
								ItemStack _setstack = (new Object() {
									public ItemStack getResult() {
										List<NuclearReactorRecipeTypeRecipe> recipes = null;
										if (world instanceof ServerLevel _lvl) {
											recipes = _lvl.recipeAccess().recipeMap().byType(NuclearReactorRecipeTypeRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
										} else {
											recipes = AlternateDimensionModRecipeTypes.recipes.byType(NuclearReactorRecipeTypeRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
										}
										for (NuclearReactorRecipeTypeRecipe recipe : recipes) {
											List<Ingredient> ingredients = recipe.getIngredients();
											if (!ingredients.get(0).test((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 2).copy())))
												continue;
											return recipe.getResultItems().get(0).copy();
										}
										return ItemStack.EMPTY;
									}
								}.getResult()).copy();
								_setstack.setCount((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 1).copy()).getCount() + 1);
								_container.setItem(1, _setstack);
							}
						}
						if (world instanceof ServerLevel _serverLevel) {
							BlockEntity _be = _serverLevel.getBlockEntity(BlockPos.containing(x, y, z));
							if (_be instanceof Container _container) {
								_container.getItem(2).shrink(1);
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
			}
			{
				BlockPos _pos = BlockPos.containing(x, y, z);
				BlockState _bs = world.getBlockState(_pos);
				if (_bs.getBlock().getStateDefinition().getProperty("running") instanceof BooleanProperty _booleanProp)
					world.setBlock(_pos, _bs.setValue(_booleanProp, (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "burnTime") > 0)), 3);
			}
			if (getPropertyByName((world.getBlockState(BlockPos.containing(x, y, z))), "running") instanceof BooleanProperty _getbp41 && (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getbp41)) {
				if (world instanceof ILevelExtension _ext) {
					if (_ext.getCapability(Capabilities.Energy.BLOCK, BlockPos.containing(x, y, z), null) instanceof EnergyHandler _energyHandler) {
						try (var _tx = Transaction.openRoot()) {
							_energyHandler.insert(100, _tx);
							_tx.commit();
						}
					}
				}
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putDouble("burnTime", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "burnTime") - 1));
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
				if (Mth.nextInt(RandomSource.create(), 0, 10) == 0) {
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null) {
							_blockEntity.getPersistentData().putDouble("heat", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "heat") + 1));
						}
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
				}
			}
			if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "heat") >= 100) {
				if (world instanceof Level _level && !_level.isClientSide())
					_level.explode(null, x, y, z, 4, Level.ExplosionInteraction.BLOCK);
			}
			PushEnergyProcedure.execute(world, x, y, z);
		} else if (getPropertyByName((world.getBlockState(BlockPos.containing(x, y, z))), "running") instanceof BooleanProperty _getbp51 && (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getbp51)) {
			if (world instanceof Level _level && !_level.isClientSide())
				_level.explode(null, x, y, z, 4, Level.ExplosionInteraction.BLOCK);
		}
		if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "heat") > 74 && (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 3).copy()).getItem() == Items.WATER_BUCKET) {
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("block.lava.extinguish")), SoundSource.BLOCKS, 1, 1);
				} else {
					_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("block.lava.extinguish")), SoundSource.BLOCKS, 1, 1, false);
				}
			}
			if (world instanceof ServerLevel _serverLevel) {
				BlockEntity _be = _serverLevel.getBlockEntity(BlockPos.containing(x, y, z));
				if (_be instanceof Container _container) {
					ItemStack _setstack = new ItemStack(Items.BUCKET).copy();
					_setstack.setCount(1);
					_container.setItem(3, _setstack);
				}
			}
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putDouble("heat", 0);
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
		} else if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "heat") > 74 && (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 3).copy()).is(ItemTags.create(Identifier.parse("alternate_dimension:coolant")))) {
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("block.lava.extinguish")), SoundSource.BLOCKS, 1, 1);
				} else {
					_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("block.lava.extinguish")), SoundSource.BLOCKS, 1, 1, false);
				}
			}
			if (world instanceof ServerLevel _serverLevel) {
				BlockEntity _be = _serverLevel.getBlockEntity(BlockPos.containing(x, y, z));
				if (_be instanceof Container _container) {
					_container.getItem(3).shrink(1);
				}
			}
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putDouble("heat", 0);
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

	private static double getBlockNBTNumber(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getDoubleOr(tag, 0);
		return -1;
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