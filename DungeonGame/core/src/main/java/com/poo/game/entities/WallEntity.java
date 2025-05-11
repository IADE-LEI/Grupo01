/* ------------------------------------------------------------------------------------------------
 * Licenciatura de Engenharia Informática - IADE - 2024/2025
 * ------------------------------------------------------------------------------------------------
 * Projeto : Dungeon Game (Projeto Grupo 1)
 * Disciplica : Programação e Algoritmos (LEI1A2S)
 * Professor : Nelson Costa
 * Autores : Affonso Neto | António Neto | Paulo Jadaugy | Tiago Araújo | Tomás Pereira
 * ------------------------------------------------------------------------------------------------
 */
package com.poo.game.entities;

import com.badlogic.gdx.graphics.Color;
import com.poo.game.components.SpriteColorComponent;

/** Wall entity (ECS - Entity) */
public class WallEntity extends Entity {
  public WallEntity(float x, float y, float width, float height) {
    super("wall");
    AddComponent(new SpriteColorComponent(Color.BLACK, x, y, width, height));
  }
}
