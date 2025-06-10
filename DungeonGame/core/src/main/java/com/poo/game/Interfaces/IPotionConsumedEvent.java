/* ------------------------------------------------------------------------------------------------
 * Licenciatura de Engenharia Informática - IADE - 2024/2025
 * ------------------------------------------------------------------------------------------------
 * Projeto : Dungeon Game (Projeto Grupo 1)
 * Disciplica : Programação e Algoritmos (LEI1A2S)
 * Professor : Nelson Costa
 * Autores : Affonso Neto | António Neto | Paulo Jadaugy | Tiago Araújo | Tomás Pereira
 * ------------------------------------------------------------------------------------------------
 */
package com.poo.game.Interfaces;

import com.poo.game.Entities.Entity;

/** Interface for potion consumed event driven dispatcher */
public interface IPotionConsumedEvent {
    /**
     * Raise information that an potion entity is consumed
     * @param e Then potion entity that consumed
     */
    void potionConsumed(Entity e);
}
