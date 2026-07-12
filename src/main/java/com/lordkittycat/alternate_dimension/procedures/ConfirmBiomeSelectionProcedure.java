package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

import com.lordkittycat.alternate_dimension.network.AlternateDimensionModVariables;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModMenus;

public class ConfirmBiomeSelectionProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (world instanceof Level _level) {
			if (!_level.isClientSide()) {
				_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("entity.experience_orb.pickup")), SoundSource.PLAYERS, 1, 1);
			} else {
				_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("entity.experience_orb.pickup")), SoundSource.PLAYERS, 1, 1, false);
			}
		}
		{
			AlternateDimensionModVariables.PlayerVariables _vars = entity.getData(AlternateDimensionModVariables.PLAYER_VARIABLES);
			_vars.selectedBiome = (entity instanceof Player _entity1 && _entity1.containerMenu instanceof AlternateDimensionModMenus.MenuAccessor _menu1) ? _menu1.getMenuState(0, "biome_input", "") : "";
			_vars.markSyncDirty();
		}
		if (entity instanceof Player _player)
			_player.closeContainer();
	}
}