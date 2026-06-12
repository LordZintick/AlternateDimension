package com.lordkittycat.alternate_dimension.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.util.Mth;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.GuiGraphics;

import com.mojang.blaze3d.systems.RenderSystem;

import com.lordkittycat.alternate_dimension.world.inventory.NuclearReactorGUIMenu;
import com.lordkittycat.alternate_dimension.procedures.*;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModScreens;

public class NuclearReactorGUIScreen extends AbstractContainerScreen<NuclearReactorGUIMenu> implements AlternateDimensionModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private static final ResourceLocation BACKGROUND = ResourceLocation.parse("alternate_dimension:textures/screens/nuclear_reactor_gui.png");
	private static final ResourceLocation SPRITE_0 = ResourceLocation.parse("alternate_dimension:textures/screens/warning_icon.png");

	public NuclearReactorGUIScreen(NuclearReactorGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 230;
		this.imageHeight = 200;
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
		guiGraphics.blit(SPRITE_0, this.leftPos + 40, this.topPos + 18, 0, Mth.clamp((int) WarningIconSpriteProviderProcedure.execute(world, x, y, z) * 16, 0, 16), 16, 16, 16, 32);
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
		guiGraphics.drawString(this.font, Component.translatable("gui.alternate_dimension.nuclear_reactor_gui.label_nuclear_reactor"), 79, 6, -16724941, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.alternate_dimension.nuclear_reactor_gui.label_fissile_fuel"), 5, 5, -12829636, false);
		guiGraphics.drawString(this.font, EnergyDisplayProcedure.execute(world, x, y, z), 5, 53, -16711783, false);
		guiGraphics.drawString(this.font, BurnTimeDisplayProcedure.execute(world, x, y, z), 5, 39, -16724941, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.alternate_dimension.nuclear_reactor_gui.label_reaction_output"), 145, 105, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.alternate_dimension.nuclear_reactor_gui.label_reaction_input"), 151, 30, -12829636, false);
		if (CheckActiveProcedure.execute(world, x, y, z))
			guiGraphics.drawString(this.font, ProgressDisplayProcedure.execute(world, x, y, z), 120, 65, -16737997, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.alternate_dimension.nuclear_reactor_gui.label_coolant"), 6, 75, -12829636, false);
		guiGraphics.drawString(this.font, HeatDisplayProcedure.execute(world, x, y, z), 6, 105, -6750208, false);
	}

	@Override
	public void init() {
		super.init();
	}
}