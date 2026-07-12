package com.lordkittycat.alternate_dimension.client.gui;

import net.neoforged.neoforge.client.network.ClientPacketDistributor;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.Identifier;
import net.minecraft.network.chat.Component;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphicsExtractor;

import com.mojang.blaze3d.platform.InputConstants;

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
	private static final Identifier BACKGROUND = Identifier.parse("alternate_dimension:textures/screens/intro_screen.png");

	public IntroScreenScreen(IntroScreenMenu container, Inventory inventory, Component text) {
		super(container, inventory, text, 411, 222);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
	}

	@Override
	public void updateMenuState(int elementType, String name, Object elementState) {
		menuStateUpdateActive = true;
		menuStateUpdateActive = false;
	}

	@Override
	public void extractRenderState(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
		super.extractRenderState(guiGraphics, mouseX, mouseY, partialTicks);
	}

	@Override
	public void extractBackground(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
		super.extractBackground(guiGraphics, mouseX, mouseY, partialTicks);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, BACKGROUND, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
	}

	@Override
	public boolean keyPressed(KeyEvent event) {
		int key = InputConstants.getKey(event).getValue();
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		return super.keyPressed(event);
	}

	@Override
	protected void extractLabels(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY) {
		guiGraphics.text(this.font, Component.translatable("gui.alternate_dimension.intro_screen.label_welcome_to_alternate_dimension"), 131, 5, -12829636, false);
		guiGraphics.text(this.font, Component.translatable("gui.alternate_dimension.intro_screen.label_alternate_dimension_places_you_i"), 6, 28, -12829636, false);
		guiGraphics.text(this.font, Component.translatable("gui.alternate_dimension.intro_screen.label_mobs_interesting_features_and"), 6, 39, -12829636, false);
		guiGraphics.text(this.font, Component.translatable("gui.alternate_dimension.intro_screen.label_although_it_is_intended_to_start"), 6, 58, -12829636, false);
		guiGraphics.text(this.font, Component.translatable("gui.alternate_dimension.intro_screen.label_as_there_are_ways_to_access_the"), 6, 69, -12829636, false);
		guiGraphics.text(this.font, Component.translatable("gui.alternate_dimension.intro_screen.label_choose_to_start_in_the_overworld"), 6, 80, -12829636, false);
		guiGraphics.text(this.font, Component.translatable("gui.alternate_dimension.intro_screen.label_be_available_later_in_the_game_a"), 6, 91, -12829636, false);
		guiGraphics.text(this.font, Component.translatable("gui.alternate_dimension.intro_screen.label_so_now_you_choose_where_would"), 87, 154, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		button_alternate_dimension = Button.builder(Component.translatable("gui.alternate_dimension.intro_screen.button_alternate_dimension"), e -> {
			int x = IntroScreenScreen.this.x;
			int y = IntroScreenScreen.this.y;
			if (true) {
				ClientPacketDistributor.sendToServer(new IntroScreenButtonMessage(0, x, y, z));
				IntroScreenButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 100, this.topPos + 193, 120, 20).build();
		this.addRenderableWidget(button_alternate_dimension);
		button_vanilla = Button.builder(Component.translatable("gui.alternate_dimension.intro_screen.button_vanilla"), e -> {
			int x = IntroScreenScreen.this.x;
			int y = IntroScreenScreen.this.y;
			if (true) {
				ClientPacketDistributor.sendToServer(new IntroScreenButtonMessage(1, x, y, z));
				IntroScreenButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		}).bounds(this.leftPos + 240, this.topPos + 193, 60, 20).build();
		this.addRenderableWidget(button_vanilla);
	}
}