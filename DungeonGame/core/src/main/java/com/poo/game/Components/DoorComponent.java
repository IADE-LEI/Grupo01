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
import com.poo.game.BaseComponents.EntityComponent;
import com.poo.game.Entities.Entity;
import com.poo.game.Interfaces.IUpdatableComponent;

public class DoorComponent extends EntityComponent implements IUpdatableComponent {

    private final Entity Player;

    public DoorComponent(Entity player) {
        Player = player;
    }

    @Override
    public void Update(float DeltaTime) {
        Rectangle DoorBounds = this.AssignedEntity.getBounds();
        Rectangle PlayerBounds = Player.getBounds();

        //TODO Show that level is completed
        if(DoorBounds.overlaps(PlayerBounds)){
            this.AssignedEntity.DungeonScene.CreateWorld();
        }
    }

    @Override
    public boolean CanUpdate() {
        return IsActive;
    }
}
