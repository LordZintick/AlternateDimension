package com.github.lordzintick.item.backpack;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

public class BackpackScreen extends AbstractContainerScreen<BackpackMenu> {
    private static final Identifier CONTAINER_TEXTURE =
            Identifier.withDefaultNamespace("textures/gui/container/generic_54.png");

    private final int containerRows;

    public BackpackScreen(BackpackMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title, 176, 114 + menu.getRows() * 18);
        this.containerRows = menu.getRows();
        this.inventoryLabelY = this.imageHeight - 94;
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {
        super.extractBackground(graphics, mouseX, mouseY, partialTick);

        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;

        graphics.blit(
                RenderPipelines.GUI_TEXTURED,
                CONTAINER_TEXTURE,
                x, y,
                0.0F, 0.0F,
                this.imageWidth, this.containerRows * 18 + 17,
                256, 256
        );
        graphics.blit(
                RenderPipelines.GUI_TEXTURED,
                CONTAINER_TEXTURE,
                x, y + this.containerRows * 18 + 17,
                0.0F, 126.0F,
                this.imageWidth, 96,
                256, 256
        );
    }

    @Override
    protected void extractLabels(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
        int maxTitleWidth = this.imageWidth - 16;
        Component displayTitle = this.title;

        if (this.font.width(displayTitle) > maxTitleWidth) {
            String ellipsis = "...";
            int availableWidth = maxTitleWidth - this.font.width(ellipsis);
            String trimmed = this.font.plainSubstrByWidth(displayTitle.getString(), Math.max(0, availableWidth));
            displayTitle = Component.literal(trimmed + ellipsis).withStyle(displayTitle.getStyle());
        }

        graphics.text(this.font, displayTitle, this.titleLabelX, this.titleLabelY, 0xFF404040, false);
        graphics.text(this.font, this.playerInventoryTitle, this.inventoryLabelX, this.inventoryLabelY, 0xFF404040, false);
    }
}
