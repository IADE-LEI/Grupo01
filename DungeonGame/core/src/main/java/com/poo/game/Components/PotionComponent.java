package com.poo.game.Components;

import com.badlogic.gdx.math.Rectangle;
import com.poo.game.Interfaces.IUpdatableComponent;
import com.poo.game.BaseComponents.AEntityComponent;
import com.poo.game.Entities.Entity;
import com.poo.game.Components.Combat.HealthComponent;
import com.poo.game.System.PotionSystem;

public class PotionComponent extends AEntityComponent implements IUpdatableComponent {

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
