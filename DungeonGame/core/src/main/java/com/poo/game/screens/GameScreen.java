/* ------------------------------------------------------------------------------------------------
 * Licenciatura de Engenharia Informática - IADE - 2024/2025
 * ------------------------------------------------------------------------------------------------
 * Projeto : Dungeon Game (Projeto Grupo 1)
 * Disciplica : Programação e Algoritmos (LEI1A2S)
 * Professor : Nelson Costa
 * Autores : Affonso Neto | António Neto | Paulo Jadaugy | Tiago Araújo | Tomás Pereira
 * ------------------------------------------------------------------------------------------------
 */
package com.poo.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.poo.game.DungeonGame;
import com.poo.game.map.BSPMapGenerator;
import com.poo.game.map.MapData;
import com.poo.game.map.MapSystem;

/** Gamming screen */
public class GameScreen extends BaseScreen {
  private final MapSystem mapSystem;

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

    // Generate map
    BSPMapGenerator generator =
        new BSPMapGenerator(DungeonGame.worldWidth, DungeonGame.worldHeight, 5, 10, 4);
    MapData mapData = generator.generate();

    // Generate player on mapData
    mapData.setPlayer(0.85f, 0.85f);

    // Set up rendering with the map
    mapSystem = new MapSystem(mapData);
  }

  @Override
  public void render(float delta) {
    input();

    mapSystem.update(game);
  }

  private void input() {
    float turbo =
        Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)
                || Gdx.input.isKeyPressed(Input.Keys.CONTROL_RIGHT)
            ? game.gameOptions.getMoveTurbo()
            : 1f;
    float delta = Gdx.graphics.getDeltaTime();
    float speed = 2f * delta * turbo;

    if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
      mapSystem.movePlayer(MapSystem.ENMoveType.LEFT, speed);
    }
    if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
      mapSystem.movePlayer(MapSystem.ENMoveType.RIGHT, speed);
    }
    if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
      mapSystem.movePlayer(MapSystem.ENMoveType.UP, speed);
    }
    if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
      mapSystem.movePlayer(MapSystem.ENMoveType.DOWN, speed);
    }
  }
}
