package com.lordkittycat.alternate_dimension.procedures;

import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.level.storage.LevelData;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.network.protocol.game.ClientboundUpdateMobEffectPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerAbilitiesPacket;
import net.minecraft.network.protocol.game.ClientboundLevelEventPacket;
import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.BlockPos;

import javax.annotation.Nullable;

import java.util.Set;

import com.lordkittycat.alternate_dimension.network.AlternateDimensionModVariables;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModItems;

@EventBusSubscriber
public class PlayerTickProcedure {
	@SubscribeEvent
	public static void onPlayerTick(PlayerTickEvent.Post event) {
		execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getZ(), event.getEntity());
	}

	public static void execute(LevelAccessor world, double x, double z, Entity entity) {
		execute(null, world, x, z, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double z, Entity entity) {
		if (entity == null)
			return;
		double meteorX = 0;
		double meteorZ = 0;
		if ((entity.level().dimension()) == Level.OVERWORLD) {
			if (world instanceof ServerLevel _origLevel) {
				LevelAccessor _switchworld7 = _origLevel.getServer().getLevel(ResourceKey.create(Registries.DIMENSION, Identifier.parse("alternate_dimension:eleroth")));
				if (_switchworld7 != null) {
					worldSwitch7(_switchworld7, x, z, entity);
				}
			}
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(AlternateDimensionModItems.HINT_BOOK.get()).copy();
				_setstack.setCount(1);
				_player.getInventory().placeItemBackInInventory(_setstack);
			}
			if (entity instanceof ServerPlayer _player && _player.level() instanceof ServerLevel _serverLevel) {
				ResourceKey<Level> destinationType = ResourceKey.create(Registries.DIMENSION, Identifier.parse("alternate_dimension:eleroth"));
				if (_player.level().dimension() == destinationType)
					return;
				ServerLevel nextLevel = _serverLevel.getServer().getLevel(destinationType);
				if (nextLevel != null) {
					_player.connection.send(new ClientboundGameEventPacket(ClientboundGameEventPacket.WIN_GAME, 0));
					_player.teleportTo(nextLevel, _player.getX(), _player.getY(), _player.getZ(), Set.of(), _player.getYRot(), _player.getXRot(), true);
					_player.connection.send(new ClientboundPlayerAbilitiesPacket(_player.getAbilities()));
					for (MobEffectInstance _effectinstance : _player.getActiveEffects())
						_player.connection.send(new ClientboundUpdateMobEffectPacket(_player.getId(), _effectinstance, false));
					_player.connection.send(new ClientboundLevelEventPacket(1032, BlockPos.ZERO, 0, false));
				}
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

	private static void worldSwitch7(LevelAccessor world, double x, double z, Entity entity) {
		if (entity instanceof ServerPlayer _serverPlayer)
			_serverPlayer.setRespawnPosition(new ServerPlayer.RespawnConfig(
					LevelData.RespawnData.of(_serverPlayer.level().dimension(), BlockPos.containing(0, Math.max(129, world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, 0, 0)), 0), _serverPlayer.getYRot(), _serverPlayer.getXRot()), true),
					false);
		{
			Entity _ent = entity;
			double _tx = 0;
			double _ty = Math.max(129, world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, 0, 0));
			double _tz = 0;
			_ent.teleportTo(_tx, _ty, _tz);
			if (_ent instanceof ServerPlayer _serverPlayer)
				_serverPlayer.connection.teleport(_tx, _ty, _tz, _ent.getYRot(), _ent.getXRot());
		}
	}
}