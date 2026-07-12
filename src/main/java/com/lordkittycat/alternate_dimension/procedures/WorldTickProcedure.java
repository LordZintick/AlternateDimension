package com.lordkittycat.alternate_dimension.procedures;

import net.neoforged.neoforge.event.tick.LevelTickEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;

import javax.annotation.Nullable;

import com.lordkittycat.alternate_dimension.network.AlternateDimensionModVariables;

@EventBusSubscriber
public class WorldTickProcedure {
	@SubscribeEvent
	public static void onWorldTick(LevelTickEvent.Post event) {
		execute(event, event.getLevel());
	}

	public static void execute(LevelAccessor world) {
		execute(null, world);
	}

	private static void execute(@Nullable Event event, LevelAccessor world) {
		double eventrand = 0;
		if (AlternateDimensionModVariables.WorldVariables.get(world).meteorCooldown > 0) {
			AlternateDimensionModVariables.WorldVariables.get(world).meteorCooldown = AlternateDimensionModVariables.WorldVariables.get(world).meteorCooldown - 1;
			AlternateDimensionModVariables.WorldVariables.get(world).markSyncDirty();
		}
		if (AlternateDimensionModVariables.WorldVariables.get(world).worldEventTime > 0) {
			AlternateDimensionModVariables.WorldVariables.get(world).worldEventTime = AlternateDimensionModVariables.WorldVariables.get(world).worldEventTime - 1;
			AlternateDimensionModVariables.WorldVariables.get(world).markSyncDirty();
			if (AlternateDimensionModVariables.WorldVariables.get(world).worldEventTime <= 0) {
				if ((AlternateDimensionModVariables.WorldVariables.get(world).worldEventType).equals("blood_moon")) {
					if (world instanceof ServerLevel _level) {
						_level.getServer().getPlayerList().broadcastSystemMessage(Component.literal("The moon has returned to normal...").withStyle(ChatFormatting.BOLD), false);
					}
				}
				AlternateDimensionModVariables.WorldVariables.get(world).worldEventType = "";
				AlternateDimensionModVariables.WorldVariables.get(world).markSyncDirty();
			}
		} else {
			eventrand = Mth.nextInt(RandomSource.create(), 0, 5);
			if (world.getGameTime() == 0) {
				// TODO: Implement day events
				AlternateDimensionModVariables.WorldVariables.get(world).worldEventType = "";
				AlternateDimensionModVariables.WorldVariables.get(world).worldEventTime = 12000;
				AlternateDimensionModVariables.WorldVariables.get(world).markSyncDirty();
			} else if (world.getGameTime() == 12000) {
				if (eventrand == 0) {
					AlternateDimensionModVariables.WorldVariables.get(world).worldEventType = "blood_moon";
					AlternateDimensionModVariables.WorldVariables.get(world).markSyncDirty();
					if (world instanceof ServerLevel _level) {
						_level.getServer().getPlayerList().broadcastSystemMessage(Component.literal("The blood moon is rising...").withColor(0xff0000).withStyle(ChatFormatting.BOLD), false);
					}
				} else {
					AlternateDimensionModVariables.WorldVariables.get(world).worldEventType = "";
					AlternateDimensionModVariables.WorldVariables.get(world).markSyncDirty();
				}
				AlternateDimensionModVariables.WorldVariables.get(world).worldEventTime = 12000;
				AlternateDimensionModVariables.WorldVariables.get(world).markSyncDirty();
			}
		}
	}
}