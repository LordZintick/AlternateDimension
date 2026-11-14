package com.lordkittycat.alternate_dimension.procedures;

import net.neoforged.neoforge.client.event.ViewportEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.util.Mth;
import net.minecraft.resources.Identifier;
import net.minecraft.core.BlockPos;
import net.minecraft.client.Minecraft;

import javax.annotation.Nullable;

import com.lordkittycat.alternate_dimension.network.AlternateDimensionModVariables;

@EventBusSubscriber(Dist.CLIENT)
public class OnFogRenderedProcedure {
	@SubscribeEvent
	public static void onSkyRendered(ViewportEvent.RenderFog event) {
		Minecraft mc = Minecraft.getInstance();
		execute(event, mc.player.level(), mc.player.getX(), mc.player.getY(), mc.player.getZ(), event);
	}

	public static void execute(LevelAccessor world, double x, double y, double z, ViewportEvent.RenderFog fogRenderEvent) {
		execute(null, world, x, y, z, fogRenderEvent);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, ViewportEvent.RenderFog fogRenderEvent) {
		if (fogRenderEvent == null)
			return;
		if (AlternateDimensionModVariables.WorldVariables.get(world).worldEventTime > 0) {
			if ((AlternateDimensionModVariables.WorldVariables.get(world).worldEventType).equals("blood_moon")) {
				fogColor = 0xff0000;
				fogRenderEvent.setNearPlaneDistance(35);
				fogRenderEvent.setFarPlaneDistance(75);
			} else if ((AlternateDimensionModVariables.WorldVariables.get(world).worldEventType).equals("blue_moon")) {
				fogColor = 0x000bff;
				fogRenderEvent.setNearPlaneDistance(55);
				fogRenderEvent.setFarPlaneDistance(95);
			}
		} else {
			if (world.getBiome(BlockPos.containing(x, y, z)).is(Identifier.parse("alternate_dimension:wasteland"))) {
				fogColor = 0xc6ae84;
				fogRenderEvent.setNearPlaneDistance(48);
				fogRenderEvent.setFarPlaneDistance(96);
			}
		}
	}

	private static int fogColor = -1;

	@SubscribeEvent
	public static void setFogColor(ViewportEvent.ComputeFogColor event) {
		if (fogColor != -1) {
			event.setRed(Mth.clamp(Mth.lerp(0.5F, Mth.clamp(event.getRed(), 0.0F, 1.0F), (fogColor >> 16 & 255) / 255.0F), 0.0F, 1.0F));
			event.setGreen(Mth.clamp(Mth.lerp(0.5F, Mth.clamp(event.getGreen(), 0.0F, 1.0F), (fogColor >> 8 & 255) / 255.0F), 0.0F, 1.0F));
			event.setBlue(Mth.clamp(Mth.lerp(0.5F, Mth.clamp(event.getBlue(), 0.0F, 1.0F), (fogColor & 255) / 255.0F), 0.0F, 1.0F));
			fogColor = -1;
		}
	}
}