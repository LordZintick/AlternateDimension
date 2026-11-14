package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Blocks;
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

public class WarpPlayerProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (world instanceof Level _level) {
			if (!_level.isClientSide()) {
				_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("entity.enderman.teleport")), SoundSource.BLOCKS, 1, 1);
			} else {
				_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("entity.enderman.teleport")), SoundSource.BLOCKS, 1, 1, false);
			}
		}
		if (world instanceof ServerLevel _level)
			_level.sendParticles(ParticleTypes.PORTAL, x, y, z, 200, 3, 3, 3, 1);
		{
			Entity _ent = entity;
			double _tx = (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "targetX"));
			double _ty = (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "targetY"));
			double _tz = (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "targetZ"));
			_ent.teleportTo(_tx, _ty, _tz);
			if (_ent instanceof ServerPlayer _serverPlayer)
				_serverPlayer.connection.teleport(_tx, _ty, _tz, _ent.getYRot(), _ent.getXRot());
		}
		if (world instanceof Level _level) {
			if (!_level.isClientSide()) {
				_level.playSound(null,
						BlockPos.containing(getBlockNBTNumber(world, BlockPos.containing(x, y, z), "targetX"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "targetY"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "targetZ")),
						BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("entity.enderman.teleport")), SoundSource.BLOCKS, 1, 1);
			} else {
				_level.playLocalSound((getBlockNBTNumber(world, BlockPos.containing(x, y, z), "targetX")), (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "targetY")), (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "targetZ")),
						BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("entity.enderman.teleport")), SoundSource.BLOCKS, 1, 1, false);
			}
		}
		if (world instanceof ServerLevel _level)
			_level.sendParticles(ParticleTypes.PORTAL, (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "targetX")), (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "targetY")),
					(getBlockNBTNumber(world, BlockPos.containing(x, y, z), "targetZ")), 200, 3, 3, 3, 1);
		if (getBlockNBTLogic(world, BlockPos.containing(x, y, z), "oneUse")) {
			world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
		} else {
			PlacePortalProcedure.execute(world, getBlockNBTNumber(world, BlockPos.containing(x, y, z), "targetX"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "targetY"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "targetZ"),
					entity, false, x, y, z);
		}
	}

	private static double getBlockNBTNumber(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getDoubleOr(tag, 0);
		return -1;
	}

	private static boolean getBlockNBTLogic(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getBooleanOr(tag, false);
		return false;
	}
}