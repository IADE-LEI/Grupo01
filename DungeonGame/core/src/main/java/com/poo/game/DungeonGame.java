/**
 * ----------------------------------------------------------------------------
 * Licenciatura de Engenharia Informática - IADE - 2024/2025
 * ----------------------------------------------------------------------------
 * Projeto      : Dungeon Game (Projeto Grupo 1)
 * Disciplica   : Programação e Algoritmos (LEI1A2S)
 * Professor    : Nelson Costa
 * Autores      : Affonso Neto | António Neto | Paulo Jadaugy | Tomás Pereira
 * ----------------------------------------------------------------------------
 */

package com.poo.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.poo.game.screens.EntryScreen;

public class DungeonGame extends Game {
    public static int worldWidth = 32;
    public static int worldHeight = 20;

    public SpriteBatch batch;
    public BitmapFont font;
    public Music mainMusic;
    public FitViewport viewport;
    public GameOptions gameOptions;

    public void create() {
        batch = new SpriteBatch();

        // Using libGDX default font
        font = new BitmapFont();

        viewport = new FitViewport(worldWidth, worldHeight);

        //font has 15pt, but we need to scale it to our viewport by ratio of viewport height to screen height
        font.setUseIntegerPositions(false);
        font.getData().setScale(viewport.getWorldHeight() / Gdx.graphics.getHeight());

        // options for game (start with defaults)
        this.gameOptions = new GameOptions();

        // main music on game
        this.mainMusic = Gdx.audio.newMusic(Gdx.files.internal("audio/music/main.ogg"));
        this.mainMusic.setLooping(true);

        this.setScreen(new EntryScreen(this));
    }

    public void render() {
        // important
        super.render();

        if (gameOptions.getPlayMusic()) {
            mainMusic.play();
        }
    }

    public void dispose() {
        batch.dispose();
        font.dispose();
        mainMusic.dispose();
        getScreen().dispose();
    }
}
