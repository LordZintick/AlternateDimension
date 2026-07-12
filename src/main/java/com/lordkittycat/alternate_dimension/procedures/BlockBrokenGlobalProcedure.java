package com.lordkittycat.alternate_dimension.procedures;

import net.neoforged.neoforge.event.level.block.BreakBlockEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import javax.annotation.Nullable;

@EventBusSubscriber
public class BlockBrokenGlobalProcedure {
	@SubscribeEvent
	public static void onBlockBreak(BreakBlockEvent event) {
		execute(event);
	}

	public static void execute() {
		execute(null);
	}

	private static void execute(@Nullable Event event) {
		double dx = 0;
		double dy = 0;
		double dz = 0;
	}
}