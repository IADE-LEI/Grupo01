package com.poo.game.elements;

import com.badlogic.gdx.graphics.Color;

public class Floor extends MapElement {

    public Floor(float x, float y, float width, float height) {
        super(Color.GRAY,"floor", x, y, width, height);
    }
}
