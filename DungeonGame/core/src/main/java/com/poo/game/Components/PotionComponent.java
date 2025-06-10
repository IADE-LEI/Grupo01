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

import com.badlogic.gdx.math.Rectangle;
import com.poo.game.Interfaces.IUpdatableComponent;
import com.poo.game.BaseComponents.EntityComponent;
import com.poo.game.Entities.Entity;
import com.poo.game.System.PotionSystem;

public class PotionComponent extends EntityComponent implements IUpdatableComponent {

    private final Entity Player;
    private static final int amountOfHealth = 10;

    public PotionComponent(Entity player) {
        Player = player;
    }

    @Override
    public void Update(float DeltaTime) {
        Rectangle PotionBounds = this.AssignedEntity.getBounds();
        Rectangle PlayerBounds = Player.getBounds();

        //TODO Show that level is completed
        if(PotionBounds.overlaps(PlayerBounds)){
            PotionSystem.handleCollision(this.AssignedEntity, Player);
        }
    }

    @Override
    public boolean CanUpdate() {
        return IsActive;
    }

    public int getAmountOfHealth(){
        return amountOfHealth;
    }
}
