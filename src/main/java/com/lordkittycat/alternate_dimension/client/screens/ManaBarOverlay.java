package com.lordkittycat.alternate_dimension.client.screens;

import net.neoforged.neoforge.client.event.RenderGuiEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.util.Mth;
import net.minecraft.resources.Identifier;
import net.minecraft.network.chat.Component;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.Minecraft;

import com.lordkittycat.alternate_dimension.procedures.GetManaBarStateProcedure;
import com.lordkittycat.alternate_dimension.procedures.CheckHoldingWandProcedure;

@EventBusSubscriber(Dist.CLIENT)
public class ManaBarOverlay {
	private static final Identifier SPRITE_0 = Identifier.parse("alternate_dimension:textures/screens/mana_bar.png");

	@SubscribeEvent(priority = EventPriority.NORMAL)
	public static void eventHandler(RenderGuiEvent.Pre event) {
		int w = event.getGuiGraphics().guiWidth();
		int h = event.getGuiGraphics().guiHeight();
		Level world = null;
		double x = 0;
		double y = 0;
		double z = 0;
		Player entity = Minecraft.getInstance().player;
		if (entity != null) {
			world = entity.level();
			x = entity.getX();
			y = entity.getY();
			z = entity.getZ();
		}
		if (CheckHoldingWandProcedure.execute(entity)) {

			event.getGuiGraphics().blit(RenderPipelines.GUI_TEXTURED, SPRITE_0, 4, 5, Mth.clamp((int) GetManaBarStateProcedure.execute(entity) * 11, 0, 191), 0, 11, 95, 202, 95);

			event.getGuiGraphics().text(Minecraft.getInstance().font, Component.translatable("gui.alternate_dimension.mana_bar.label_mana"), 17, 5, -9669147, true);
		}
	}
}