/**
 * ----------------------------------------------------------------------------
 * Licenciatura de Engenharia Informática - IADE - 2024/2025
 * ----------------------------------------------------------------------------
 * Projeto      : Dungeon Game (Projeto Grupo 1)
 * Disciplica   : Programação e Algoritmos (LEI1A2S)
 * Professor    : Nelson Costa
 * Autores      : Affonso Neto | António Neto | Paulo Jadaugy | Tomás Pereira
 * ----------------------------------------------------------------------------
 */

package com.poo.game.elements;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.poo.game.map.MapData;

public class Player extends MapElement {

    public Player(float x, float y, float width, float height) {
        super("player", x, y, width, height);
    }

    public boolean canMoveTo(float newX, float newY, MapData mapData, int tileSize) {
        // Create temporary rectangle for proposed position
        Rectangle tempRect = new Rectangle(newX, newY, sprite.getWidth(), sprite.getHeight());

        // Check each tile the player would overlap
        int startX = (int) (newX / tileSize);
        int startY = (int) (newY / tileSize);
        int endX = (int) ((newX + sprite.getWidth()) / tileSize);
        int endY = (int) ((newY + sprite.getHeight()) / tileSize);

        for (int x = startX; x <= endX; x++) {
            for (int y = startY; y <= endY; y++) {
                // Can't pass the tile
                if (!(mapData.getTile(x, y) instanceof Floor)) {
                    Rectangle wallRect = new Rectangle(x * tileSize, y * tileSize, tileSize, tileSize);
                    if (tempRect.overlaps(wallRect)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void move(float deltaX, float deltaY, MapData mapData, int tileSize) {

        // Ensure player doesn't get out of the map
        sprite.setX(MathUtils.clamp(sprite.getX(), 0, mapData.getWidth() - sprite.getWidth()));
        sprite.setY(MathUtils.clamp(sprite.getY(), 0, mapData.getHeight() - sprite.getHeight()));

        float newX = sprite.getX() + deltaX;
        float newY = sprite.getY() + deltaY;

        if (canMoveTo(newX, sprite.getY(), mapData, tileSize)) {
            sprite.setX(newX);
        }

        if (canMoveTo(sprite.getX(), newY, mapData, tileSize)) {
            sprite.setY(newY);
        }
    }
}
