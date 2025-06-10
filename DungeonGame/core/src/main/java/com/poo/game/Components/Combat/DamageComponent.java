package com.poo.game.Components.Combat;

import com.poo.game.BaseComponents.EntityComponent;
import com.poo.game.Interfaces.IUpdatableComponent;

public class DamageComponent extends EntityComponent implements IUpdatableComponent {

    public int damageAmount;

    // Time between attacks
    public float cooldown;
    public float currentCooldown = 0;

    public DamageComponent(int damageAmount, float cooldown) {
        this.damageAmount = damageAmount;
        this.cooldown = cooldown;
    }

    @Override
    public void Update(float DeltaTime) {
        currentCooldown -= DeltaTime;
    }

    @Override
    public boolean CanUpdate() {
        return IsActive;
    }
}
