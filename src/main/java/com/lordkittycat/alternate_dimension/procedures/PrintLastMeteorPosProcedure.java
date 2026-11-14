package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;

import com.lordkittycat.alternate_dimension.network.AlternateDimensionModVariables;

public class PrintLastMeteorPosProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof ServerPlayer _player)
			_player.sendSystemMessage(Component.literal(("The last meteor was spawned at X: " + AlternateDimensionModVariables.WorldVariables.get(world).lastMeteorPos.x() + " Y: "
					+ AlternateDimensionModVariables.WorldVariables.get(world).lastMeteorPos.y() + " Z: " + AlternateDimensionModVariables.WorldVariables.get(world).lastMeteorPos.z() + ".")), false);
	}
}