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

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.poo.game.Components.Combat.HealthComponent;
import com.poo.game.Entities.Entity;
import com.poo.game.Utils.FontHelper;

public class HealthRenderSystem {

  public static void RenderHUD(Entity player, SpriteBatch Batch) {
    HealthComponent healthComponent = player.GetFirstComponentOfType(HealthComponent.class);

    int currentHealth = healthComponent.getCurrentHealth();
    String text = "Health: " + currentHealth;

    BitmapFont font = FontHelper.FontFromFile("Montserrat-Regular.ttf", 32);
    font.setColor(currentHealth >= 20 ? Color.GOLD : Color.RED);

    // Different camera to not mix with the game
    OrthographicCamera camera = new OrthographicCamera();
    camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    camera.update();

    Batch.setProjectionMatrix(camera.combined);

    font.draw(Batch, text, 0, camera.viewportHeight - 5);
  }
}
