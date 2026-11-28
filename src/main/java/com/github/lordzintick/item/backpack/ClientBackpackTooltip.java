package com.github.lordzintick.item.backpack;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ARGB;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;

public class ClientBackpackTooltip implements ClientTooltipComponent {
    private static final Identifier SLOT_SPRITE = Identifier.withDefaultNamespace("container/slot");
    private static final int SLOT_SIZE = 18;
    private static final int COLUMNS = 9;
    private static final int BAR_HEIGHT = 12;
    private static final int BAR_MARGIN_BOTTOM = 4;

    private final BackpackInventoryComponent inventory;
    private final NonNullList<ItemStack> items;
    private final int totalSlots;
    private final int occupiedSlots;
    private final int rows;
    private final int cols;

    public ClientBackpackTooltip(BackpackTooltipComponent component) {
        this.inventory = component.inventory();
        this.totalSlots = inventory.slots();
        this.items = inventory.copyItemsToList();
        this.occupiedSlots = inventory.occupiedSlots();
        this.cols = Math.min(COLUMNS, totalSlots);
        this.rows = Math.max(1, Mth.ceil(totalSlots / (float) COLUMNS));
    }

    @Override
    public int getWidth(Font font) {
        return Math.max(this.cols * SLOT_SIZE + 2, 100);
    }

    @Override
    public int getHeight(Font font) {
        return BAR_HEIGHT + BAR_MARGIN_BOTTOM + (this.rows * SLOT_SIZE) + 4;
    }

    @Override
    public void extractImage(Font font, int x, int y, int w, int h, GuiGraphicsExtractor graphics) {
        int gridWidth = this.cols * SLOT_SIZE;
        int barWidth = Math.max(gridWidth, 100);

        float fillRatio = totalSlots > 0 ? Mth.clamp((float) occupiedSlots / totalSlots, 0.0F, 1.0F) : 0.0F;

        graphics.fill(x, y, x + barWidth, y + BAR_HEIGHT, 0xFF555555);
        graphics.fill(x + 1, y + 1, x + barWidth - 1, y + BAR_HEIGHT - 1, 0xFF1A1A1A);

        int innerMaxWidth = barWidth - 2;
        int filledWidth = Mth.floor(innerMaxWidth * fillRatio);
        if (filledWidth > 0) {
            int barColor = computeFillColor(fillRatio);
            graphics.fill(x + 1, y + 1, x + 1 + filledWidth, y + BAR_HEIGHT - 1, barColor);
        }

        Component label = Component.literal(occupiedSlots + " / " + totalSlots);
        int textX = x + (barWidth - font.width(label)) / 2;
        int textY = y + (BAR_HEIGHT - font.lineHeight) / 2 + 1;
        graphics.text(font, label, textX, textY, 0xFFFFFFFF, true);

        int gridStartY = y + BAR_HEIGHT + BAR_MARGIN_BOTTOM;
        for (int index = 0; index < totalSlots; index++) {
            int row = index / COLUMNS;
            int col = index % COLUMNS;
            int slotX = x + col * SLOT_SIZE;
            int slotY = gridStartY + row * SLOT_SIZE;

            graphics.blitSprite(RenderPipelines.GUI_TEXTURED, SLOT_SPRITE, slotX, slotY, SLOT_SIZE, SLOT_SIZE);

            ItemStack stack = items.get(index);
            if (!stack.isEmpty()) {
                graphics.item(stack, slotX + 1, slotY + 1, index);
                graphics.itemDecorations(font, stack, slotX + 1, slotY + 1);
            }
        }
    }

    private static int computeFillColor(float ratio) {
        if (ratio >= 1.0F) {
            return 0xFFD93838;
        } else if (ratio >= 0.75F) {
            return 0xFFE69D24;
        }
        return 0xFF3FB55C;
    }
}
