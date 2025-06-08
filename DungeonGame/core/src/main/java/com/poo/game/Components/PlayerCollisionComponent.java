package com.poo.game.Components;

import com.poo.game.BaseComponents.AEntityComponent;
import com.poo.game.Entities.Entity;
import com.poo.game.Interfaces.IInteractable;
import com.poo.game.System.DamageSystem;

public class PlayerCollisionComponent extends AEntityComponent implements IInteractable {

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
