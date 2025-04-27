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
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.poo.game.DungeonGame;

public class EntryScreen extends BaseScreen {
    Stage stage;

    public EntryScreen(final DungeonGame game) {
        super(game);

        stage = new Stage();
        stage.addListener(new InputListener() {
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
    }

    @Override
    public void render(float delta) {
        draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    private void draw() {
        ScreenUtils.clear(Color.BLACK);

        game.viewport.apply();
        game.batch.setProjectionMatrix(game.viewport.getCamera().combined);

        game.batch.begin();

        game.font.draw(game.batch, "Welcome to the Dungeon Game ", 10, 16);
        game.font.draw(game.batch, "Play (F2)", 10, 13);
        game.font.draw(game.batch, "Options (F10)", 10, 12);
        game.font.draw(game.batch, "Exit (ESC)", 10, 11);

        game.batch.end();
    }
}
