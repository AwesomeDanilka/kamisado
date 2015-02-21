package com.example.kamisado.model;

import static android.graphics.Color.argb;

/**
 * Created by danil on 21/02/2015.
 */
public enum Color {
    ORANGE, GREEN, RED, MAGENTA, YELLOW, BLUE, CYAN, BROWN;

    private int color;

    static {
        ORANGE.color = argb(0xFF, 0xFF, 0xA5, 0x00);
        GREEN.color = argb(0xFF, 0xFF, 0xA5, 0x00);
        RED.color = argb(0xFF, 0xFF, 0xA5, 0x00);
        MAGENTA.color = argb(0xFF, 0xFF, 0xA5, 0x00);
        YELLOW.color = argb(0xFF, 0xFF, 0xA5, 0x00);
        BLUE.color = argb(0xFF, 0xFF, 0xA5, 0x00);
        CYAN.color = argb(0xFF, 0xFF, 0xA5, 0x00);
        BROWN.color = argb(0xFF, 0xFF, 0xA5, 0x00);


    }

    public int getIntColor() {
        return color;
    }

}
