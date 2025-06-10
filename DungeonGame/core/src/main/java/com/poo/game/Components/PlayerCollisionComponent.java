/* ------------------------------------------------------------------------------------------------
 * Licenciatura de Engenharia Informática - IADE - 2024/2025
 * ------------------------------------------------------------------------------------------------
 * Projeto : Dungeon Game (Projeto Grupo 1)
 * Disciplica : Programação e Algoritmos (LEI1A2S)
 * Professor : Nelson Costa
 * Autores : Affonso Neto | António Neto | Paulo Jadaugy | Tiago Araújo | Tomás Pereira
 * ------------------------------------------------------------------------------------------------
 */
package com.poo.game.Components;

import com.poo.game.BaseComponents.EntityComponent;
import com.poo.game.Entities.Entity;
import com.poo.game.Interfaces.IInteractable;
import com.poo.game.System.DamageSystem;

public class PlayerCollisionComponent extends EntityComponent implements IInteractable {

    private final Entity player;

    public PlayerCollisionComponent(Entity player) {
        this.player = player;
    }

    @Override
    public boolean CanInteract() {
        return IsActive;
    }

    @Override
    public void Interact() {
        if (AssignedEntity == player)
            return;

        if (player.getBounds().overlaps(AssignedEntity.getBounds()))
            DamageSystem.handleCollision(AssignedEntity, player);
    }
}
