package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.BlockPos;

import com.lordkittycat.alternate_dimension.init.AlternateDimensionModItems;

public class FinishPortalWandEffectsProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double targetX = 0;
		double targetY = 0;
		double targetZ = 0;
		if (world instanceof Level _level) {
			if (!_level.isClientSide()) {
				_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("block.portal.trigger")), SoundSource.PLAYERS, 1, 1);
			} else {
				_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("block.portal.trigger")), SoundSource.PLAYERS, 1, 1, false);
			}
		}
		if (world instanceof ServerLevel _level)
			_level.sendParticles(ParticleTypes.PORTAL, x, y, z, 50, 3, 3, 3, 1);
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem() == AlternateDimensionModItems.POSITION_CARD.get()) {
			targetX = (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDoubleOr("targetX", 0);
			targetY = (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDoubleOr("targetY", 0);
			targetZ = (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDoubleOr("targetZ", 0);
		} else if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem() == AlternateDimensionModItems.DEATH_POS_CARD.get()) {
			targetX = (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDoubleOr("posX", 0);
			targetY = (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDoubleOr("posY", 0) + 1;
			targetZ = (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDoubleOr("posZ", 0);
		} else {
			targetX = (entity instanceof ServerPlayer _player)
					? (_player.getRespawnConfig() != null && (ServerPlayer.RespawnConfig.getDimensionOrDefault(_player.getRespawnConfig()).equals(_player.level().dimension()))
							? _player.getRespawnConfig().respawnData().pos().getX()
							: _player.level().getLevelData().getRespawnData().pos().getX())
					: 0;
			targetY = (entity instanceof ServerPlayer _player)
					? (_player.getRespawnConfig() != null && (ServerPlayer.RespawnConfig.getDimensionOrDefault(_player.getRespawnConfig()).equals(_player.level().dimension()))
							? _player.getRespawnConfig().respawnData().pos().getY()
							: _player.level().getLevelData().getRespawnData().pos().getY())
					: 0;
			targetZ = (entity instanceof ServerPlayer _player)
					? (_player.getRespawnConfig() != null && (ServerPlayer.RespawnConfig.getDimensionOrDefault(_player.getRespawnConfig()).equals(_player.level().dimension()))
							? _player.getRespawnConfig().respawnData().pos().getZ()
							: _player.level().getLevelData().getRespawnData().pos().getZ())
					: 0;
		}
		if (world instanceof ServerLevel _level)
			_level.sendParticles(ParticleTypes.PORTAL, targetX, (targetY + 1), targetZ, 50, 3, 3, 3, 1);
		if (world instanceof Level _level) {
			if (!_level.isClientSide()) {
				_level.playSound(null, BlockPos.containing(targetX, targetY + 1, targetZ), BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("block.portal.trigger")), SoundSource.PLAYERS, 1, 1);
			} else {
				_level.playLocalSound(targetX, (targetY + 1), targetZ, BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("block.portal.trigger")), SoundSource.PLAYERS, 1, 1, false);
			}
		}
		PlacePortalProcedure.execute(world, targetX, targetY, targetZ, entity, false, x, y, z);
		PlacePortalProcedure.execute(world, x, y, z, entity, false, targetX, targetY, targetZ);
		{
			Entity _ent = entity;
			double _tx = targetX;
			double _ty = (targetY + 1);
			double _tz = targetZ;
			_ent.teleportTo(_tx, _ty, _tz);
			if (_ent instanceof ServerPlayer _serverPlayer)
				_serverPlayer.connection.teleport(_tx, _ty, _tz, _ent.getYRot(), _ent.getXRot());
		}
	}
}