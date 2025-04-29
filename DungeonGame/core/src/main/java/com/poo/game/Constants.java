/* ------------------------------------------------------------------------------------------------
 * Licenciatura de Engenharia Informática - IADE - 2024/2025
 * ------------------------------------------------------------------------------------------------
 * Projeto : Dungeon Game (Projeto Grupo 1)
 * Disciplica : Programação e Algoritmos (LEI1A2S)
 * Professor : Nelson Costa
 * Autores : Affonso Neto | António Neto | Paulo Jadaugy | Tomás Pereira
 * ------------------------------------------------------------------------------------------------
 */
package com.poo.game;

import com.badlogic.gdx.Gdx;

public class Constants {
  public static final String BUTTON_SKIN = "skin/button/glassy-ui.json";
  public static final String BUTTON_SKIN_ATLAS = "skin/button/glassy-ui.atlas";

  public static final int SCREEN_WIDTH = Gdx.graphics.getWidth();
  public static final int SCREEN_HEIGHT = Gdx.graphics.getHeight();
  public static final int CENTER_X = SCREEN_WIDTH / 2;
  public static final int CENTER_Y = SCREEN_HEIGHT / 2;
  public static final int COL_WIDTH = SCREEN_WIDTH / 10;
  public static final int COL_HEIGHT = SCREEN_HEIGHT / 10;
}
