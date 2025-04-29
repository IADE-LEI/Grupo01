/* ------------------------------------------------------------------------------------------------
 * Licenciatura de Engenharia Informática - IADE - 2024/2025
 * ------------------------------------------------------------------------------------------------
 * Projeto : Dungeon Game (Projeto Grupo 1)
 * Disciplica : Programação e Algoritmos (LEI1A2S)
 * Professor : Nelson Costa
 * Autores : Affonso Neto | António Neto | Paulo Jadaugy | Tomás Pereira
 * ------------------------------------------------------------------------------------------------
 */
package com.poo.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.poo.game.DungeonGame;

public class EntryScreen extends BaseScreen {
  Texture backgroundTexture;
  SpriteBatch batch;
  Stage stage;

  public EntryScreen(final DungeonGame game) {
    super(game);

    stage = new Stage();
    stage.addListener(
        new InputListener() {
          @Override
          public boolean keyDown(InputEvent event, int keycode) {
            switch (keycode) {
              case Input.Keys.F2:
                game.gotoGameScreen();
                return true;
              case Input.Keys.F10:
                game.gotoOptionsScreen();
                return true;
              case Input.Keys.ESCAPE:
                game.exit();
                return true;
            }
            return false;
          }
        });
    Gdx.input.setInputProcessor(stage);

    // texture for background image
    backgroundTexture = new Texture("image/entry-background.jpg");
    batch = new SpriteBatch();
  }

  @Override
  public void render(float delta) {
    draw();
  }

  @Override
  public void dispose() {
    batch.dispose();
    stage.dispose();
  }

  private void draw() {
    ScreenUtils.clear(Color.BLACK);

    batch.setProjectionMatrix(stage.getCamera().combined);
    batch.begin();
    batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    batch.end();

    game.viewport.apply();
    game.batch.setProjectionMatrix(game.viewport.getCamera().combined);

    game.batch.begin();

    game.font.draw(game.batch, "Welcome to the Dungeon Game ", 15, 16);
    game.font.draw(game.batch, "Play (F2)", 15, 13);
    game.font.draw(game.batch, "Options (F10)", 15, 12);
    game.font.draw(game.batch, "Exit (ESC)", 15, 11);

    game.batch.end();
  }
}
