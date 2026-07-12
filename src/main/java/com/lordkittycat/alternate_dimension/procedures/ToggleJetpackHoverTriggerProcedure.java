package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

import com.lordkittycat.alternate_dimension.network.AlternateDimensionModVariables;

public class ToggleJetpackHoverTriggerProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (world instanceof Level _level) {
			if (!_level.isClientSide()) {
				_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("block.end_portal_frame.fill")), SoundSource.PLAYERS, 1, 1);
			} else {
				_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("block.end_portal_frame.fill")), SoundSource.PLAYERS, 1, 1, false);
			}
		}
		{
			AlternateDimensionModVariables.PlayerVariables _vars = entity.getData(AlternateDimensionModVariables.PLAYER_VARIABLES);
			_vars.jetpackHover = !entity.getData(AlternateDimensionModVariables.PLAYER_VARIABLES).jetpackHover;
			_vars.markSyncDirty();
		}
	}
}