/* ------------------------------------------------------------------------------------------------
 * Licenciatura de Engenharia Informática - IADE - 2024/2025
 * ------------------------------------------------------------------------------------------------
 * Projeto : Dungeon Game (Projeto Grupo 1)
 * Disciplica : Programação e Algoritmos (LEI1A2S)
 * Professor : Nelson Costa
 * Autores : Affonso Neto | António Neto | Paulo Jadaugy | Tiago Araújo | Tomás Pereira
 * ------------------------------------------------------------------------------------------------
 */
package com.poo.game.BaseComponents;

import java.util.prefs.BackingStoreException;

public interface ISettings {
  boolean getPlayMusic();

  void setPlayMusic(boolean value);

  float getSoundVolume();

  void setSoundVolume(float value);

  float getMoveTurbo();

  void setMoveTurbo(float value);

  void Load();

  void Save() throws BackingStoreException;
}
