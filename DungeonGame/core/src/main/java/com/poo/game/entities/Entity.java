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

import com.badlogic.gdx.utils.Array;
import com.poo.game.components.Component;

import java.util.UUID;

/** Base class for entities of game (ECS - Entity) */
public abstract class Entity {

  /** Unique ID of entity */
  int ID;

  /** Name of entity */
  String name;

  /** List of component data for entity */
  Array<Component> components = null;

  public Entity(String name) {
    // Generate random unique identifier for entity ID and save hashCode
    this.ID = UUID.randomUUID().hashCode();
    this.name = name;
  }

  public int getID() {
    return ID;
  }

  public String getName() {
    return name;
  }

  public Array<Component> getComponents() {
    return components;
  }

  /**
   * Add element to component list
   *
   * @param component New element to add
   */
  public void AddComponent(Component component) {
    if (components == null) {
      components = new Array<>();
    }
    components.add(component);
  }
}
