package com.poo.game.Components;

import com.poo.game.BaseComponents.AEntityComponent;
import com.poo.game.Interfaces.IInteractable;

public class HealthComponent extends AEntityComponent implements IInteractable {

    private int currentHealth;
    private int maxHealth;

    public HealthComponent(int health, int maxHealth) {
        this.currentHealth = health;
        this.maxHealth = maxHealth;
    }

    @Override
    public boolean CanInteract() {
        return IsActive;
    }

    @Override
    public void Interact(AEntityComponent AEntityComponent) {

    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void decreaseHealth(int damage) {
        this.currentHealth -= damage;
    }

    public void increaseHealth(int potion) {
        this.currentHealth += potion;
        this.currentHealth = Math.min(maxHealth, this.currentHealth);
    }
}
