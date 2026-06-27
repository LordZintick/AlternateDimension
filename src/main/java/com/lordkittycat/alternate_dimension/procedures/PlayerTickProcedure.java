package com.lordkittycat.alternate_dimension.procedures;

import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.MenuProvider;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import javax.annotation.Nullable;

import io.netty.buffer.Unpooled;

import com.lordkittycat.alternate_dimension.world.inventory.IntroScreenMenu;
import com.lordkittycat.alternate_dimension.network.AlternateDimensionModVariables;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModItems;

@EventBusSubscriber
public class PlayerTickProcedure {
	@SubscribeEvent
	public static void onPlayerTick(PlayerTickEvent.Post event) {
		execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity());
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (entity.getData(AlternateDimensionModVariables.PLAYER_VARIABLES).jetpackHover && entity.getDeltaMovement().y() < 0
				&& (hasEntityInInventory(entity, new ItemStack(AlternateDimensionModItems.CANDY_CANE.get())) || entity.getData(AlternateDimensionModVariables.PLAYER_VARIABLES).burnTime > 0)
				&& entity.getData(AlternateDimensionModVariables.PLAYER_VARIABLES).jetpackEngine
				&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY).getItem() == AlternateDimensionModItems.CANDY_ARMOR_CHESTPLATE.get()) {
			if (entity instanceof LivingEntity _livingEntity4 && _livingEntity4.getAttributes().hasAttribute(Attributes.FLYING_SPEED))
				_livingEntity4.getAttribute(Attributes.FLYING_SPEED).setBaseValue(0.7);
			entity.fallDistance = 0;
			entity.setDeltaMovement(new Vec3((entity.getDeltaMovement().x()), 0, (entity.getDeltaMovement().z())));
			if (hasEntityInInventory(entity, new ItemStack(AlternateDimensionModItems.CANDY_CANE.get())) && entity.getData(AlternateDimensionModVariables.PLAYER_VARIABLES).burnTime <= 0) {
				if (entity instanceof Player _player) {
					ItemStack _stktoremove = new ItemStack(AlternateDimensionModItems.CANDY_CANE.get());
					_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1, _player.inventoryMenu.getCraftSlots());
				}
				{
					AlternateDimensionModVariables.PlayerVariables _vars = entity.getData(AlternateDimensionModVariables.PLAYER_VARIABLES);
					_vars.burnTime = 20;
					_vars.markSyncDirty();
				}
			} else {
				{
					AlternateDimensionModVariables.PlayerVariables _vars = entity.getData(AlternateDimensionModVariables.PLAYER_VARIABLES);
					_vars.burnTime = entity.getData(AlternateDimensionModVariables.PLAYER_VARIABLES).burnTime - 1;
					_vars.markSyncDirty();
				}
			}
		} else {
			if (entity instanceof LivingEntity _livingEntity11 && _livingEntity11.getAttributes().hasAttribute(Attributes.FLYING_SPEED))
				_livingEntity11.getAttribute(Attributes.FLYING_SPEED).setBaseValue(0.1);
		}
		if (AlternateDimensionModVariables.WorldVariables.get(world).meteorCooldown > 0) {
			AlternateDimensionModVariables.WorldVariables.get(world).meteorCooldown = AlternateDimensionModVariables.WorldVariables.get(world).meteorCooldown - 1;
			AlternateDimensionModVariables.WorldVariables.get(world).markSyncDirty();
		} else {
			if (Mth.nextDouble(RandomSource.create(), 0, 1000) == 10 && world.canSeeSkyFromBelowWater(BlockPos.containing(x, y, z))) {
				SpawnMeteorProcedure.execute(world, x + Mth.nextInt(RandomSource.create(), -128, 128), y, z + Mth.nextInt(RandomSource.create(), -128, 128), entity);
				AlternateDimensionModVariables.WorldVariables.get(world).meteorCooldown = 2400;
				AlternateDimensionModVariables.WorldVariables.get(world).markSyncDirty();
			}
		}
		if ((entity.level().dimension()) == Level.OVERWORLD && (entity.getData(AlternateDimensionModVariables.PLAYER_VARIABLES).chosenPlanet).equals("") && !(entity instanceof Player _plr19 && _plr19.containerMenu instanceof IntroScreenMenu)) {
			if (entity instanceof ServerPlayer _ent) {
				BlockPos _bpos = BlockPos.containing(x, y, z);
				_ent.openMenu(new MenuProvider() {
					@Override
					public Component getDisplayName() {
						return Component.literal("IntroScreen");
					}

					@Override
					public boolean shouldTriggerClientSideContainerClosingOnOpen() {
						return false;
					}

					@Override
					public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
						return new IntroScreenMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
					}
				}, _bpos);
			}
		}
		if (entity.getData(AlternateDimensionModVariables.PLAYER_VARIABLES).mana < 100) {
			{
				AlternateDimensionModVariables.PlayerVariables _vars = entity.getData(AlternateDimensionModVariables.PLAYER_VARIABLES);
				_vars.mana = entity.getData(AlternateDimensionModVariables.PLAYER_VARIABLES).mana + 0.5;
				_vars.markSyncDirty();
			}
		}
	}

	private static boolean hasEntityInInventory(Entity entity, ItemStack itemstack) {
		if (entity instanceof Player player)
			return player.getInventory().contains(stack -> !stack.isEmpty() && ItemStack.isSameItem(stack, itemstack));
		return false;
	}
}