package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.permissions.LevelBasedPermissionSet;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.Identifier;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

import com.lordkittycat.alternate_dimension.network.AlternateDimensionModVariables;

public class LocateBiomeProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (!(entity.getData(AlternateDimensionModVariables.PLAYER_VARIABLES).selectedBiome).equals("")) {
			if ((entity.getData(AlternateDimensionModVariables.PLAYER_VARIABLES).selectedBiome).equals((entity.getData(AlternateDimensionModVariables.PLAYER_VARIABLES).selectedBiome).toLowerCase())
					&& (entity.getData(AlternateDimensionModVariables.PLAYER_VARIABLES).selectedBiome).equals(entity.getData(AlternateDimensionModVariables.PLAYER_VARIABLES).selectedBiome.replace(" ", "_"))) {
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("entity.experience_orb.pickup")), SoundSource.PLAYERS, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("entity.experience_orb.pickup")), SoundSource.PLAYERS, 1, 1, false);
					}
				}
				if (entity instanceof ServerPlayer _player)
					_player.sendSystemMessage(Component.literal(("Locating biome \"" + "" + entity.getData(AlternateDimensionModVariables.PLAYER_VARIABLES).selectedBiome + "\". Result: "
							+ executeCommandGetResult(entity, ("locate biome alternate_dimension:" + entity.getData(AlternateDimensionModVariables.PLAYER_VARIABLES).selectedBiome)))), false);
			} else {
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("block.lava.extinguish")), SoundSource.PLAYERS, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("block.lava.extinguish")), SoundSource.PLAYERS, 1, 1, false);
					}
				}
				if (entity instanceof ServerPlayer _player)
					_player.sendSystemMessage(Component.literal("Could not locate biome - Biome name must be all lowercase letters with spaces replaced by \"_\""), false);
			}
		} else {
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("block.lava.extinguish")), SoundSource.PLAYERS, 1, 1);
				} else {
					_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("block.lava.extinguish")), SoundSource.PLAYERS, 1, 1, false);
				}
			}
			if (entity instanceof ServerPlayer _player)
				_player.sendSystemMessage(Component.literal("Could not locate biome - No biome selected!"), false);
		}
	}

	private static String executeCommandGetResult(Entity entity, String command) {
		StringBuilder result = new StringBuilder();
		if (!entity.level().isClientSide() && entity.level().getServer() != null) {
			CommandSource dataConsumer = new CommandSource() {
				@Override
				public void sendSystemMessage(Component message) {
					result.append(message.getString());
				}

				@Override
				public boolean acceptsSuccess() {
					return true;
				}

				@Override
				public boolean acceptsFailure() {
					return true;
				}

				@Override
				public boolean shouldInformAdmins() {
					return false;
				}
			};
			entity.level().getServer().getCommands().performPrefixedCommand(new CommandSourceStack(dataConsumer, entity.position(), entity.getRotationVector(), entity.level() instanceof ServerLevel ? (ServerLevel) entity.level() : null,
					LevelBasedPermissionSet.OWNER, entity.getName().getString(), entity.getDisplayName(), entity.level().getServer(), entity), command);
		}
		return result.toString();
	}
}