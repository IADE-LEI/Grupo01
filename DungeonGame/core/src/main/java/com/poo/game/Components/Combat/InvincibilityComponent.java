/* ------------------------------------------------------------------------------------------------
 * Licenciatura de Engenharia Informática - IADE - 2024/2025
 * ------------------------------------------------------------------------------------------------
 * Projeto : Dungeon Game (Projeto Grupo 1)
 * Disciplica : Programação e Algoritmos (LEI1A2S)
 * Professor : Nelson Costa
 * Autores : Affonso Neto | António Neto | Paulo Jadaugy | Tiago Araújo | Tomás Pereira
 * ------------------------------------------------------------------------------------------------
 */
package com.poo.game.Components.Combat;

import com.poo.game.BaseComponents.EntityComponent;
import com.poo.game.Interfaces.IUpdatableComponent;

public class InvincibilityComponent extends EntityComponent implements IUpdatableComponent {
    //Temporary invincibility after being hit
    public float duration;
    public float timeLeft;

    public InvincibilityComponent(float duration) {
        this.duration = duration;
        this.timeLeft = duration;
    }

    @Override
    public void Update(float DeltaTime) {
        timeLeft -= DeltaTime;

        if (timeLeft <= 0) {
            this.AssignedEntity.RemoveFirstComponentOfType(InvincibilityComponent.class);
        }
    }

    @Override
    public boolean CanUpdate() {
        return IsActive;
    }
}
