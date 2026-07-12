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
import net.minecraft.tags.ItemTags;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import java.util.stream.Collectors;
import java.util.List;

import com.lordkittycat.alternate_dimension.jei_recipes.IngotPresserRecipeTypeRecipe;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModRecipeTypes;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModBlocks;
import com.lordkittycat.alternate_dimension.AlternateDimensionMod;

public class PressIngotProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		ItemStack result = ItemStack.EMPTY;
		result = (new Object() {
			public ItemStack getResult() {
				List<IngotPresserRecipeTypeRecipe> recipes = null;
				if (world instanceof ServerLevel _lvl) {
					recipes = _lvl.recipeAccess().recipeMap().byType(IngotPresserRecipeTypeRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
				} else {
					recipes = AlternateDimensionModRecipeTypes.recipes.byType(IngotPresserRecipeTypeRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
				}
				for (IngotPresserRecipeTypeRecipe recipe : recipes) {
					List<Ingredient> ingredients = recipe.getIngredients();
					if (!ingredients.get(0).test((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy())))
						continue;
					return recipe.getResultItems().get(0).copy();
				}
				return ItemStack.EMPTY;
			}
		}.getResult()).copy();
		if (getEnergyStored(world, BlockPos.containing(x, y, z), null) >= 50 * (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy()).getCount()
				&& ((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy()).is(ItemTags.create(Identifier.parse("alternate_dimension:basic_ingot_presser_whitelist")))
						|| (world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == AlternateDimensionModBlocks.ADVANCED_INGOT_PRESSER.get()
						|| (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy()).getItem() == ItemStack.EMPTY.getItem())) {
			{
				BlockPos _pos = BlockPos.containing(x, y, z);
				BlockState _bs = world.getBlockState(_pos);
				if (_bs.getBlock().getStateDefinition().getProperty("running") instanceof BooleanProperty _booleanProp)
					world.setBlock(_pos, _bs.setValue(_booleanProp, true), 3);
			}
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("block.piston.extend")), SoundSource.PLAYERS, 1, 1);
				} else {
					_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("block.piston.extend")), SoundSource.PLAYERS, 1, 1, false);
				}
			}
			AlternateDimensionMod.queueServerWork(10, () -> {
				{
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("running") instanceof BooleanProperty _booleanProp)
						world.setBlock(_pos, _bs.setValue(_booleanProp, false), 3);
				}
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("block.piston.contract")), SoundSource.PLAYERS, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("block.piston.contract")), SoundSource.PLAYERS, 1, 1, false);
					}
				}
			});
			if (!(result.getItem() == ItemStack.EMPTY.getItem())) {
				if (world instanceof ILevelExtension _ext) {
					if (_ext.getCapability(Capabilities.Energy.BLOCK, BlockPos.containing(x, y, z), null) instanceof EnergyHandler _energyHandler) {
						try (var _tx = Transaction.openRoot()) {
							_energyHandler.extract(50 * (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy()).getCount(), _tx);
							_tx.commit();
						}
					}
				}
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("block.anvil.land")), SoundSource.PLAYERS, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("block.anvil.land")), SoundSource.PLAYERS, 1, 1, false);
					}
				}
				if (world instanceof ServerLevel _serverLevel) {
					BlockEntity _be = _serverLevel.getBlockEntity(BlockPos.containing(x, y, z));
					if (_be instanceof Container _container) {
						ItemStack _setstack = result.copy();
						_setstack.setCount(result.getCount() * (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy()).getCount());
						_container.setItem(0, _setstack);
					}
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
}