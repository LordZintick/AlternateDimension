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

import com.lordkittycat.alternate_dimension.world.inventory.CorruptorGUIMenu;
import com.lordkittycat.alternate_dimension.procedures.EnergyDisplayProcedure;
import com.lordkittycat.alternate_dimension.network.CorruptorGUIButtonMessage;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModScreens;

public class CorruptorGUIScreen extends AbstractContainerScreen<CorruptorGUIMenu> implements AlternateDimensionModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private Button button_press;
	private static final ResourceLocation BACKGROUND = ResourceLocation.parse("alternate_dimension:textures/screens/corruptor_gui.png");

	public CorruptorGUIScreen(CorruptorGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 160;
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
		guiGraphics.drawString(this.font, Component.translatable("gui.alternate_dimension.corruptor_gui.label_ingot_presser"), 65, 5, -8650752, false);
		guiGraphics.drawString(this.font, EnergyDisplayProcedure.execute(world, x, y, z), 6, 64, -16711799, false);
	}

	@Override
	public void init() {
		super.init();
		button_press = Button.builder(Component.translatable("gui.alternate_dimension.corruptor_gui.button_press"), e -> {
			int x = CorruptorGUIScreen.this.x;
			int y = CorruptorGUIScreen.this.y;
			if (true) {
				PacketDistributor.sendToServer(new CorruptorGUIButtonMessage(0, x, y, z));
				CorruptorGUIButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 100, this.topPos + 32, 56, 20).build();
		this.addRenderableWidget(button_press);
	}
}