package com.poo.game.Components;

import com.poo.game.BaseComponents.AEntityComponent;


public class HealthComponent extends AEntityComponent  {

    private int currentHealth;
    private final int maxHealth;

    public HealthComponent(int health, int maxHealth, boolean IsActive) {
        this.currentHealth = health;
        this.maxHealth = maxHealth;
        this.IsActive = IsActive;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void decreaseHealth(int damage) {
        this.currentHealth -= damage;
        this.currentHealth = Math.max(0, this.currentHealth);
    }

    public void increaseHealth(int potion) {
        this.currentHealth += potion;
        this.currentHealth = Math.min(maxHealth, this.currentHealth);
    }
}
