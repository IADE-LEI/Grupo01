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
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.poo.game.Constants;
import com.poo.game.DungeonGame;
import com.poo.game.Utils.FontHelper;

public class GameOverScreen extends BaseScreen {
  ScreenViewport viewport;
  Texture backgroundTexture;
  BitmapFont titleFont;
  Image backgroundImage;
  Stack stack;
  Button playButton;
  Button exitButton;
  TextureAtlas atlas;
  Skin buttonSkin;
  Stage stage;

  public GameOverScreen(final DungeonGame game) {
    super(game);

    viewport = new ScreenViewport();
    stage = new Stage(viewport);
    stage.addListener(
        new InputListener() {
          @Override
          public boolean keyDown(InputEvent event, int keycode) {
            switch (keycode) {
              case Input.Keys.F2:
                game.gotoGameScreen();
                return true;
              case Input.Keys.ESCAPE:
                game.exit();
                return true;
            }
            return false;
          }
        });
    Gdx.input.setInputProcessor(stage);

    // Skin for buttons
    buttonSkin = new Skin();
    atlas = new TextureAtlas(Constants.BUTTON_SKIN);
    buttonSkin.addRegions(atlas);

    // Buttons Style
    TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
    buttonStyle.up = buttonSkin.getDrawable("button");
    buttonStyle.over = buttonSkin.getDrawable("button-over");
    buttonStyle.down = buttonSkin.getDrawable("button-down");
    buttonStyle.font = FontHelper.FontFromFile("Dungeon.ttf", 32);

    playButton = new TextButton("Play Again", buttonStyle);
    playButton.setSize(192, 48);
    playButton.addListener(
        new InputListener() {
          @Override
          public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            game.gotoGameScreen();
            return true;
          }

          @Override
          public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            super.touchUp(event, x, y, pointer, button);
          }
        });

    exitButton = new TextButton("Exit", buttonStyle);
    exitButton.setSize(192, 48);
    exitButton.addListener(
        new InputListener() {
          @Override
          public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            game.exit();
            return true;
          }

          @Override
          public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            super.touchUp(event, x, y, pointer, button);
          }
        });

    VerticalGroup buttonsGroup = new VerticalGroup();
    buttonsGroup.setFillParent(true);
    buttonsGroup.align(Align.center);
    buttonsGroup.space(15);
    buttonsGroup.addActor(playButton);
    buttonsGroup.addActor(exitButton);
    buttonsGroup.setVisible(true);

    stack = new Stack();
    stack.setFillParent(true);
    stack.addActor(buttonsGroup);
    stack.setVisible(true);
    stage.addActor(stack);

    // texture for background image
    backgroundTexture = new Texture("image/entry-background.jpg");
    backgroundImage = new Image(backgroundTexture);
    backgroundImage.setSize(backgroundTexture.getWidth(), backgroundTexture.getHeight());
    backgroundImage.setAlign(Align.center);
    backgroundImage.setLayoutEnabled(true);
    backgroundImage.setFillParent(true);

    titleFont = FontHelper.FontFromFile("Dungeon.ttf", 90);
  }

  @Override
  public void render(float delta) {
    clearScreen();

    game.batch.begin();

    stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
    game.viewport.apply();
    game.batch.setProjectionMatrix(game.viewport.getCamera().combined);

    Group background = new Group();
    background.toBack();
    background.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    Group foreground = new Group();
    foreground.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

    stage.addActor(background);
    stage.addActor(foreground);

    foreground.addActor(stack);
    background.addActor(backgroundImage);
    Gdx.input.setInputProcessor(stage);

    String text = "GAME OVER";
    float widthPosition = FontHelper.CenterScreen(titleFont, text, Gdx.graphics.getWidth());
    titleFont.draw(game.batch, text, widthPosition, 170);

    stage.draw();
    game.batch.end();
  }

  @Override
  public void dispose() {
    atlas.dispose();
    titleFont.dispose();
    backgroundTexture.dispose();
    buttonSkin.dispose();
    stage.dispose();
  }
}
