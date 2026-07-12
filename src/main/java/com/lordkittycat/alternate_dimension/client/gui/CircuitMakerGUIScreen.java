package com.lordkittycat.alternate_dimension.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.Identifier;
import net.minecraft.network.chat.Component;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.GuiGraphicsExtractor;

import com.mojang.blaze3d.platform.InputConstants;

import com.lordkittycat.alternate_dimension.world.inventory.CircuitMakerGUIMenu;
import com.lordkittycat.alternate_dimension.procedures.ProgressDisplayProcedure;
import com.lordkittycat.alternate_dimension.procedures.EnergyDisplayProcedure;
import com.lordkittycat.alternate_dimension.procedures.CheckActiveProcedure;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModScreens;

public class CircuitMakerGUIScreen extends AbstractContainerScreen<CircuitMakerGUIMenu> implements AlternateDimensionModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private static final Identifier BACKGROUND = Identifier.parse("alternate_dimension:textures/screens/circuit_maker_gui.png");
	private static final Identifier IMAGE_0 = Identifier.parse("alternate_dimension:textures/screens/threeway_arrow.png");
	private static final Identifier IMAGE_1 = Identifier.parse("alternate_dimension:textures/screens/chip_outline.png");

	public CircuitMakerGUIScreen(CircuitMakerGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text, 176, 192);
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
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_0, this.leftPos + 56, this.topPos + 34, 0, 0, 48, 48, 48, 48);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_1, this.leftPos + 108, this.topPos + 51, 0, 0, 16, 16, 16, 16);
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
		guiGraphics.text(this.font, Component.translatable("gui.alternate_dimension.circuit_maker_gui.label_circuit_maker"), 50, 9, -12829636, false);
		guiGraphics.text(this.font, EnergyDisplayProcedure.execute(world, x, y, z), 6, 99, -16711799, false);
		if (CheckActiveProcedure.execute(world, x, y, z))
			guiGraphics.text(this.font, ProgressDisplayProcedure.execute(world, x, y, z), 5, 89, -16777216, false);
	}

	@Override
	public void init() {
		super.init();
	}
}