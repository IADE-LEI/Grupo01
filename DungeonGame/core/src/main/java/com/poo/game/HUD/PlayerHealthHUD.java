package com.poo.game.HUD;

import com.poo.game.BaseComponents.AEntityComponent;
import com.poo.game.Components.HealthComponent;
import com.poo.game.Interfaces.IUpdatableComponent;

public class PlayerHealthHUD extends AEntityComponent implements IUpdatableComponent {

    private HealthComponent health;

    public PlayerHealthHUD(HealthComponent health) {
        this.health = health;
    }

    @Override
    public void Update(float DeltaTime) {

    }

    @Override
    public boolean CanUpdate() {
        return IsActive;
    }
}
