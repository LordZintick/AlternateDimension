package com.lordkittycat.alternate_dimension.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.GuiGraphics;

import com.mojang.blaze3d.systems.RenderSystem;

import com.lordkittycat.alternate_dimension.world.inventory.ElementalInscriberGUIMenu;
import com.lordkittycat.alternate_dimension.procedures.ProgressDisplayProcedure;
import com.lordkittycat.alternate_dimension.procedures.GenericBurnTimeDisplayProcedure;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModScreens;

public class ElementalInscriberGUIScreen extends AbstractContainerScreen<ElementalInscriberGUIMenu> implements AlternateDimensionModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private static final ResourceLocation BACKGROUND = ResourceLocation.parse("alternate_dimension:textures/screens/elemental_inscriber_gui.png");
	private static final ResourceLocation IMAGE_0 = ResourceLocation.parse("alternate_dimension:textures/screens/elemental_arrow.png");
	private static final ResourceLocation IMAGE_1 = ResourceLocation.parse("alternate_dimension:textures/screens/elementium_ingot_outline.png");

	public ElementalInscriberGUIScreen(ElementalInscriberGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 220;
		this.imageHeight = 215;
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
		guiGraphics.blit(IMAGE_0, this.leftPos + 37, this.topPos + 58, 0, 0, 146, 39, 146, 39);
		guiGraphics.blit(IMAGE_1, this.leftPos + 8, this.topPos + 104, 0, 0, 16, 16, 16, 16);
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
		guiGraphics.drawString(this.font, Component.translatable("gui.alternate_dimension.elemental_inscriber_gui.label_ele"), 57, 11, -65536, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.alternate_dimension.elemental_inscriber_gui.label_mental"), 79, 11, -16711936, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.alternate_dimension.elemental_inscriber_gui.label_insc"), 111, 11, -16711681, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.alternate_dimension.elemental_inscriber_gui.label_riber"), 133, 11, -65281, false);
		guiGraphics.drawString(this.font, ProgressDisplayProcedure.execute(world, x, y, z), 119, 87, -12829636, false);
		guiGraphics.drawString(this.font, GenericBurnTimeDisplayProcedure.execute(world, x, y, z), 102, 122, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.alternate_dimension.elemental_inscriber_gui.label_elem"), 8, 122, -65536, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.alternate_dimension.elemental_inscriber_gui.label_ental"), 30, 122, -16711936, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.alternate_dimension.elemental_inscriber_gui.label_ene"), 60, 122, -16711681, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.alternate_dimension.elemental_inscriber_gui.label_rgy"), 78, 122, -65281, false);
	}

	@Override
	public void init() {
		super.init();
	}
}