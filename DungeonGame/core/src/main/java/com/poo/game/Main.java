/* ------------------------------------------------------------------------------------------------
 * Licenciatura de Engenharia Informática - IADE - 2024/2025
 * ------------------------------------------------------------------------------------------------
 * Projeto : Dungeon Game (Projeto Grupo 1)
 * Disciplica : Programação e Algoritmos (LEI1A2S)
 * Professor : Nelson Costa
 * Autores : Affonso Neto | António Neto | Paulo Jadaugy | Tiago Araújo | Tomás Pereira
 * ------------------------------------------------------------------------------------------------
 */
package com.poo.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
  private SpriteBatch batch;
  private Texture image;

  @Override
  public void create() {
    batch = new SpriteBatch();
    image = new Texture("image\\libgdx.png");
  }

  @Override
  public void render() {
    ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
    batch.begin();
    batch.draw(image, 140, 210);
    batch.end();
  }

  @Override
  public void dispose() {
    batch.dispose();
    image.dispose();
  }
}
