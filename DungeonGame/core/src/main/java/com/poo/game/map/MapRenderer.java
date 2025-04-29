/**
 * ---------------------------------------------------------------------------- Licenciatura de
 * Engenharia Informática - IADE - 2024/2025
 * ---------------------------------------------------------------------------- Projeto : Dungeon
 * Game (Projeto Grupo 1) Disciplica : Programação e Algoritmos (LEI1A2S) Professor : Nelson Costa
 * Autores : Affonso Neto | António Neto | Paulo Jadaugy | Tomás Pereira
 * ----------------------------------------------------------------------------
 */
package com.poo.game.map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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
