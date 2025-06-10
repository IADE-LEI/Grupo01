/* ------------------------------------------------------------------------------------------------
 * Licenciatura de Engenharia Informática - IADE - 2024/2025
 * ------------------------------------------------------------------------------------------------
 * Projeto : Dungeon Game (Projeto Grupo 1)
 * Disciplica : Programação e Algoritmos (LEI1A2S)
 * Professor : Nelson Costa
 * Autores : Affonso Neto | António Neto | Paulo Jadaugy | Tiago Araújo | Tomás Pereira
 * ------------------------------------------------------------------------------------------------
 */
package com.poo.game.System;

import com.poo.game.Components.Combat.DamageComponent;
import com.poo.game.Components.Combat.HealthComponent;
import com.poo.game.Components.Combat.InvincibilityComponent;
import com.poo.game.Entities.Entity;

public class DamageSystem {

    public static void handleCollision(Entity attacker, Entity target) {
        DamageComponent damage = attacker.GetFirstComponentOfType(DamageComponent.class);

        // Check if attacker can damage (cooldown)
        if (damage.currentCooldown > 0)
            return;

        // Check if target can take damage
        if (target.GetFirstComponentOfType(InvincibilityComponent.class) != null)
            return;

        //Apply the damage
        HealthComponent health = target.GetFirstComponentOfType(HealthComponent.class);
        health.decreaseHealth(damage.damageAmount);

        // Add temporary invincibility, 1 second invincibility
        target.AddComponent(new InvincibilityComponent(1.0f));

        // Reset damage cooldown
        damage.currentCooldown = damage.cooldown;
    }
}
