/**
 * ---------------------------------------------------------------------------- Licenciatura de
 * Engenharia Informática - IADE - 2024/2025
 * ---------------------------------------------------------------------------- Projeto : Dungeon
 * Game (Projeto Grupo 1) Disciplica : Programação e Algoritmos (LEI1A2S) Professor : Nelson Costa
 * Autores : Affonso Neto | António Neto | Paulo Jadaugy | Tomás Pereira
 * ----------------------------------------------------------------------------
 */
package com.poo.game.map;

import com.badlogic.gdx.math.Vector2;
import com.poo.game.elements.Floor;
import com.poo.game.elements.MapElement;
import com.poo.game.elements.Wall;

public class MapData {
  private final MapElement[][] tiles;
  private final int width;
  private final int height;

  public MapData(int width, int height) {
    this.width = width;
    this.height = height;
    this.tiles = new MapElement[width][height];
    initializeWithWalls();
  }

  private void initializeWithWalls() {
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        tiles[x][y] = new Wall(x, y, 1, 1);
      }
    }
  }

  public void setTile(int x, int y, MapElement value) {
    // Validate inputs
    if (x >= 0 && x < width && y >= 0 && y < height) {
      tiles[x][y] = value;
    }
  }

  public MapElement getTile(int x, int y) {
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

  public MapElement[][] getTiles() {
    return tiles;
  }

  public Vector2 getStartingPosition() {
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        if (getTile(x, y) instanceof Floor) return new Vector2(x, y);
      }
    }
    return null;
  }
}
