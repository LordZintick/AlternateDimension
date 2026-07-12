package com.lordkittycat.alternate_dimension.procedures;

import net.neoforged.neoforge.transfer.item.ItemUtil;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.common.extensions.ILevelExtension;
import net.neoforged.neoforge.capabilities.Capabilities;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.Container;
import net.minecraft.tags.ItemTags;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

import java.util.stream.Collectors;
import java.util.List;

import com.lordkittycat.alternate_dimension.jei_recipes.ImbunementTableRecipeRecipe;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModRecipeTypes;
import com.lordkittycat.alternate_dimension.AlternateDimensionMod;

import com.github.lordzintick.item.ring.RingItem;
import com.github.lordzintick.item.ring.RingEffectComponent;
import com.github.lordzintick.init.AlternateDimensionModComponents;

public class UpdateImbunementTableRecipeProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		ItemStack result = ItemStack.EMPTY;
		if (!(getPropertyByName((world.getBlockState(BlockPos.containing(x, y, z))), "running") instanceof BooleanProperty _getbp1 && (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getbp1)) && (!((new Object() {
			public ItemStack getResult() {
				List<ImbunementTableRecipeRecipe> recipes = null;
				if (world instanceof ServerLevel _lvl) {
					recipes = _lvl.recipeAccess().recipeMap().byType(ImbunementTableRecipeRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
				} else {
					recipes = AlternateDimensionModRecipeTypes.recipes.byType(ImbunementTableRecipeRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
				}
				for (ImbunementTableRecipeRecipe recipe : recipes) {
					List<Ingredient> ingredients = recipe.getIngredients();
					if (!ingredients.get(0).test((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy())))
						continue;
					if (!ingredients.get(1).test((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 1).copy())))
						continue;
					return recipe.getResultItems().get(0).copy();
				}
				return ItemStack.EMPTY;
			}
		}.getResult()).getItem() == ItemStack.EMPTY.getItem()) || !((new Object() {
			public ItemStack getResult() {
				List<ImbunementTableRecipeRecipe> recipes = null;
				if (world instanceof ServerLevel _lvl) {
					recipes = _lvl.recipeAccess().recipeMap().byType(ImbunementTableRecipeRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
				} else {
					recipes = AlternateDimensionModRecipeTypes.recipes.byType(ImbunementTableRecipeRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
				}
				for (ImbunementTableRecipeRecipe recipe : recipes) {
					List<Ingredient> ingredients = recipe.getIngredients();
					if (!ingredients.get(0).test((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy())))
						continue;
					if (!ingredients.get(1).test((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 1).copy())))
						continue;
					return recipe.getResultItems().get(0).copy();
				}
				return ItemStack.EMPTY;
			}
		}.getResult()).getItem() == Blocks.AIR.asItem()))) {
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
					world.setBlock(_pos, _bs.setValue(_booleanProp, true), 3);
			}
			AlternateDimensionMod.queueServerWork(100, () -> {
				if (world instanceof ServerLevel _serverLevel) {
					BlockEntity _be = _serverLevel.getBlockEntity(BlockPos.containing(x, y, z));
					if (_be instanceof Container _container) {
						ItemStack _setstack = (new Object() {
							public ItemStack getResult() {
								List<ImbunementTableRecipeRecipe> recipes = null;
								if (world instanceof ServerLevel _lvl) {
									recipes = _lvl.recipeAccess().recipeMap().byType(ImbunementTableRecipeRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
								} else {
									recipes = AlternateDimensionModRecipeTypes.recipes.byType(ImbunementTableRecipeRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
								}
								for (ImbunementTableRecipeRecipe recipe : recipes) {
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
						_setstack.setCount((new Object() {
							public ItemStack getResult() {
								List<ImbunementTableRecipeRecipe> recipes = null;
								if (world instanceof ServerLevel _lvl) {
									recipes = _lvl.recipeAccess().recipeMap().byType(ImbunementTableRecipeRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
								} else {
									recipes = AlternateDimensionModRecipeTypes.recipes.byType(ImbunementTableRecipeRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
								}
								for (ImbunementTableRecipeRecipe recipe : recipes) {
									List<Ingredient> ingredients = recipe.getIngredients();
									if (!ingredients.get(0).test((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy())))
										continue;
									if (!ingredients.get(1).test((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 1).copy())))
										continue;
									return recipe.getResultItems().get(0).copy();
								}
								return ItemStack.EMPTY;
							}
						}.getResult()).getCount());
						_container.setItem(2, _setstack);
					}
				}
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("block.enchantment_table.use")), SoundSource.PLAYERS, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("block.enchantment_table.use")), SoundSource.PLAYERS, 1, 1, false);
					}
				}
				if (world instanceof ServerLevel _serverLevel) {
					BlockEntity _be = _serverLevel.getBlockEntity(BlockPos.containing(x, y, z));
					if (_be instanceof Container _container) {
						_container.getItem(0).shrink(1);
					}
				}
				if (world instanceof ServerLevel _serverLevel) {
					BlockEntity _be = _serverLevel.getBlockEntity(BlockPos.containing(x, y, z));
					if (_be instanceof Container _container) {
						_container.getItem(1).shrink(1);
					}
				}
				{
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("running") instanceof BooleanProperty _booleanProp)
						world.setBlock(_pos, _bs.setValue(_booleanProp, false), 3);
				}
			});
		}
		if (getPropertyByName((world.getBlockState(BlockPos.containing(x, y, z))), "running") instanceof BooleanProperty _getbp49 && (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getbp49)) {
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

	private static Property<?> getPropertyByName(BlockState state, String name) {
		for (Property<?> property : state.getProperties()) {
			if (property.getName().equals(name)) {
				return property;
			}
		}
		return null;
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