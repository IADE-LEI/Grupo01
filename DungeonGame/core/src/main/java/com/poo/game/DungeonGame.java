/* ------------------------------------------------------------------------------------------------
 * Licenciatura de Engenharia Informática - IADE - 2024/2025
 * ------------------------------------------------------------------------------------------------
 * Projeto : Dungeon Game (Projeto Grupo 1)
 * Disciplica : Programação e Algoritmos (LEI1A2S)
 * Professor : Nelson Costa
 * Autores : Affonso Neto | António Neto | Paulo Jadaugy | Tiago Araújo | Tomás Pereira
 * ------------------------------------------------------------------------------------------------
 */
package com.poo.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.poo.game.Interfaces.ISettings;
import com.poo.game.Screens.EntryScreen;
import com.poo.game.Screens.GameOverScreen;
import com.poo.game.Screens.GameScreen;
import com.poo.game.Screens.SettingsScreen;

public class DungeonGame extends Game {
  public static int worldWidth = 50;
  public static int worldHeight = 50;
  public static int ViewportWidth = 20;
  public static int ViewportHeight = 20;
  public static int WindowSizeX = 1000;
  public static int WindowSizeY = 1000;
  public static int MaxNrMonsters = 5;
  public static int MaxNrPotions = 10;

  public SpriteBatch batch;
  public BitmapFont font;
  public FitViewport viewport;
  public ISettings gameSettings;

  Music mainMusic;

  public void create() {
    batch = new SpriteBatch();

    viewport = new FitViewport(ViewportWidth, ViewportHeight);

    // Using libGDX default font
    font = new BitmapFont();
    // font has 15pt, but we need to scale it to our viewport by ratio of
    // viewport height to screen height
    font.setUseIntegerPositions(false);
    font.getData().setScale(viewport.getWorldHeight() / Gdx.graphics.getHeight());

    // options for game (start with defaults)
    gameSettings = new Settings();

    // main music on game
    mainMusic = Gdx.audio.newMusic(Gdx.files.internal("audio/music/main.ogg"));
    mainMusic.setLooping(true);

    gotoEntryScreen();
  }

  public void render() {
    // important
    super.render();

    if (gameSettings.getPlayMusic()) {
      mainMusic.setVolume(gameSettings.getSoundVolume());
      mainMusic.play();
    } else {
      mainMusic.stop();
    }
  }

  public void gotoEntryScreen() {
    EntryScreen screen = new EntryScreen(this);
    setScreen(screen);
  }

  public void gotoGameScreen() {
    GameScreen screen = new GameScreen(this);
    setScreen(screen);
  }

  public void gotoSettingsScreen() {
    SettingsScreen screen = new SettingsScreen(this);
    setScreen(screen);
  }

  public void gotoGameOverScreen() {
    GameOverScreen screen = new GameOverScreen(this);
    setScreen(screen);
  }

  public void exit() {
    Gdx.app.exit();
  }

  public void dispose() {
    font.dispose();
    mainMusic.dispose();
    getScreen().dispose();
  }
}
