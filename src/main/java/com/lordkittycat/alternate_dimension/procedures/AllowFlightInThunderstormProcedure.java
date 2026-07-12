package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.GameType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

public class AllowFlightInThunderstormProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof Player _player) {
			_player.getAbilities().mayfly = (world instanceof Level _lvl0 && _lvl0.isThundering() || entity instanceof Player _plr1 && _plr1.gameMode() == GameType.CREATIVE || entity instanceof Player _plr2 && _plr2.gameMode() == GameType.SPECTATOR);
			_player.onUpdateAbilities();
		}
	}
}