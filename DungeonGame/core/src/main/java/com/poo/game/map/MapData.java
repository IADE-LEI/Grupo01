/* ------------------------------------------------------------------------------------------------
 * Licenciatura de Engenharia Informática - IADE - 2024/2025
 * ------------------------------------------------------------------------------------------------
 * Projeto : Dungeon Game (Projeto Grupo 1)
 * Disciplica : Programação e Algoritmos (LEI1A2S)
 * Professor : Nelson Costa
 * Autores : Affonso Neto | António Neto | Paulo Jadaugy | Tiago Araújo | Tomás Pereira
 * ------------------------------------------------------------------------------------------------
 */
package com.poo.game.map;

import com.badlogic.gdx.math.Vector2;
import com.poo.game.entities.*;

/** Class for map information Tiles (Floor & Wall) & Player */
public class MapData {
  private final int width;
  private final int height;

  private final Entity[][] tiles;
  private PlayerEntity player;

  public MapData(int width, int height) {
    this.width = width;
    this.height = height;
    this.tiles = new Entity[width][height];
    initializeWithWalls();
  }

  private void initializeWithWalls() {
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        tiles[x][y] = new WallEntity(x, y, 1, 1);
      }
    }
  }

  public void setPlayer(float width, float height) {
    Vector2 startingPosition = getStartingPosition();
    this.player = new PlayerEntity(startingPosition.x, startingPosition.y, width, height);
  }

  public PlayerEntity getPlayer() {
    return player;
  }

  public void setTile(int x, int y, Entity value) {
    // Validate inputs
    if (x >= 0 && x < width && y >= 0 && y < height) {
      tiles[x][y] = value;
    }
  }

  public Entity getTile(int x, int y) {
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

  public Entity[][] getTiles() {
    return tiles;
  }

  public Vector2 getStartingPosition() {
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        if (getTile(x, y) instanceof FloorEntity) return new Vector2(x, y);
      }
    }
    return null;
  }
}
