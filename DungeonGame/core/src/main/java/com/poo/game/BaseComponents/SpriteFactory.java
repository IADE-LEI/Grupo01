package com.poo.game.BaseComponents;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SpriteFactory {
    public static Sprite CreateSpriteObject(Color color) {
        Pixmap pm = new Pixmap(16, 16, Pixmap.Format.RGBA8888);
        pm.setColor(color);
        pm.fill();
        Texture texture = new Texture(pm);
        return new Sprite(texture);
    }
}
