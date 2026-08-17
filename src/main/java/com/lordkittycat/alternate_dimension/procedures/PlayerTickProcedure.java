package com.lordkittycat.alternate_dimension.procedures;

import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.MenuProvider;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.network.chat.Component;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.BlockPos;

import javax.annotation.Nullable;

import io.netty.buffer.Unpooled;

import com.lordkittycat.alternate_dimension.world.inventory.IntroScreenMenu;
import com.lordkittycat.alternate_dimension.network.AlternateDimensionModVariables;

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
		double meteorX = 0;
		double meteorZ = 0;
		if ((entity.level().dimension()) == Level.OVERWORLD && !(entity instanceof Player _plr3 && _plr3.containerMenu instanceof IntroScreenMenu)) {
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
		if ((entity.position()).distanceTo(AlternateDimensionModVariables.WorldVariables.get(world).lastMeteorPos) >= 200 && AlternateDimensionModVariables.WorldVariables.get(world).meteorCooldown <= 0
				&& (entity.level().dimension()) == ResourceKey.create(Registries.DIMENSION, Identifier.parse("alternate_dimension:eleroth"))) {
			meteorX = x + Mth.nextInt(RandomSource.create(), -32, 32);
			meteorZ = z + Mth.nextInt(RandomSource.create(), -32, 32);
			AlternateDimensionModVariables.WorldVariables.get(world).meteorCooldown = 6000;
			AlternateDimensionModVariables.WorldVariables.get(world).markSyncDirty();
			SpawnMeteorProcedure.execute(world, meteorX, world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) meteorX, (int) meteorZ), meteorZ);
		}
	}
}