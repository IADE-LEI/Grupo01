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
import com.poo.game.BaseComponents.SpriteFactory;
import com.poo.game.Graph.MapNode;

public class Corridor {
    private final MapData mapData;
    private final MapNode[][] MapNodeGrid;

    public Corridor(MapData mapData, MapNode[][] MapNodeGrid) {
        this.mapData = mapData;
        this.MapNodeGrid = MapNodeGrid;
    }

    public void connectRooms(Room room1, Room room2) {
        int x1 = room1.getCenterX();
        int y1 = room1.getCenterY();
        int x2 = room2.getCenterX();
        int y2 = room2.getCenterY();

        // Randomly decide to connect vertically or horizontally
        if (Math.random() < 0.5) {
            drawHorizontalCorridor(x1, x2, y1);
            drawVerticalCorridor(y1, y2, x2);
        } else {
            drawVerticalCorridor(y1, y2, x1);
            drawHorizontalCorridor(x1, x2, y2);
        }
    }

    private void drawHorizontalCorridor(int x1, int x2, int y) {
        for (int x = Math.min(x1, x2); x <= Math.max(x1, x2); x++) {
            AddTileAndNode(x, y);
        }
    }

    private void drawVerticalCorridor(int y1, int y2, int x) {
        for (int y = Math.min(y1, y2); y <= Math.max(y1, y2); y++) {
            AddTileAndNode(x, y);
        }
    }

    private void AddTileAndNode(int x, int y) {
        mapData.setTile(x, y, SpriteFactory.CreateSpriteObject(Color.GRAY));
        MapNode NewMapNode = new MapNode(x, y, null);
        MapNodeGrid[x][y] = NewMapNode;
    }
}
