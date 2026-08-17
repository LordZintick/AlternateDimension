package com.lordkittycat.alternate_dimension.client.toasts;

import org.jetbrains.annotations.NotNull;

import net.minecraft.world.level.entity.Visibility;
import net.minecraft.resources.Identifier;
import net.minecraft.network.chat.Component;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.gui.components.toasts.ToastManager;
import net.minecraft.client.gui.components.toasts.Toast;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.Font;

public class MoonEventEndToastToast implements Toast {
	private Toast.Visibility wantedVisibility = Toast.Visibility.HIDE;
	private static final Identifier BACKGROUND_SPRITE = Identifier.withDefaultNamespace("toast/recipe");
	private static final Identifier ICON_SPRITE = Identifier.parse("alternate_dimension:textures/screens/moon.png");
	private static final Component TITLE_TEXT = Component.translatable("toasts.alternate_dimension.moon_event_end_toast.title");
	private static final Component DESCRIPTION_TEXT = Component.translatable("toasts.alternate_dimension.moon_event_end_toast.description");
	private static final int DEFAULT_TIME = 5000;
	private final double displayTime;
	private final Identifier icon;

	public MoonEventEndToastToast() {
		this(DEFAULT_TIME, ICON_SPRITE);
	}

	public MoonEventEndToastToast(double time) {
		this(time, ICON_SPRITE);
	}

	public MoonEventEndToastToast(double displayTime, Identifier icon) {
		this.displayTime = displayTime;
		this.icon = icon;
	}

	@Override
	public @NotNull Visibility getWantedVisibility() {
		return wantedVisibility;
	}

	@Override
	public void update(@NotNull ToastManager toastManager, long lastChanged) {
		double totalMS = (double) displayTime * toastManager.getNotificationDisplayTimeMultiplier();
		this.wantedVisibility = (double) lastChanged <= totalMS ? Toast.Visibility.SHOW : Toast.Visibility.HIDE;
	}

	@Override
	public void extractRenderState(GuiGraphicsExtractor guiGraphics, @NotNull Font font, long l) {
		guiGraphics.blitSprite(RenderPipelines.GUI_TEXTURED, BACKGROUND_SPRITE, 0, 0, this.width(), this.height());
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, icon, 6, 6, 0, 0, 20, 20, 20, 20);
		guiGraphics.text(font, TITLE_TEXT, 30, 7, -11534256, false);
		guiGraphics.text(font, DESCRIPTION_TEXT, 30, 18, -16777216, false);
	}
}