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

import com.poo.game.components.SpriteImageComponent;

/** Player entity (ECS - Entity) */
public class PlayerEntity extends Entity {
  public PlayerEntity(float x, float y, float width, float height) {
    super("player");
    AddComponent(new SpriteImageComponent("player.png", x, y, width, height));
  }
}
