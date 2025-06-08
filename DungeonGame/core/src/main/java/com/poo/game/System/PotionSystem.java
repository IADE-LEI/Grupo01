package com.poo.game.System;

import com.poo.game.Components.PotionComponent;
import com.poo.game.Entities.Entity;
import com.poo.game.Components.Combat.HealthComponent;

public class PotionSystem {

    public static void handleCollision(Entity potion, Entity player) {
        PotionComponent potionComponent = potion.GetFirstComponentOfType(PotionComponent.class);

        // Apply health
        HealthComponent health = player.GetFirstComponentOfType(HealthComponent.class);
        health.increaseHealth(potionComponent.getAmountOfHealth());

        // TODO: Remove the entity from the scene - implement Events
        potion.DungeonScene.RemoveEntity(potion);
    }
}
