/**
 * ---------------------------------------------------------------------------- Licenciatura de
 * Engenharia Informática - IADE - 2024/2025
 * ---------------------------------------------------------------------------- Projeto : Dungeon
 * Game (Projeto Grupo 1) Disciplica : Programação e Algoritmos (LEI1A2S) Professor : Nelson Costa
 * Autores : Affonso Neto | António Neto | Paulo Jadaugy | Tomás Pereira
 * ----------------------------------------------------------------------------
 */
package com.poo.game.map;

import com.poo.game.elements.Floor;

public class Corridor {
  private final MapData mapData;

  public Corridor(MapData mapData) {
    this.mapData = mapData;
  }

  public void connectRooms(Room room1, Room room2) {
    int x1 = room1.getCenterX();
    int y1 = room1.getCenterY();
    int x2 = room2.getCenterX();
    int y2 = room2.getCenterY();

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
      mapData.setTile(x, y, new Floor(x, y, 1, 1)); // 0 = floor
    }
  }

  private void drawVerticalCorridor(int y1, int y2, int x) {
    for (int y = Math.min(y1, y2); y <= Math.max(y1, y2); y++) {
      mapData.setTile(x, y, new Floor(x, y, 1, 1)); // 0 = floor
    }
  }
}
