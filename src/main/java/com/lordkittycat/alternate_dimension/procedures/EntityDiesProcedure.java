package com.lordkittycat.alternate_dimension.procedures;

import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.ICancellableEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.level.gamerules.GameRules;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

import javax.annotation.Nullable;

import com.lordkittycat.alternate_dimension.network.AlternateDimensionModVariables;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModMobEffects;

@EventBusSubscriber
public class EntityDiesProcedure {
	@SubscribeEvent
	public static void onEntityDeath(LivingDeathEvent event) {
		if (event.getEntity() != null) {
			execute(event, event.getEntity().level(), event.getEntity());
		}
	}

	public static void execute(LevelAccessor world, Entity entity) {
		execute(null, world, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if ((BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType()).toString()).equals("minecraft:player")) {
			if (world instanceof ServerLevel _serverLevelGR1 && _serverLevelGR1.getGameRules().get(GameRules.KEEP_INVENTORY)) {
				if (entity instanceof LivingEntity _entity)
					_entity.setHealth(entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1);
				if (entity instanceof LivingEntity _entity)
					_entity.removeAllEffects();
				if (event instanceof ICancellableEvent _cancellable) {
					_cancellable.setCanceled(true);
				}
				{
					Entity _ent = entity;
					double _tx = entity.getData(AlternateDimensionModVariables.PLAYER_VARIABLES).spawnX;
					double _ty = entity.getData(AlternateDimensionModVariables.PLAYER_VARIABLES).spawnY;
					double _tz = entity.getData(AlternateDimensionModVariables.PLAYER_VARIABLES).spawnZ;
					_ent.teleportTo(_tx, _ty, _tz);
					if (_ent instanceof ServerPlayer _serverPlayer)
						_serverPlayer.connection.teleport(_tx, _ty, _tz, _ent.getYRot(), _ent.getXRot());
				}
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(entity.getData(AlternateDimensionModVariables.PLAYER_VARIABLES).spawnX, entity.getData(AlternateDimensionModVariables.PLAYER_VARIABLES).spawnY,
								entity.getData(AlternateDimensionModVariables.PLAYER_VARIABLES).spawnZ), BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("particle.soul_escape")), SoundSource.PLAYERS, 1, 1);
					} else {
						_level.playLocalSound(entity.getData(AlternateDimensionModVariables.PLAYER_VARIABLES).spawnX, entity.getData(AlternateDimensionModVariables.PLAYER_VARIABLES).spawnY,
								entity.getData(AlternateDimensionModVariables.PLAYER_VARIABLES).spawnZ, BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("particle.soul_escape")), SoundSource.PLAYERS, 1, 1, false);
					}
				}
				if (entity instanceof Player _player)
					_player.getFoodData().setFoodLevel(20);
			}
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(AlternateDimensionModMobEffects.LIGHT_FOOTED_EFFECT, 100, 0));
		}
	}
}