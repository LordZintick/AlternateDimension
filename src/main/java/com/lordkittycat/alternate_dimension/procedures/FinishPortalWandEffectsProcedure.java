package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;

import com.lordkittycat.alternate_dimension.network.AlternateDimensionModVariables;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModBlocks;

public class FinishPortalWandEffectsProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (world instanceof Level _level) {
			if (!_level.isClientSide()) {
				_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("block.portal.trigger")), SoundSource.PLAYERS, 1, 1);
			} else {
				_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("block.portal.trigger")), SoundSource.PLAYERS, 1, 1, false);
			}
		}
		if (world instanceof ServerLevel _level)
			_level.sendParticles(ParticleTypes.PORTAL, x, y, z, 50, 3, 3, 3, 1);
		if (world instanceof ServerLevel _level)
			_level.sendParticles(ParticleTypes.PORTAL, entity.getData(AlternateDimensionModVariables.PLAYER_VARIABLES).spawnX, (entity.getData(AlternateDimensionModVariables.PLAYER_VARIABLES).spawnY + 1),
					entity.getData(AlternateDimensionModVariables.PLAYER_VARIABLES).spawnZ, 50, 3, 3, 3, 1);
		if (world instanceof Level _level) {
			if (!_level.isClientSide()) {
				_level.playSound(null, BlockPos.containing(entity.getData(AlternateDimensionModVariables.PLAYER_VARIABLES).spawnX, entity.getData(AlternateDimensionModVariables.PLAYER_VARIABLES).spawnY + 1,
						entity.getData(AlternateDimensionModVariables.PLAYER_VARIABLES).spawnZ), BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("block.portal.trigger")), SoundSource.PLAYERS, 1, 1);
			} else {
				_level.playLocalSound(entity.getData(AlternateDimensionModVariables.PLAYER_VARIABLES).spawnX, (entity.getData(AlternateDimensionModVariables.PLAYER_VARIABLES).spawnY + 1),
						entity.getData(AlternateDimensionModVariables.PLAYER_VARIABLES).spawnZ, BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("block.portal.trigger")), SoundSource.PLAYERS, 1, 1, false);
			}
		}
		world.setBlock(BlockPos.containing(entity.getData(AlternateDimensionModVariables.PLAYER_VARIABLES).spawnX, entity.getData(AlternateDimensionModVariables.PLAYER_VARIABLES).spawnY + 1,
				entity.getData(AlternateDimensionModVariables.PLAYER_VARIABLES).spawnZ), AlternateDimensionModBlocks.PORTAL_BLOCK.get().defaultBlockState(), 3);
		if (!world.isClientSide()) {
			BlockPos _bp = BlockPos.containing(entity.getData(AlternateDimensionModVariables.PLAYER_VARIABLES).spawnX, entity.getData(AlternateDimensionModVariables.PLAYER_VARIABLES).spawnY + 1,
					entity.getData(AlternateDimensionModVariables.PLAYER_VARIABLES).spawnZ);
			BlockEntity _blockEntity = world.getBlockEntity(_bp);
			BlockState _bs = world.getBlockState(_bp);
			if (_blockEntity != null) {
				_blockEntity.getPersistentData().putString("owner", (entity.getStringUUID()));
				_blockEntity.getPersistentData().putDouble("targetX", x);
				_blockEntity.getPersistentData().putDouble("targetY", y);
				_blockEntity.getPersistentData().putDouble("targetZ", z);
			}
			if (world instanceof Level _level)
				_level.sendBlockUpdated(_bp, _bs, _bs, 3);
		}
		{
			Entity _ent = entity;
			double _tx = entity.getData(AlternateDimensionModVariables.PLAYER_VARIABLES).spawnX;
			double _ty = (entity.getData(AlternateDimensionModVariables.PLAYER_VARIABLES).spawnY + 2);
			double _tz = entity.getData(AlternateDimensionModVariables.PLAYER_VARIABLES).spawnZ;
			_ent.teleportTo(_tx, _ty, _tz);
			if (_ent instanceof ServerPlayer _serverPlayer)
				_serverPlayer.connection.teleport(_tx, _ty, _tz, _ent.getYRot(), _ent.getXRot());
		}
	}
}