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

import com.lordkittycat.alternate_dimension.world.inventory.IncineratorGUIMenu;
import com.lordkittycat.alternate_dimension.network.IncineratorGUIButtonMessage;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModScreens;

public class IncineratorGUIScreen extends AbstractContainerScreen<IncineratorGUIMenu> implements AlternateDimensionModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private Button button_incinerate;
	private static final ResourceLocation BACKGROUND = ResourceLocation.parse("alternate_dimension:textures/screens/incinerator_gui.png");

	public IncineratorGUIScreen(IncineratorGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 166;
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
		guiGraphics.drawString(this.font, Component.translatable("gui.alternate_dimension.incinerator_gui.label_item_incinerator"), 48, 7, -65536, false);
	}

	@Override
	public void init() {
		super.init();
		button_incinerate = Button.builder(Component.translatable("gui.alternate_dimension.incinerator_gui.button_incinerate"), e -> {
			int x = IncineratorGUIScreen.this.x;
			int y = IncineratorGUIScreen.this.y;
			if (true) {
				PacketDistributor.sendToServer(new IncineratorGUIButtonMessage(0, x, y, z));
				IncineratorGUIButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 50, this.topPos + 60, 77, 20).build();
		this.addRenderableWidget(button_incinerate);
	}
}