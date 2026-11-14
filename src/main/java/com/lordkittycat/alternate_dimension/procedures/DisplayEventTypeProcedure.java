package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;

import com.lordkittycat.alternate_dimension.network.AlternateDimensionModVariables;

public class DisplayEventTypeProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if ((AlternateDimensionModVariables.WorldVariables.get(world).worldEventType).equals("")) {
			if (entity instanceof ServerPlayer _player)
				_player.sendSystemMessage(Component.literal("There is no current world event happening."), false);
		} else {
			if (entity instanceof ServerPlayer _player)
				_player.sendSystemMessage(Component.literal(("The current world event is " + AlternateDimensionModVariables.WorldVariables.get(world).worldEventType)), false);
		}
	}
}