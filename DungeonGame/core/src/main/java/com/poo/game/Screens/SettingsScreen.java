/* ------------------------------------------------------------------------------------------------
 * Licenciatura de Engenharia Informática - IADE - 2024/2025
 * ------------------------------------------------------------------------------------------------
 * Projeto : Dungeon Game (Projeto Grupo 1)
 * Disciplica : Programação e Algoritmos (LEI1A2S)
 * Professor : Nelson Costa
 * Autores : Affonso Neto | António Neto | Paulo Jadaugy | Tiago Araújo | Tomás Pereira
 * ------------------------------------------------------------------------------------------------
 */
package com.poo.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.poo.game.Constants;
import com.poo.game.DungeonGame;

import java.util.prefs.BackingStoreException;

public class SettingsScreen extends BaseScreen implements Screen {
  TextButton.TextButtonStyle buttonStyle;
  TextureAtlas atlas;
  Button saveButton;
  Button cancelButton;
  Skin skin;
  ScreenViewport viewport;
  Stage stage;

  public SettingsScreen(final DungeonGame game) {
    super(game);

    viewport = new ScreenViewport();
    stage = new Stage(viewport);
    Gdx.input.setInputProcessor(stage);

    // Keyboard input for screen
    stage.addListener(
        new InputListener() {
          @Override
          public boolean keyDown(InputEvent event, int keycode) {
            switch (keycode) {
              case Input.Keys.F10:
                saveSettings();
                game.gotoEntryScreen();
                return true;

              case Input.Keys.ESCAPE:
                game.gotoEntryScreen();
                return true;
            }
            return false;
          }
        });
  }

  @Override
  public void show() {
    // Default skin for buttons
    skin = new Skin();
    atlas = new TextureAtlas(Constants.BUTTON_SKIN);
    skin.addRegions(atlas);

    // Buttons Style
    buttonStyle = new TextButton.TextButtonStyle();
    buttonStyle.up = skin.getDrawable("button");
    buttonStyle.over = skin.getDrawable("button-over");
    buttonStyle.down = skin.getDrawable("button-down");
    buttonStyle.font = new BitmapFont();

    saveButton = new TextButton("Save (F10)", buttonStyle);
    saveButton.setSize(192, 48);
    saveButton.setPosition(Gdx.graphics.getWidth() - saveButton.getWidth() - 5, 6);
    saveButton.addListener(
        new InputListener() {
          @Override
          public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            saveSettings();
            game.gotoEntryScreen();
            return true;
          }

          @Override
          public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            super.touchUp(event, x, y, pointer, button);
          }
        });

    cancelButton = new TextButton("Cancel (ESC)", buttonStyle);
    cancelButton.setSize(192, 48);
    float width = Gdx.graphics.getWidth() - cancelButton.getWidth() - saveButton.getWidth() - 10;
    cancelButton.setPosition(width, 6);
    cancelButton.addListener(
        new InputListener() {
          @Override
          public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            game.gotoEntryScreen();
            return true;
          }

          @Override
          public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            super.touchUp(event, x, y, pointer, button);
          }
        });

    // main table to fill the screen
    Table table = new Table();
    table.setFillParent(true);
    stage.addActor(table);

    table.add(cancelButton).fillY().uniformY();
    table.row().pad(0, 10, 0, 10);
    table.row();
    table.add(saveButton).fillY().uniformY();
  }

  @Override
  public void render(float delta) {
    Gdx.gl.glClearColor(0, 0, 0.1f, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
    stage.draw();
  }

  @Override
  public void resize(int width, int height) {
    stage.getViewport().update(width, height, true);
  }

  @Override
  public void dispose() {
    skin.dispose();
    stage.dispose();
  }

  private void saveSettings() {
    game.gameSettings.setPlayMusic(true);
    game.gameSettings.setMoveTurbo(4f);
    try {
      game.gameSettings.Save();
    } catch (BackingStoreException e) {
      throw new RuntimeException(e);
    }
  }
}
