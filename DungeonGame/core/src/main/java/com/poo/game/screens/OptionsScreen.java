/**
 * ---------------------------------------------------------------------------- Licenciatura de
 * Engenharia Informática - IADE - 2024/2025
 * ---------------------------------------------------------------------------- Projeto : Dungeon
 * Game (Projeto Grupo 1) Disciplica : Programação e Algoritmos (LEI1A2S) Professor : Nelson Costa
 * Autores : Affonso Neto | António Neto | Paulo Jadaugy | Tomás Pereira
 * ----------------------------------------------------------------------------
 */
package com.poo.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.poo.game.Constants;
import com.poo.game.DungeonGame;
import java.util.prefs.BackingStoreException;

public class OptionsScreen extends BaseScreen implements Screen {
  ScreenViewport viewport;
  Skin skin;
  Stage stage;
  Button saveButton;
  Button cancelButton;

  public OptionsScreen(final DungeonGame game) {
    super(game);

    skin = new Skin(Gdx.files.internal(Constants.BUTTON_SKIN));
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
                saveOptions();
                game.gotoEntryScreen();
                return true;

              case Input.Keys.ESCAPE:
                game.gotoEntryScreen();
                return true;
            }
            return false;
          }
        });

    saveButton = new TextButton("Save (F10)", skin, "small");
    saveButton.setSize(Constants.COL_WIDTH * 2, Constants.COL_HEIGHT);
    saveButton.setPosition(Constants.SCREEN_WIDTH - saveButton.getWidth(), 5);
    saveButton.addListener(
        new InputListener() {
          @Override
          public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            saveOptions();
            game.gotoEntryScreen();
            return true;
          }

          @Override
          public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            super.touchUp(event, x, y, pointer, button);
          }
        });
    stage.addActor(saveButton);

    cancelButton = new TextButton("Cancel (ESC)", skin, "small");
    cancelButton.setSize(Constants.COL_WIDTH * 2, Constants.COL_HEIGHT);
    cancelButton.setPosition(
        Constants.SCREEN_WIDTH - cancelButton.getWidth() - saveButton.getWidth(), 5);
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
    stage.addActor(cancelButton);
  }

  @Override
  public void show() {}

  @Override
  public void render(float delta) {
    ScreenUtils.clear(Color.NAVY);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    stage.act();
    stage.draw();
  }

  @Override
  public void resize(int width, int height) {
    stage.getViewport().update(width, height, false);
  }

  @Override
  public void dispose() {
    skin.dispose();
    stage.dispose();
  }

  private void saveOptions() {
    game.gameOptions.setPlayMusic(true);
    game.gameOptions.setMoveTurbo(4f);
    try {
      game.gameOptions.SaveOptions();
    } catch (BackingStoreException e) {
      throw new RuntimeException(e);
    }
  }
}
