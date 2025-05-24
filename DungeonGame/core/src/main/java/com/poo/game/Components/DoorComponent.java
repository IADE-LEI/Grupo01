package com.poo.game.Components;

import com.badlogic.gdx.math.Rectangle;
import com.poo.game.BaseComponents.AEntityComponent;
import com.poo.game.Entities.Entity;
import com.poo.game.Interfaces.IUpdatableComponent;
import com.poo.game.Scene.DungeonScene;

public class DoorComponent extends AEntityComponent implements IUpdatableComponent {

    private Entity Player;

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
