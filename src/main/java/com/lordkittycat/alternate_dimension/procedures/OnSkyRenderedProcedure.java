package com.lordkittycat.alternate_dimension.procedures;

import net.neoforged.neoforge.client.event.RenderLevelStageEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.resources.Identifier;
import net.minecraft.client.Minecraft;

import javax.annotation.Nullable;

import com.lordkittycat.alternate_dimension.network.AlternateDimensionModVariables;
import com.lordkittycat.alternate_dimension.client.RenderUtils;

@EventBusSubscriber(Dist.CLIENT)
public class OnSkyRenderedProcedure {
	@SubscribeEvent
	public static void onSkyRendered(RenderLevelStageEvent.AfterSky event) {
		Minecraft mc = Minecraft.getInstance();
		execute(event, mc.player.level(), event);
	}

	public static void execute(LevelAccessor world, RenderLevelStageEvent.AfterSky skyRenderEvent) {
		execute(null, world, skyRenderEvent);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, RenderLevelStageEvent.AfterSky skyRenderEvent) {
		if (skyRenderEvent == null)
			return;
		if (AlternateDimensionModVariables.WorldVariables.get(world).worldEventTime > 0) {
			if ((AlternateDimensionModVariables.WorldVariables.get(world).worldEventType).equals("blood_moon")) {
				RenderUtils.renderCustomMoon(skyRenderEvent, Identifier.parse("alternate_dimension:textures/blood_moon.png"));
			} else if ((AlternateDimensionModVariables.WorldVariables.get(world).worldEventType).equals("blue_moon")) {
				RenderUtils.renderCustomMoon(skyRenderEvent, Identifier.parse("alternate_dimension:textures/blue_moon.png"));
			}
		}
	}
}