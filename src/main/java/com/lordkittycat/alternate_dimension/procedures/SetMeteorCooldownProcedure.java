package com.lordkittycat.alternate_dimension.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.commands.CommandSourceStack;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.arguments.DoubleArgumentType;

import com.lordkittycat.alternate_dimension.network.AlternateDimensionModVariables;

public class SetMeteorCooldownProcedure {
	public static void execute(LevelAccessor world, CommandContext<CommandSourceStack> arguments, Entity entity) {
		if (entity == null)
			return;
		double old = 0;
		old = AlternateDimensionModVariables.WorldVariables.get(world).meteorCooldown;
		AlternateDimensionModVariables.WorldVariables.get(world).meteorCooldown = DoubleArgumentType.getDouble(arguments, "newTime");
		AlternateDimensionModVariables.WorldVariables.get(world).markSyncDirty();
		if (entity instanceof ServerPlayer _player)
			_player.sendSystemMessage(Component.literal(("Changed meteor cooldown from " + old + " to " + AlternateDimensionModVariables.WorldVariables.get(world).meteorCooldown + ".")), false);
	}
}