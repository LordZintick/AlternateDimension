package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import com.lordkittycat.alternate_dimension.network.AlternateDimensionModVariables;

public class PrintMeteorCooldownProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal(("Meteor Cooldown: " + AlternateDimensionModVariables.WorldVariables.get(world).meteorCooldown)), false);
	}
}