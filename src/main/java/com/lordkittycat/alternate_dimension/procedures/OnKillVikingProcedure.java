package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

import com.lordkittycat.alternate_dimension.network.AlternateDimensionModVariables;

public class OnKillVikingProcedure {
	public static void execute(Entity immediatesourceentity) {
		if (immediatesourceentity == null)
			return;
		if (immediatesourceentity instanceof Player) {
			{
				AlternateDimensionModVariables.PlayerVariables _vars = immediatesourceentity.getData(AlternateDimensionModVariables.PLAYER_VARIABLES);
				_vars.viking_reputation = Math.max(immediatesourceentity.getData(AlternateDimensionModVariables.PLAYER_VARIABLES).viking_reputation - 5, 0);
				_vars.roman_reputation = immediatesourceentity.getData(AlternateDimensionModVariables.PLAYER_VARIABLES).roman_reputation + 3;
				_vars.markSyncDirty();
			}
		}
	}
}