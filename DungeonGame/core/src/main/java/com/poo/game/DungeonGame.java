package com.poo.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.poo.game.screens.EntryScreen;

public class DungeonGame extends Game {
    public static int worldWidth = 32;
    public static int worldHeight = 20;

    public SpriteBatch batch;
    public BitmapFont font;
    public FitViewport viewport;

    public void create() {
        batch = new SpriteBatch();

        // Using libGDX default font
        font = new BitmapFont();

        viewport = new FitViewport(worldWidth, worldHeight);

        //font has 15pt, but we need to scale it to our viewport by ratio of viewport height to screen height
        font.setUseIntegerPositions(false);
        font.getData().setScale(viewport.getWorldHeight() / Gdx.graphics.getHeight());

        this.setScreen(new EntryScreen(this));
    }

    public void render() {
        // important
        super.render();
    }

    public void dispose() {
        batch.dispose();
        font.dispose();
        getScreen().dispose();
    }
}
