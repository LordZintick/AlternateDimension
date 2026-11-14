package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;

import com.lordkittycat.alternate_dimension.network.AlternateDimensionModVariables;

public class DisplayEventTimeProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if ((AlternateDimensionModVariables.WorldVariables.get(world).worldEventType).equals("")) {
			if (entity instanceof ServerPlayer _player)
				_player.sendSystemMessage(Component.literal(("The next world event can happen in " + AlternateDimensionModVariables.WorldVariables.get(world).worldEventTime + " ticks.")), false);
		} else {
			if (entity instanceof ServerPlayer _player)
				_player.sendSystemMessage(Component.literal(("The current world event will last " + AlternateDimensionModVariables.WorldVariables.get(world).worldEventTime + " more ticks.")), false);
		}
	}
}