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

import com.lordkittycat.alternate_dimension.world.inventory.ElementalInscriberGUIMenu;
import com.lordkittycat.alternate_dimension.procedures.ProgressDisplayProcedure;
import com.lordkittycat.alternate_dimension.procedures.GenericBurnTimeDisplayProcedure;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModScreens;

public class ElementalInscriberGUIScreen extends AbstractContainerScreen<ElementalInscriberGUIMenu> implements AlternateDimensionModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private static final Identifier BACKGROUND = Identifier.parse("alternate_dimension:textures/screens/elemental_inscriber_gui.png");
	private static final Identifier IMAGE_0 = Identifier.parse("alternate_dimension:textures/screens/elemental_arrow.png");
	private static final Identifier IMAGE_1 = Identifier.parse("alternate_dimension:textures/screens/elementium_ingot_outline.png");

	public ElementalInscriberGUIScreen(ElementalInscriberGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text, 220, 215);
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
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_0, this.leftPos + 37, this.topPos + 58, 0, 0, 146, 39, 146, 39);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_1, this.leftPos + 8, this.topPos + 104, 0, 0, 16, 16, 16, 16);
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
		guiGraphics.text(this.font, Component.translatable("gui.alternate_dimension.elemental_inscriber_gui.label_ele"), 57, 11, -65536, false);
		guiGraphics.text(this.font, Component.translatable("gui.alternate_dimension.elemental_inscriber_gui.label_mental"), 79, 11, -16711936, false);
		guiGraphics.text(this.font, Component.translatable("gui.alternate_dimension.elemental_inscriber_gui.label_insc"), 111, 11, -16711681, false);
		guiGraphics.text(this.font, Component.translatable("gui.alternate_dimension.elemental_inscriber_gui.label_riber"), 133, 11, -65281, false);
		guiGraphics.text(this.font, ProgressDisplayProcedure.execute(world, x, y, z), 119, 87, -12829636, false);
		guiGraphics.text(this.font, GenericBurnTimeDisplayProcedure.execute(world, x, y, z), 102, 122, -12829636, false);
		guiGraphics.text(this.font, Component.translatable("gui.alternate_dimension.elemental_inscriber_gui.label_elem"), 8, 122, -65536, false);
		guiGraphics.text(this.font, Component.translatable("gui.alternate_dimension.elemental_inscriber_gui.label_ental"), 30, 122, -16711936, false);
		guiGraphics.text(this.font, Component.translatable("gui.alternate_dimension.elemental_inscriber_gui.label_ene"), 60, 122, -16711681, false);
		guiGraphics.text(this.font, Component.translatable("gui.alternate_dimension.elemental_inscriber_gui.label_rgy"), 78, 122, -65281, false);
	}

	@Override
	public void init() {
		super.init();
	}
}