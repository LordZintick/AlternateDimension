package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

import com.lordkittycat.alternate_dimension.network.AlternateDimensionModVariables;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModItems;

public class JetpackActivateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if ((!entity.getData(AlternateDimensionModVariables.PLAYER_VARIABLES).jetpackHover && hasEntityInInventory(entity, new ItemStack(AlternateDimensionModItems.CANDY_CANE.get()))
				|| entity.getData(AlternateDimensionModVariables.PLAYER_VARIABLES).jetpackHover) && entity.getData(AlternateDimensionModVariables.PLAYER_VARIABLES).jetpackEngine
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY).getItem() == AlternateDimensionModItems.CANDY_ARMOR_CHESTPLATE.get()) {
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("item.firecharge.use")), SoundSource.PLAYERS, (float) 0.25, 1);
				} else {
					_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("item.firecharge.use")), SoundSource.PLAYERS, (float) 0.25, 1, false);
				}
			}
			if (entity.getDeltaMovement().y() < 0) {
				entity.push((entity.getDeltaMovement().x()), (entity.getDeltaMovement().y() * (-4)), (entity.getDeltaMovement().z()));
			} else {
				entity.push((entity.getDeltaMovement().x()), 1, (entity.getDeltaMovement().z()));
			}
			if (!entity.getData(AlternateDimensionModVariables.PLAYER_VARIABLES).jetpackHover && hasEntityInInventory(entity, new ItemStack(AlternateDimensionModItems.CANDY_CANE.get()))) {
				if (entity instanceof Player _player) {
					ItemStack _stktoremove = new ItemStack(AlternateDimensionModItems.CANDY_CANE.get());
					_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1, _player.inventoryMenu.getCraftSlots());
				}
			}
		}
	}

	private static boolean hasEntityInInventory(Entity entity, ItemStack itemstack) {
		if (entity instanceof Player player)
			return player.getInventory().contains(stack -> !stack.isEmpty() && ItemStack.isSameItem(stack, itemstack));
		return false;
	}
}