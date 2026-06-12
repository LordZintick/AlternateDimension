package com.lordkittycat.alternate_dimension.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.GuiGraphics;

import com.mojang.blaze3d.systems.RenderSystem;

import com.lordkittycat.alternate_dimension.world.inventory.CrystalChestGUIMenu;
import com.lordkittycat.alternate_dimension.procedures.*;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModScreens;

public class CrystalChestGUIScreen extends AbstractContainerScreen<CrystalChestGUIMenu> implements AlternateDimensionModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private static final ResourceLocation BACKGROUND = ResourceLocation.parse("alternate_dimension:textures/screens/crystal_chest_gui.png");
	private static final ResourceLocation IMAGE_0 = ResourceLocation.parse("alternate_dimension:textures/screens/slot_cover.png");
	private static final ResourceLocation IMAGE_1 = ResourceLocation.parse("alternate_dimension:textures/screens/slot_cover.png");
	private static final ResourceLocation IMAGE_2 = ResourceLocation.parse("alternate_dimension:textures/screens/slot_cover.png");
	private static final ResourceLocation IMAGE_3 = ResourceLocation.parse("alternate_dimension:textures/screens/slot_cover.png");
	private static final ResourceLocation IMAGE_4 = ResourceLocation.parse("alternate_dimension:textures/screens/slot_cover.png");
	private static final ResourceLocation IMAGE_5 = ResourceLocation.parse("alternate_dimension:textures/screens/slot_cover.png");

	public CrystalChestGUIScreen(CrystalChestGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 224;
		this.imageHeight = 224;
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
		if (CheckCapacity6Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(IMAGE_0, this.leftPos + 4, this.topPos + 14, 0, 0, 216, 18, 216, 18);
		}
		if (CheckCapacity5Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(IMAGE_1, this.leftPos + 4, this.topPos + 32, 0, 0, 216, 18, 216, 18);
		}
		if (CheckCapacity4Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(IMAGE_2, this.leftPos + 4, this.topPos + 50, 0, 0, 216, 18, 216, 18);
		}
		if (CheckCapacity3Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(IMAGE_3, this.leftPos + 4, this.topPos + 68, 0, 0, 216, 18, 216, 18);
		}
		if (CheckCapacity2Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(IMAGE_4, this.leftPos + 4, this.topPos + 86, 0, 0, 216, 18, 216, 18);
		}
		if (CheckCapacity1Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(IMAGE_5, this.leftPos + 4, this.topPos + 104, 0, 0, 216, 18, 216, 18);
		}
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
		guiGraphics.drawString(this.font, Component.translatable("gui.alternate_dimension.crystal_chest_gui.label_modules"), 175, 143, -12829636, true);
		guiGraphics.drawString(this.font, Component.translatable("gui.alternate_dimension.crystal_chest_gui.label_crystal_chest"), 71, 2, -6684673, true);
	}

	@Override
	public void init() {
		super.init();
	}
}