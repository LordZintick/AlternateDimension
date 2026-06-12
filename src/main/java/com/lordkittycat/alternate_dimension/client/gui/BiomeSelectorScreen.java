package com.lordkittycat.alternate_dimension.client.gui;

import net.neoforged.neoforge.network.PacketDistributor;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.Minecraft;

import com.mojang.blaze3d.systems.RenderSystem;

import com.lordkittycat.alternate_dimension.world.inventory.BiomeSelectorMenu;
import com.lordkittycat.alternate_dimension.network.BiomeSelectorButtonMessage;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModScreens;

public class BiomeSelectorScreen extends AbstractContainerScreen<BiomeSelectorMenu> implements AlternateDimensionModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private EditBox biome_input;
	private Button button_confirm;
	private static final ResourceLocation BACKGROUND = ResourceLocation.parse("alternate_dimension:textures/screens/biome_selector.png");

	public BiomeSelectorScreen(BiomeSelectorMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 138;
		this.imageHeight = 70;
	}

	@Override
	public void updateMenuState(int elementType, String name, Object elementState) {
		menuStateUpdateActive = true;
		if (elementType == 0 && elementState instanceof String stringState) {
			if (name.equals("biome_input"))
				biome_input.setValue(stringState);
		}
		menuStateUpdateActive = false;
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		biome_input.render(guiGraphics, mouseX, mouseY, partialTicks);
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
		if (biome_input.isFocused())
			return biome_input.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void resize(Minecraft minecraft, int width, int height) {
		String biome_inputValue = biome_input.getValue();
		super.resize(minecraft, width, height);
		biome_input.setValue(biome_inputValue);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.alternate_dimension.biome_selector.label_biome_locator"), 33, 5, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		biome_input = new EditBox(this.font, this.leftPos + 12, this.topPos + 19, 118, 18, Component.translatable("gui.alternate_dimension.biome_selector.biome_input"));
		biome_input.setMaxLength(8192);
		biome_input.setResponder(content -> {
			if (!menuStateUpdateActive)
				menu.sendMenuStateUpdate(entity, 0, "biome_input", content, false);
		});
		biome_input.setHint(Component.translatable("gui.alternate_dimension.biome_selector.biome_input"));
		this.addWidget(this.biome_input);
		button_confirm = Button.builder(Component.translatable("gui.alternate_dimension.biome_selector.button_confirm"), e -> {
			int x = BiomeSelectorScreen.this.x;
			int y = BiomeSelectorScreen.this.y;
			if (true) {
				PacketDistributor.sendToServer(new BiomeSelectorButtonMessage(0, x, y, z));
				BiomeSelectorButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 36, this.topPos + 43, 61, 20).build();
		this.addRenderableWidget(button_confirm);
	}
}