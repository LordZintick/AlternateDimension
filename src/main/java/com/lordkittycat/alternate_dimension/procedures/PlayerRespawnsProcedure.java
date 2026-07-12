package com.lordkittycat.alternate_dimension.procedures;

import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.permissions.LevelBasedPermissionSet;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

import javax.annotation.Nullable;

import com.lordkittycat.alternate_dimension.AlternateDimensionMod;

@EventBusSubscriber
public class PlayerRespawnsProcedure {
	@SubscribeEvent
	public static void onPlayerRespawned(PlayerEvent.PlayerRespawnEvent event) {
		execute(event, event.getEntity().level(), event.getEntity());
	}

	public static void execute(LevelAccessor world, Entity entity) {
		execute(null, world, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		AlternateDimensionMod.queueServerWork(1, () -> {
			if ((entity.level().dimension()) == Level.OVERWORLD) {
				AlternateDimensionMod.queueServerWork(1, () -> {
					SwitchToCurrentDimensionProcedure.execute(entity);
					AlternateDimensionMod.queueServerWork(20, () -> {
						{
							Entity _ent = entity;
							if (!_ent.level().isClientSide() && _ent.level().getServer() != null) {
								_ent.level().getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, _ent.position(), _ent.getRotationVector(),
										_ent.level() instanceof ServerLevel ? (ServerLevel) _ent.level() : null, LevelBasedPermissionSet.OWNER, _ent.getName().getString(), _ent.getDisplayName(), _ent.level().getServer(), _ent), "stopsound @s music");
							}
						}
					});
				});
			}
		});
	}
}