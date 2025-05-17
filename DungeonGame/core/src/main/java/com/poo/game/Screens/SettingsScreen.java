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
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.poo.game.Constants;
import com.poo.game.DungeonGame;
import com.poo.game.Utils.FontHelper;

import java.util.prefs.BackingStoreException;

public class SettingsScreen extends BaseScreen implements Screen {
  ScreenViewport viewport;
  CheckBox musicCheckBox;
  Slider soundVolumeSlider;
  Slider turboSlider;
  Button saveButton;
  Button cancelButton;
  Skin buttonSkin;
  Skin uiSkin;
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
    // Skin for UI componente (wihout buttons)
    uiSkin = new Skin(Gdx.files.internal(Constants.UI_SKIN));

    // Skin for buttons
    buttonSkin = new Skin();
    TextureAtlas atlas = new TextureAtlas(Constants.BUTTON_SKIN);
    buttonSkin.addRegions(atlas);

    // Buttons Style
    TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
    buttonStyle.up = buttonSkin.getDrawable("button");
    buttonStyle.over = buttonSkin.getDrawable("button-over");
    buttonStyle.down = buttonSkin.getDrawable("button-down");
    buttonStyle.font = FontHelper.FontFromTtf("Dungeon.ttf", 32);

    // Buttons
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
    boolean tableDebug = true;

    // styles for labels (title & options)
    Label.LabelStyle titleStyle = new Label.LabelStyle();
    titleStyle.font = FontHelper.FontFromTtf("Dungeon.ttf", 54);
    Label.LabelStyle labelStyle = new Label.LabelStyle();
    labelStyle.font = FontHelper.FontFromTtf("Dungeon.ttf", 45);

    // title
    HorizontalGroup titleGroup = new HorizontalGroup();
    titleGroup.align(Align.top);
    titleGroup.padTop(25);
    Label title = new Label("Game Settings", titleStyle);
    titleGroup.addActor(title);
    titleGroup.setVisible(true);

    // music group
    HorizontalGroup musicGroup = new HorizontalGroup();
    musicGroup.align(Align.top);
    musicGroup.padTop(128);
    musicGroup.space(15);
    Label musicLabel = new Label("Music", labelStyle);
    musicCheckBox = new CheckBox("", uiSkin);
    musicCheckBox.setChecked(game.gameSettings.getPlayMusic());
    musicGroup.addActor(musicLabel);
    musicGroup.addActor(musicCheckBox);
    musicGroup.setVisible(true);

    // sound group
    HorizontalGroup soundGroup = new HorizontalGroup();
    soundGroup.align(Align.top);
    soundGroup.padTop(192);
    soundGroup.space(15);
    Label soundVolumeLabel = new Label("Sound", labelStyle);
    soundVolumeSlider = new Slider(0, 1, 0.1f, false, uiSkin);
    soundVolumeSlider.setValue(game.gameSettings.getSoundVolume());
    soundGroup.addActor(soundVolumeLabel);
    soundGroup.addActor(soundVolumeSlider);
    soundGroup.setVisible(true);

    // turbo group
    HorizontalGroup turboGroup = new HorizontalGroup();
    turboGroup.align(Align.top);
    turboGroup.padTop(256);
    turboGroup.space(15);
    Label turboLabel = new Label("Turbo", labelStyle);
    turboSlider = new Slider(0, 15, 1, false, uiSkin);
    turboSlider.setValue(game.gameSettings.getMoveTurbo());
    turboGroup.addActor(turboLabel);
    turboGroup.addActor(turboSlider);
    turboGroup.setVisible(true);

    // buttons group
    HorizontalGroup buttonsGroup = new HorizontalGroup();
    buttonsGroup.setDebug(tableDebug);
    buttonsGroup.setFillParent(true);
    buttonsGroup.align(Align.bottom);
    buttonsGroup.space(15);
    buttonsGroup.padBottom(25);
    buttonsGroup.addActor(cancelButton);
    buttonsGroup.addActor(saveButton);

    // main stack
    Stack stack = new Stack();
    stack.setDebug(tableDebug);
    stack.setFillParent(true);
    stack.addActor(buttonsGroup);
    stack.addActor(musicGroup);
    stack.addActor(soundGroup);
    stack.addActor(turboGroup);
    stack.addActor(titleGroup);
    stage.addActor(stack);
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
    buttonSkin.dispose();
    uiSkin.dispose();
    stage.dispose();
  }

  private void saveSettings() {
    game.gameSettings.setPlayMusic(musicCheckBox.isChecked());
    game.gameSettings.setSoundVolume(soundVolumeSlider.getValue());
    game.gameSettings.setMoveTurbo(turboSlider.getValue());
    try {
      game.gameSettings.Save();
    } catch (BackingStoreException e) {
      throw new RuntimeException(e);
    }
  }
}
