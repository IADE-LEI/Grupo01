package com.poo.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;
import com.poo.game.DungeonGame;

public class EntryScreen implements Screen {

    final DungeonGame game;

    public EntryScreen(final DungeonGame game) {
        this.game = game;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);

        game.viewport.apply();
        game.batch.setProjectionMatrix(game.viewport.getCamera().combined);

        game.batch.begin();

        //Draw entry message.
        game.font.draw(game.batch, "Welcome to the Dungeon Game ", 15, 10.5f);
        game.font.draw(game.batch, "Tap anywhere to begin!", 15, 10);
        game.batch.end();

        if (Gdx.input.isTouched()) {
            game.setScreen(new DummyScreen(game));
            dispose();
        }
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
