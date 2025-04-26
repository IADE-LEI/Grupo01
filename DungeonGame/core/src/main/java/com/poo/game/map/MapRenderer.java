package com.poo.game.map;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.poo.game.elements.Wall;

public class MapRenderer {
    private MapData mapData;

    public MapRenderer(MapData mapData) {
        this.mapData = mapData;
    }

    public void render(SpriteBatch batch) {
        for (int x = 0; x < mapData.getWidth(); x++) {
            for (int y = 0; y < mapData.getHeight(); y++) {
                mapData.getTile(x, y).draw(batch);
            }
        }
    }

    public void dispose() {
        mapData = null;
    }
}
