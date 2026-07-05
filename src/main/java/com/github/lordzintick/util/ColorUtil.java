package com.github.lordzintick.util;

public final class ColorUtil {
    public static int fromRGB(int r, int g, int b) {
        return (r << 16) | (g << 8) | b;
    }
}
