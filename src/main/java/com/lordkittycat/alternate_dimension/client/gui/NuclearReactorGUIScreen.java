package com.lordkittycat.alternate_dimension.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.util.Mth;
import net.minecraft.resources.Identifier;
import net.minecraft.network.chat.Component;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.GuiGraphicsExtractor;

import com.mojang.blaze3d.platform.InputConstants;

import com.lordkittycat.alternate_dimension.world.inventory.NuclearReactorGUIMenu;
import com.lordkittycat.alternate_dimension.procedures.*;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModScreens;

public class NuclearReactorGUIScreen extends AbstractContainerScreen<NuclearReactorGUIMenu> implements AlternateDimensionModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private static final Identifier BACKGROUND = Identifier.parse("alternate_dimension:textures/screens/nuclear_reactor_gui.png");
	private static final Identifier SPRITE_0 = Identifier.parse("alternate_dimension:textures/screens/warning_icon.png");

	public NuclearReactorGUIScreen(NuclearReactorGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text, 230, 200);
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
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SPRITE_0, this.leftPos + 40, this.topPos + 18, 0, Mth.clamp((int) WarningIconSpriteProviderProcedure.execute(world, x, y, z) * 16, 0, 16), 16, 16, 16, 32);
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
		guiGraphics.text(this.font, Component.translatable("gui.alternate_dimension.nuclear_reactor_gui.label_nuclear_reactor"), 79, 6, -16724941, false);
		guiGraphics.text(this.font, Component.translatable("gui.alternate_dimension.nuclear_reactor_gui.label_fissile_fuel"), 5, 5, -12829636, false);
		guiGraphics.text(this.font, EnergyDisplayProcedure.execute(world, x, y, z), 5, 53, -16711783, false);
		guiGraphics.text(this.font, BurnTimeDisplayProcedure.execute(world, x, y, z), 5, 39, -16724941, false);
		guiGraphics.text(this.font, Component.translatable("gui.alternate_dimension.nuclear_reactor_gui.label_reaction_output"), 145, 105, -12829636, false);
		guiGraphics.text(this.font, Component.translatable("gui.alternate_dimension.nuclear_reactor_gui.label_reaction_input"), 151, 30, -12829636, false);
		if (CheckActiveProcedure.execute(world, x, y, z))
			guiGraphics.text(this.font, ProgressDisplayProcedure.execute(world, x, y, z), 120, 65, -16737997, false);
		guiGraphics.text(this.font, Component.translatable("gui.alternate_dimension.nuclear_reactor_gui.label_coolant"), 6, 75, -12829636, false);
		guiGraphics.text(this.font, HeatDisplayProcedure.execute(world, x, y, z), 6, 105, -6750208, false);
	}

	@Override
	public void init() {
		super.init();
	}
}