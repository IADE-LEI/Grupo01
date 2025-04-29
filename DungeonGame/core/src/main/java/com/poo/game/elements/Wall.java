/**
 * ---------------------------------------------------------------------------- Licenciatura de
 * Engenharia Informática - IADE - 2024/2025
 * ---------------------------------------------------------------------------- Projeto : Dungeon
 * Game (Projeto Grupo 1) Disciplica : Programação e Algoritmos (LEI1A2S) Professor : Nelson Costa
 * Autores : Affonso Neto | António Neto | Paulo Jadaugy | Tomás Pereira
 * ----------------------------------------------------------------------------
 */
package com.poo.game.elements;

import com.badlogic.gdx.graphics.Color;

public class Wall extends MapElement {

  public Wall(float x, float y, float width, float height) {
    super(Color.BLACK, "wall", x, y, width, height);
  }
}
