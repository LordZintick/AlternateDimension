package com.lordkittycat.alternate_dimension.client.gui;

import net.neoforged.neoforge.client.network.ClientPacketDistributor;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.Identifier;
import net.minecraft.network.chat.Component;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphicsExtractor;

import com.mojang.blaze3d.platform.InputConstants;

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
	private static final Identifier BACKGROUND = Identifier.parse("alternate_dimension:textures/screens/biome_selector.png");

	public BiomeSelectorScreen(BiomeSelectorMenu container, Inventory inventory, Component text) {
		super(container, inventory, text, 138, 70);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
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
	public void extractRenderState(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
		super.extractRenderState(guiGraphics, mouseX, mouseY, partialTicks);
		biome_input.extractWidgetRenderState(guiGraphics, mouseX, mouseY, partialTicks);
	}

	@Override
	public void extractBackground(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
		super.extractBackground(guiGraphics, mouseX, mouseY, partialTicks);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, BACKGROUND, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
	}

	@Override
	public boolean keyPressed(KeyEvent event) {
		int key = InputConstants.getKey(event).getValue();
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		if (biome_input.isFocused())
			return biome_input.keyPressed(event);
		return super.keyPressed(event);
	}

	@Override
	public void resize(int width, int height) {
		String biome_inputValue = biome_input.getValue();
		super.resize(width, height);
		biome_input.setValue(biome_inputValue);
	}

	@Override
	protected void extractLabels(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY) {
		guiGraphics.text(this.font, Component.translatable("gui.alternate_dimension.biome_selector.label_biome_locator"), 33, 5, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		biome_input = new EditBox(this.font, this.leftPos + 11, this.topPos + 18, 120, 20, Component.translatable("gui.alternate_dimension.biome_selector.biome_input"));
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
				ClientPacketDistributor.sendToServer(new BiomeSelectorButtonMessage(0, x, y, z));
				BiomeSelectorButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 36, this.topPos + 43, 61, 20).build();
		this.addRenderableWidget(button_confirm);
	}
}