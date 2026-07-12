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

import com.lordkittycat.alternate_dimension.world.inventory.CrystalChestGUIMenu;
import com.lordkittycat.alternate_dimension.procedures.*;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModScreens;

public class CrystalChestGUIScreen extends AbstractContainerScreen<CrystalChestGUIMenu> implements AlternateDimensionModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private static final Identifier BACKGROUND = Identifier.parse("alternate_dimension:textures/screens/crystal_chest_gui.png");
	private static final Identifier IMAGE_0 = Identifier.parse("alternate_dimension:textures/screens/slot_cover.png");
	private static final Identifier IMAGE_1 = Identifier.parse("alternate_dimension:textures/screens/slot_cover.png");
	private static final Identifier IMAGE_2 = Identifier.parse("alternate_dimension:textures/screens/slot_cover.png");
	private static final Identifier IMAGE_3 = Identifier.parse("alternate_dimension:textures/screens/slot_cover.png");
	private static final Identifier IMAGE_4 = Identifier.parse("alternate_dimension:textures/screens/slot_cover.png");
	private static final Identifier IMAGE_5 = Identifier.parse("alternate_dimension:textures/screens/slot_cover.png");

	public CrystalChestGUIScreen(CrystalChestGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text, 224, 224);
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
		if (CheckCapacity6Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_0, this.leftPos + 4, this.topPos + 14, 0, 0, 216, 18, 216, 18);
		}
		if (CheckCapacity5Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_1, this.leftPos + 4, this.topPos + 32, 0, 0, 216, 18, 216, 18);
		}
		if (CheckCapacity4Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_2, this.leftPos + 4, this.topPos + 50, 0, 0, 216, 18, 216, 18);
		}
		if (CheckCapacity3Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_3, this.leftPos + 4, this.topPos + 68, 0, 0, 216, 18, 216, 18);
		}
		if (CheckCapacity2Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_4, this.leftPos + 4, this.topPos + 86, 0, 0, 216, 18, 216, 18);
		}
		if (CheckCapacity1Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_5, this.leftPos + 4, this.topPos + 104, 0, 0, 216, 18, 216, 18);
		}
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
		guiGraphics.text(this.font, Component.translatable("gui.alternate_dimension.crystal_chest_gui.label_modules"), 175, 143, -12829636, true);
		guiGraphics.text(this.font, Component.translatable("gui.alternate_dimension.crystal_chest_gui.label_crystal_chest"), 71, 2, -6684673, true);
	}

	@Override
	public void init() {
		super.init();
	}
}