/* ------------------------------------------------------------------------------------------------
 * Licenciatura de Engenharia Informática - IADE - 2024/2025
 * ------------------------------------------------------------------------------------------------
 * Projeto : Dungeon Game (Projeto Grupo 1)
 * Disciplica : Programação e Algoritmos (LEI1A2S)
 * Professor : Nelson Costa
 * Autores : Affonso Neto | António Neto | Paulo Jadaugy | Tiago Araújo | Tomás Pereira
 * ------------------------------------------------------------------------------------------------
 */
package com.poo.game.Map;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.poo.game.BaseComponents.SpriteFactory;
import com.poo.game.Graph.MapGraph;

/**
 * Class for map information Tiles (Floor & Wall) & Player
 */
public class MapData {
    private final int width;
    private final int height;

    private final Sprite[][] tiles;
    public MapGraph MapGraph = new MapGraph();


    private Room exit = null;

    public MapData(int width, int height) {
        this.width = width;
        this.height = height;
        this.tiles = new Sprite[width][height];

    }

    public void setTile(int x, int y, Sprite value) {
        // Validate inputs
        if (x >= 0 && x < width && y >= 0 && y < height) {
            tiles[x][y] = value;
        }
    }

    public Sprite getTile(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            return tiles[x][y];
        }
        return null; // Return null if out of bounds
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Sprite[][] getTiles() {
        return tiles;
    }

    public Vector2 getStartingPosition() {
        return MapGraph.GetFirstNode().GetPosition();
    }

    public void RenderMap(SpriteBatch SpriteBatch) {
        for (int Y = 0; Y < height; ++Y) {
            for (int X = 0; X < width; ++X) {
                if (tiles[X][Y] == null) continue;

                SpriteBatch.draw(tiles[X][Y], X, Y, 1, 1);
            }
        }
    }

    public void SetExitRoom(Room room) {
        exit = room;
    }

    public Room GetExitRoom() {
        return exit;
    }
}
