package com.lordkittycat.alternate_dimension.procedures;

import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.event.tick.LevelTickEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;

import javax.annotation.Nullable;

import com.lordkittycat.alternate_dimension.network.EventPacketMessage;
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
				if (AlternateDimensionModVariables.WorldVariables.get(world).worldEventType.contains("moon")) {
					if (!world.isClientSide())
						PacketDistributor.sendToAllPlayers(new EventPacketMessage("end_moon_event"));
				}
				AlternateDimensionModVariables.WorldVariables.get(world).worldEventType = "";
				AlternateDimensionModVariables.WorldVariables.get(world).markSyncDirty();
			}
		} else {
			eventrand = Mth.nextInt(RandomSource.create(), 0, 5);
			if ((world instanceof Level _level2 ? _level2.getDefaultClockTime() : 0) == 0) {
				AlternateDimensionModVariables.WorldVariables.get(world).worldEventTime = 12000;
				AlternateDimensionModVariables.WorldVariables.get(world).markSyncDirty();
				// TODO: Implement day events
				AlternateDimensionModVariables.WorldVariables.get(world).worldEventType = "";
				AlternateDimensionModVariables.WorldVariables.get(world).markSyncDirty();
			} else if ((world instanceof Level _level3 ? _level3.getDefaultClockTime() : 0) == 12000) {
				AlternateDimensionModVariables.WorldVariables.get(world).worldEventTime = 12000;
				AlternateDimensionModVariables.WorldVariables.get(world).markSyncDirty();
				if (eventrand == 0) {
					AlternateDimensionModVariables.WorldVariables.get(world).worldEventType = "blood_moon";
					AlternateDimensionModVariables.WorldVariables.get(world).markSyncDirty();
					if (!world.isClientSide())
						PacketDistributor.sendToAllPlayers(new EventPacketMessage("start_blood_moon"));
				} else if (eventrand == 1) {
					AlternateDimensionModVariables.WorldVariables.get(world).worldEventType = "blue_moon";
					AlternateDimensionModVariables.WorldVariables.get(world).markSyncDirty();
					if (!world.isClientSide())
						PacketDistributor.sendToAllPlayers(new EventPacketMessage("start_blue_moon"));
				} else {
					AlternateDimensionModVariables.WorldVariables.get(world).worldEventType = "";
					AlternateDimensionModVariables.WorldVariables.get(world).markSyncDirty();
				}
			}
		}
	}
}