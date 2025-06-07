package com.poo.game.System;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.poo.game.Components.Damage.HealthComponent;
import com.poo.game.Entities.Entity;

public class HealthRenderSystem {

    public static void RenderHUD(Entity player, SpriteBatch Batch) {
        HealthComponent healthComponent = player.GetFirstComponentOfType(HealthComponent.class);

        String text = "Health: " + healthComponent.getCurrentHealth();

        BitmapFont font = new BitmapFont();
        font.setColor(Color.GOLD);

        //Different camera to not mix with the game
        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();

        Batch.setProjectionMatrix(camera.combined);

        font.draw(Batch, text, 0, camera.viewportHeight - 5);
    }
}
