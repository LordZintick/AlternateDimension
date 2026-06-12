package com.lordkittycat.alternate_dimension.client.gui;

import net.neoforged.neoforge.network.PacketDistributor;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;

import com.mojang.blaze3d.systems.RenderSystem;

import com.lordkittycat.alternate_dimension.world.inventory.IntroScreenMenu;
import com.lordkittycat.alternate_dimension.network.IntroScreenButtonMessage;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModScreens;

public class IntroScreenScreen extends AbstractContainerScreen<IntroScreenMenu> implements AlternateDimensionModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private Button button_alternate_dimension;
	private Button button_vanilla;
	private static final ResourceLocation BACKGROUND = ResourceLocation.parse("alternate_dimension:textures/screens/intro_screen.png");

	public IntroScreenScreen(IntroScreenMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 411;
		this.imageHeight = 222;
	}

	@Override
	public void updateMenuState(int elementType, String name, Object elementState) {
		menuStateUpdateActive = true;
		menuStateUpdateActive = false;
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(BACKGROUND, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		return super.keyPressed(key, b, c);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.alternate_dimension.intro_screen.label_welcome_to_alternate_dimension"), 131, 5, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.alternate_dimension.intro_screen.label_alternate_dimension_places_you_i"), 6, 28, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.alternate_dimension.intro_screen.label_mobs_interesting_features_and"), 6, 39, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.alternate_dimension.intro_screen.label_although_it_is_intended_to_start"), 6, 58, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.alternate_dimension.intro_screen.label_as_there_are_ways_to_access_the"), 6, 69, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.alternate_dimension.intro_screen.label_choose_to_start_in_the_overworld"), 6, 80, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.alternate_dimension.intro_screen.label_be_available_later_in_the_game_a"), 6, 91, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.alternate_dimension.intro_screen.label_so_now_you_choose_where_would"), 87, 154, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		button_alternate_dimension = Button.builder(Component.translatable("gui.alternate_dimension.intro_screen.button_alternate_dimension"), e -> {
			int x = IntroScreenScreen.this.x;
			int y = IntroScreenScreen.this.y;
			if (true) {
				PacketDistributor.sendToServer(new IntroScreenButtonMessage(0, x, y, z));
				IntroScreenButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 100, this.topPos + 193, 120, 20).build();
		this.addRenderableWidget(button_alternate_dimension);
		button_vanilla = Button.builder(Component.translatable("gui.alternate_dimension.intro_screen.button_vanilla"), e -> {
			int x = IntroScreenScreen.this.x;
			int y = IntroScreenScreen.this.y;
			if (true) {
				PacketDistributor.sendToServer(new IntroScreenButtonMessage(1, x, y, z));
				IntroScreenButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		}).bounds(this.leftPos + 240, this.topPos + 193, 60, 20).build();
		this.addRenderableWidget(button_vanilla);
	}
}