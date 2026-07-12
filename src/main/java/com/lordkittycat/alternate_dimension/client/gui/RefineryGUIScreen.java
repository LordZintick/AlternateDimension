package com.lordkittycat.alternate_dimension.client.gui;

import net.neoforged.neoforge.client.gui.widget.ExtendedSlider;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.Identifier;
import net.minecraft.network.chat.Component;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.Checkbox;
import net.minecraft.client.gui.GuiGraphicsExtractor;

import com.mojang.blaze3d.platform.InputConstants;

import com.lordkittycat.alternate_dimension.world.inventory.RefineryGUIMenu;
import com.lordkittycat.alternate_dimension.procedures.ProgressDisplayProcedure;
import com.lordkittycat.alternate_dimension.procedures.EnergyDisplayProcedure;
import com.lordkittycat.alternate_dimension.init.AlternateDimensionModScreens;

public class RefineryGUIScreen extends AbstractContainerScreen<RefineryGUIMenu> implements AlternateDimensionModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private Checkbox onoff_switch;
	private ExtendedSlider power;
	private static final Identifier BACKGROUND = Identifier.parse("alternate_dimension:textures/screens/refinery_gui.png");
	private static final Identifier IMAGE_0 = Identifier.parse("alternate_dimension:textures/screens/red_right_arrow.png");

	public RefineryGUIScreen(RefineryGUIMenu container, Inventory inventory, Component text) {
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
		if (elementType == 1 && elementState instanceof Boolean logicState) {
			if (name.equals("onoff_switch")) {
				if (onoff_switch.selected() != logicState)
					onoff_switch.onPress(null);
			}
		}
		if (elementType == 2 && elementState instanceof Number n) {
			if (name.equals("power"))
				power.setValue(n.doubleValue());
		}
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
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_0, this.leftPos + 65, this.topPos + 20, 0, 0, 16, 16, 16, 16);
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
		guiGraphics.text(this.font, Component.translatable("gui.alternate_dimension.refinery_gui.label_refinery"), 66, 5, -12829636, false);
		guiGraphics.text(this.font, EnergyDisplayProcedure.execute(world, x, y, z), 7, 90, -10944596, false);
		guiGraphics.text(this.font, ProgressDisplayProcedure.execute(world, x, y, z), 7, 80, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		onoff_switch = Checkbox.builder(Component.translatable("gui.alternate_dimension.refinery_gui.onoff_switch"), this.font).pos(this.leftPos + 48, this.topPos + 38).onValueChange((checkbox, value) -> {
			if (!menuStateUpdateActive)
				menu.sendMenuStateUpdate(entity, 1, "onoff_switch", value, false);
		}).build();
		this.addRenderableWidget(onoff_switch);
		power = new ExtendedSlider(this.leftPos + 7, this.topPos + 60, 125, 20, Component.translatable("gui.alternate_dimension.refinery_gui.power_prefix"), Component.translatable("gui.alternate_dimension.refinery_gui.power_suffix"), 1, 10, 5, 1, 0,
				true) {
			@Override
			protected void applyValue() {
				if (!menuStateUpdateActive)
					menu.sendMenuStateUpdate(entity, 2, "power", this.getValue(), false);
			}
		};
		this.addRenderableWidget(power);
		if (!menuStateUpdateActive)
			menu.sendMenuStateUpdate(entity, 2, "power", power.getValue(), false);
	}
}