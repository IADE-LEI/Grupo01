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

import com.poo.game.Entities.Entity;

public class EntityComponent {
    protected Entity AssignedEntity = null;
    protected boolean IsActive = true;

    public void Start() {
    }

    public Entity AssignedEntity() {
        return AssignedEntity;
    }

    public void AssignedEntity(Entity Entity) {
        AssignedEntity = Entity;
    }

    public boolean IsActive() {
        return IsActive;
    }

    public void SetIsActive(boolean active) {
        IsActive = active;
    }

}
