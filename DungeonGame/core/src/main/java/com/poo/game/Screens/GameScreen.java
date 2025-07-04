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
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.poo.game.DungeonGame;
import com.poo.game.Scene.DungeonScene;

/** Gaming screen */
public class GameScreen extends BaseScreen {
  private final DungeonScene dungeonScene;
  Stage stage;

  public GameScreen(final DungeonGame game) {
    super(game);

    stage = new Stage();
    stage.addListener(
        new InputListener() {
          @Override
          public boolean keyDown(InputEvent event, int keyCode) {
            if (keyCode == Input.Keys.ESCAPE) {
              game.gotoEntryScreen();
              return true;
            }
            return false;
          }
        });
    Gdx.input.setInputProcessor(stage);

    dungeonScene = new DungeonScene(this.game);
    dungeonScene.CreateWorld();
  }

  @Override
  public void render(float delta) {
    dungeonScene.UpdateWorld(delta);

    // Handle interactions after updating the world
    dungeonScene.HandleInteractions();

    ScreenUtils.clear(Color.BLACK);
    game.viewport.apply();

    dungeonScene.RenderWorld();
  }
}
