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

package com.poo.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.poo.game.DungeonGame;
import com.poo.game.elements.Floor;
import com.poo.game.elements.MapElement;
import com.poo.game.elements.Player;
import com.poo.game.map.BSPMapGenerator;
import com.poo.game.map.MapData;
import com.poo.game.map.MapRenderer;


public class GameScreen implements Screen {
    final DungeonGame game;

    private MapData mapData;
    private MapRenderer mapRenderer;

    private final Player player;

    public GameScreen(final DungeonGame game) {
        this.game = game;

        BSPMapGenerator generator = new BSPMapGenerator(DungeonGame.worldWidth, DungeonGame.worldHeight, 5, 10, 4);
        mapData = generator.generate();

        Vector2 startingPosition = mapData.getStartingPosition();
        player = new Player(startingPosition.x,startingPosition.y,0.85f,0.85f);

        // Set up rendering with the map
        mapRenderer = new MapRenderer(mapData);

    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        input();
        draw();
    }

    private void input() {
        float turbo =
            Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT) ||
                Gdx.input.isKeyPressed(Input.Keys.CONTROL_RIGHT) ? game.gameOptions.getMoveTurbo() : 1f;
        float delta = Gdx.graphics.getDeltaTime();
        float speed = 2f * delta * turbo;

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            player.move(-speed, 0, mapData, 1);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            player.move(speed, 0, mapData, 1);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            player.move(0, speed, mapData, 1);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            player.move(0, -speed, mapData, 1);
        }
    }

    private void draw() {
        ScreenUtils.clear(Color.BLACK);
        game.viewport.apply();

        game.batch.setProjectionMatrix(game.viewport.getCamera().combined);
        game.batch.begin();
        mapRenderer.render(game.batch);
        player.draw(game.batch);
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        game.viewport.update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
