/* ------------------------------------------------------------------------------------------------
 * Licenciatura de Engenharia Informática - IADE - 2024/2025
 * ------------------------------------------------------------------------------------------------
 * Projeto : Dungeon Game (Projeto Grupo 1)
 * Disciplica : Programação e Algoritmos (LEI1A2S)
 * Professor : Nelson Costa
 * Autores : Affonso Neto | António Neto | Paulo Jadaugy | Tiago Araújo | Tomás Pereira
 * ------------------------------------------------------------------------------------------------
 */
package com.poo.game.Entities;

import com.poo.game.Interfaces.IPotionConsumedEvent;
import com.poo.game.Scene.DungeonScene;

import java.util.ArrayList;
import java.util.List;

public class Potion extends Entity {

  ArrayList<IPotionConsumedEvent> listeners;

  public Potion(DungeonScene scene, List<Integer> EntityTags) {
    super(scene, "Potion", EntityTags);
    listeners = new ArrayList<>();
  }

  public void addListener(IPotionConsumedEvent event) {
    listeners.add(event);
  }

  public void consumed() {
    for (int idx = 0; idx < listeners.size(); ++idx) {
      listeners.get(idx).potionConsumed(this);
    }
  }
}
