package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.commands.CommandSourceStack;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.arguments.DoubleArgumentType;

import com.lordkittycat.alternate_dimension.network.AlternateDimensionModVariables;

public class StartEventProcedure {
	public static void execute(LevelAccessor world, CommandContext<CommandSourceStack> arguments, Entity entity) {
		if (entity == null)
			return;
		AlternateDimensionModVariables.WorldVariables.get(world).worldEventTime = DoubleArgumentType.getDouble(arguments, "time");
		AlternateDimensionModVariables.WorldVariables.get(world).worldEventType = StringArgumentType.getString(arguments, "type");
		AlternateDimensionModVariables.WorldVariables.get(world).markSyncDirty();
		if (entity instanceof ServerPlayer _player)
			_player.sendSystemMessage(Component.literal(("Starting event \"" + "" + StringArgumentType.getString(arguments, "type") + "\" for " + DoubleArgumentType.getDouble(arguments, "time") + " ticks.")), false);
	}
}