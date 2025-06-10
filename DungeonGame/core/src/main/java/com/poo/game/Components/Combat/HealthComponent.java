/* ------------------------------------------------------------------------------------------------
 * Licenciatura de Engenharia Informática - IADE - 2024/2025
 * ------------------------------------------------------------------------------------------------
 * Projeto : Dungeon Game (Projeto Grupo 1)
 * Disciplica : Programação e Algoritmos (LEI1A2S)
 * Professor : Nelson Costa
 * Autores : Affonso Neto | António Neto | Paulo Jadaugy | Tiago Araújo | Tomás Pereira
 * ------------------------------------------------------------------------------------------------
 */
package com.poo.game.Components.Combat;

import com.poo.game.BaseComponents.EntityComponent;

public class HealthComponent extends EntityComponent {

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
