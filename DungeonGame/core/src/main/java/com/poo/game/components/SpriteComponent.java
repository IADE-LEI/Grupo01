/* ------------------------------------------------------------------------------------------------
 * Licenciatura de Engenharia Informática - IADE - 2024/2025
 * ------------------------------------------------------------------------------------------------
 * Projeto : Dungeon Game (Projeto Grupo 1)
 * Disciplica : Programação e Algoritmos (LEI1A2S)
 * Professor : Nelson Costa
 * Autores : Affonso Neto | António Neto | Paulo Jadaugy | Tiago Araújo | Tomás Pereira
 * ------------------------------------------------------------------------------------------------
 */
package com.poo.game.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class SpriteComponent extends Component {
  Vector2 size;

  protected Texture texture;
  protected Sprite sprite;

  public Sprite getSprite() {
    return sprite;
  }

  public Vector2 getSize() {
    return size;
  }

  public Vector2 getPosition() {
    return new Vector2(sprite.getX(), sprite.getY());
  }

  public void draw(SpriteBatch batch) {
    sprite.draw(batch);
  }
}
