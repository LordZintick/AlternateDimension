package com.lordkittycat.alternate_dimension.client.gui;

import net.neoforged.neoforge.client.network.ClientPacketDistributor;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.util.Mth;
import net.minecraft.resources.Identifier;
import net.minecraft.network.chat.Component;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphicsExtractor;

import com.mojang.blaze3d.platform.InputConstants;

import com.lordkittycat.alternate_dimension.world.inventory.IngotPresserGUIMenu;
import com.lordkittycat.alternate_dimension.procedures.PistonSpriteHandlerProcedure;
import com.lordkittycat.alternate_dimension.procedures.EnergyDisplayProcedure;
import com.lordkittycat.alternate_dimension.network.IngotPresserGUIButtonMessage;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModScreens;

public class IngotPresserGUIScreen extends AbstractContainerScreen<IngotPresserGUIMenu> implements AlternateDimensionModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private Button button_press;
	private static final Identifier BACKGROUND = Identifier.parse("alternate_dimension:textures/screens/ingot_presser_gui.png");
	private static final Identifier SPRITE_0 = Identifier.parse("alternate_dimension:textures/screens/gui_piston_down.png");
	private static final Identifier SPRITE_1 = Identifier.parse("alternate_dimension:textures/screens/gui_piston_up.png");

	public IngotPresserGUIScreen(IngotPresserGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text, 176, 185);
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
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SPRITE_0, this.leftPos + 80, this.topPos + 27, 0, Mth.clamp((int) PistonSpriteHandlerProcedure.execute(world, x, y, z) * 27, 0, 27), 16, 27, 16, 54);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SPRITE_1, this.leftPos + 80, this.topPos + 54, 0, Mth.clamp((int) PistonSpriteHandlerProcedure.execute(world, x, y, z) * 27, 0, 27), 16, 27, 16, 54);
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
		guiGraphics.text(this.font, Component.translatable("gui.alternate_dimension.ingot_presser_gui.label_ingot_presser"), 51, 5, -12829636, false);
		guiGraphics.text(this.font, EnergyDisplayProcedure.execute(world, x, y, z), 6, 88, -16711799, false);
	}

	@Override
	public void init() {
		super.init();
		button_press = Button.builder(Component.translatable("gui.alternate_dimension.ingot_presser_gui.button_press"), e -> {
			int x = IngotPresserGUIScreen.this.x;
			int y = IngotPresserGUIScreen.this.y;
			if (true) {
				ClientPacketDistributor.sendToServer(new IngotPresserGUIButtonMessage(0, x, y, z));
				IngotPresserGUIButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 100, this.topPos + 45, 56, 20).build();
		this.addRenderableWidget(button_press);
	}
}