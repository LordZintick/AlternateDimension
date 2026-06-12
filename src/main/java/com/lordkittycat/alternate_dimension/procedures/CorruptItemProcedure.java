package com.lordkittycat.alternate_dimension.procedures;

import net.neoforged.neoforge.energy.IEnergyStorage;
import net.neoforged.neoforge.common.extensions.ILevelExtension;
import net.neoforged.neoforge.capabilities.Capabilities;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import java.util.stream.Collectors;
import java.util.List;

import com.lordkittycat.alternate_dimension.jei_recipes.CorruptorRecipeTypeRecipe;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModMenus;

public class CorruptItemProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		ItemStack result = ItemStack.EMPTY;
		result = (new Object() {
			public ItemStack getResult() {
				if (world instanceof Level _lvl) {
					net.minecraft.world.item.crafting.RecipeManager rm = _lvl.getRecipeManager();
					List<CorruptorRecipeTypeRecipe> recipes = rm.getAllRecipesFor(CorruptorRecipeTypeRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
					for (CorruptorRecipeTypeRecipe recipe : recipes) {
						NonNullList<Ingredient> ingredients = recipe.getIngredients();
						if (!ingredients.get(0).test((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof AlternateDimensionModMenus.MenuAccessor _menu0 ? _menu0.getSlots().get(0).getItem() : ItemStack.EMPTY)))
							continue;
						return recipe.getResultItems().get(0).copy();
					}
				}
				return ItemStack.EMPTY;
			}
		}.getResult()).copy();
		if (getEnergyStored(world, BlockPos.containing(x, y, z), null) >= 50
				* (entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof AlternateDimensionModMenus.MenuAccessor _menu3 ? _menu3.getSlots().get(0).getItem() : ItemStack.EMPTY).getCount()) {
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("particle.soul_escape")), SoundSource.PLAYERS, 1, 1);
				} else {
					_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("particle.soul_escape")), SoundSource.PLAYERS, 1, 1, false);
				}
			}
			if (!(result.getItem() == ItemStack.EMPTY.getItem())) {
				if (world instanceof ILevelExtension _ext) {
					IEnergyStorage _entityStorage = _ext.getCapability(Capabilities.EnergyStorage.BLOCK, BlockPos.containing(x, y, z), null);
					if (_entityStorage != null)
						_entityStorage.extractEnergy(
								50 * (entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof AlternateDimensionModMenus.MenuAccessor _menu8 ? _menu8.getSlots().get(0).getItem() : ItemStack.EMPTY).getCount(), false);
				}
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.experience_orb.pickup")), SoundSource.PLAYERS, 1, (float) 0.5);
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.experience_orb.pickup")), SoundSource.PLAYERS, 1, (float) 0.5, false);
					}
				}
				if (entity instanceof Player _player && _player.containerMenu instanceof AlternateDimensionModMenus.MenuAccessor _menu) {
					ItemStack _setstack15 = result.copy();
					_setstack15.setCount(
							result.getCount() * (entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof AlternateDimensionModMenus.MenuAccessor _menu13 ? _menu13.getSlots().get(0).getItem() : ItemStack.EMPTY).getCount());
					_menu.getSlots().get(0).set(_setstack15);
					_player.containerMenu.broadcastChanges();
				}
			}
		}
	}

	public static int getEnergyStored(LevelAccessor level, BlockPos pos, Direction direction) {
		if (level instanceof ILevelExtension levelExtension) {
			IEnergyStorage energyStorage = levelExtension.getCapability(Capabilities.EnergyStorage.BLOCK, pos, direction);
			if (energyStorage != null)
				return energyStorage.getEnergyStored();
		}
		return 0;
	}
}