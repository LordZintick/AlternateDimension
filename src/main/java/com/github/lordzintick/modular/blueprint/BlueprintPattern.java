package com.github.lordzintick.modular.blueprint;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;
import java.util.Map;

public record BlueprintPattern(
        List<String> pattern,
        Map<String, Ingredient> key,
        int materialCost
) {
    public static final Codec<BlueprintPattern> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.listOf().fieldOf("pattern").forGetter(BlueprintPattern::pattern),
            Codec.unboundedMap(Codec.STRING, Ingredient.CODEC).fieldOf("key").forGetter(BlueprintPattern::key),
            Codec.INT.optionalFieldOf("material_cost", 1).forGetter(BlueprintPattern::materialCost)
    ).apply(instance, BlueprintPattern::new));

    public boolean matches(CraftingInput input) {
        if (pattern.isEmpty()) return false;

        int minRow = pattern.size(), maxRow = -1;
        int minCol = Integer.MAX_VALUE, maxCol = -1;

        for (int r = 0; r < pattern.size(); r++) {
            String row = pattern.get(r);
            for (int c = 0; c < row.length(); c++) {
                if (row.charAt(c) != ' ') {
                    minRow = Math.min(minRow, r);
                    maxRow = Math.max(maxRow, r);
                    minCol = Math.min(minCol, c);
                    maxCol = Math.max(maxCol, c);
                }
            }
        }

        if (maxRow < minRow || maxCol < minCol) return false;

        int trimmedHeight = (maxRow - minRow) + 1;
        int trimmedWidth = (maxCol - minCol) + 1;

        if (input.width() != trimmedWidth || input.height() != trimmedHeight) {
            return false;
        }

        for (int y = 0; y < trimmedHeight; y++) {
            String row = pattern.get(minRow + y);
            for (int x = 0; x < trimmedWidth; x++) {
                int colIdx = minCol + x;
                char symbol = colIdx < row.length() ? row.charAt(colIdx) : ' ';
                ItemStack stack = input.getItem(x, y);

                if (symbol == ' ') {
                    if (!stack.isEmpty()) return false;
                } else {
                    Ingredient ing = key.get(String.valueOf(symbol));
                    if (ing == null || !ing.test(stack)) return false;
                }
            }
        }
        return true;
    }

    private boolean matchesAtOffset(CraftingInput input, int offsetX, int offsetY, int patWidth, int patHeight) {
        for (int y = 0; y < input.height(); y++) {
            for (int x = 0; x < input.width(); x++) {
                ItemStack stack = input.getItem(x, y);
                int patX = x - offsetX;
                int patY = y - offsetY;

                if (patX >= 0 && patX < patWidth && patY >= 0 && patY < patHeight) {
                    char symbol = pattern.get(patY).charAt(patX);
                    if (symbol == ' ') {
                        if (!stack.isEmpty()) return false;
                    } else {
                        Ingredient ing = key.get(String.valueOf(symbol));
                        if (ing == null || !ing.test(stack)) return false;
                    }
                } else if (!stack.isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }
}
