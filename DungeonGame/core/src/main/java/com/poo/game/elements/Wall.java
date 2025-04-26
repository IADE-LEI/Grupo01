package com.poo.game.elements;

import com.badlogic.gdx.graphics.Color;

public class Wall extends MapElement {

    public Wall(float x, float y, float width, float height) {
        super(Color.BLACK,"wall", x, y, width, height);
    }
}
