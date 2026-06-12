package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;

import com.lordkittycat.alternate_dimension.init.AlternateDimensionModBlocks;

public class WarpPlayerProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (world instanceof Level _level) {
			if (!_level.isClientSide()) {
				_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.enderman.teleport")), SoundSource.BLOCKS, 1, 1);
			} else {
				_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.enderman.teleport")), SoundSource.BLOCKS, 1, 1, false);
			}
		}
		if (world instanceof ServerLevel _level)
			_level.sendParticles(ParticleTypes.PORTAL, x, y, z, 200, 3, 3, 3, 1);
		{
			Entity _ent = entity;
			_ent.teleportTo((getBlockNBTNumber(world, BlockPos.containing(x, y, z), "targetX")), (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "targetY")), (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "targetZ")));
			if (_ent instanceof ServerPlayer _serverPlayer)
				_serverPlayer.connection.teleport((getBlockNBTNumber(world, BlockPos.containing(x, y, z), "targetX")), (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "targetY")),
						(getBlockNBTNumber(world, BlockPos.containing(x, y, z), "targetZ")), _ent.getYRot(), _ent.getXRot());
		}
		if (world instanceof Level _level) {
			if (!_level.isClientSide()) {
				_level.playSound(null,
						BlockPos.containing(getBlockNBTNumber(world, BlockPos.containing(x, y, z), "targetX"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "targetY"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "targetZ")),
						BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.enderman.teleport")), SoundSource.BLOCKS, 1, 1);
			} else {
				_level.playLocalSound((getBlockNBTNumber(world, BlockPos.containing(x, y, z), "targetX")), (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "targetY")), (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "targetZ")),
						BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.enderman.teleport")), SoundSource.BLOCKS, 1, 1, false);
			}
		}
		if (world instanceof ServerLevel _level)
			_level.sendParticles(ParticleTypes.PORTAL, (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "targetX")), (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "targetY")),
					(getBlockNBTNumber(world, BlockPos.containing(x, y, z), "targetZ")), 200, 3, 3, 3, 1);
		if (getBlockNBTLogic(world, BlockPos.containing(x, y, z), "oneUse")) {
			world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
		} else {
			world.setBlock(BlockPos.containing(getBlockNBTNumber(world, BlockPos.containing(x, y, z), "targetX"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "targetY"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "targetZ")),
					AlternateDimensionModBlocks.PORTAL_BLOCK.get().defaultBlockState(), 3);
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(getBlockNBTNumber(world, BlockPos.containing(x, y, z), "targetX"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "targetY"),
						getBlockNBTNumber(world, BlockPos.containing(x, y, z), "targetZ"));
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putBoolean("oneUse", true);
					_blockEntity.getPersistentData().putDouble("targetX", x);
					_blockEntity.getPersistentData().putDouble("targetY", (y + 1));
					_blockEntity.getPersistentData().putDouble("targetZ", z);
					_blockEntity.getPersistentData().putString("owner", (getBlockNBTString(world, BlockPos.containing(x, y, z), "owner")));
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
		}
	}

	private static double getBlockNBTNumber(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getDouble(tag);
		return -1;
	}

	private static boolean getBlockNBTLogic(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getBoolean(tag);
		return false;
	}

	private static String getBlockNBTString(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getString(tag);
		return "";
	}
}