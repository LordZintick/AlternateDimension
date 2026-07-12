package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;

import com.lordkittycat.alternate_dimension.network.AlternateDimensionModVariables;

public class PrintMeteorCooldownProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof ServerPlayer _player)
			_player.sendSystemMessage(Component.literal(("Meteor Cooldown: " + AlternateDimensionModVariables.WorldVariables.get(world).meteorCooldown)), false);
	}
}