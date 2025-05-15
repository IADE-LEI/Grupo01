/* ------------------------------------------------------------------------------------------------
 * Licenciatura de Engenharia Informática - IADE - 2024/2025
 * ------------------------------------------------------------------------------------------------
 * Projeto : Dungeon Game (Projeto Grupo 1)
 * Disciplica : Programação e Algoritmos (LEI1A2S)
 * Professor : Nelson Costa
 * Autores : Affonso Neto | António Neto | Paulo Jadaugy | Tiago Araújo | Tomás Pereira
 * ------------------------------------------------------------------------------------------------
 */
package com.poo.game.Components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class SpriteComponent extends Component {
  String imageName;
  Vector2 size;

  protected Texture texture;
  protected Sprite sprite;

  public SpriteComponent(Color color, float x, float y, float width, float height) {
    this.size = new Vector2(width, height);
    Pixmap pm = new Pixmap(16, 16, Pixmap.Format.RGBA8888);
    pm.setColor(color);
    pm.fill();
    this.texture = new Texture(pm);
    pm.dispose();
    this.sprite = new Sprite(texture);
    this.sprite.setSize(size.x, size.y);
    this.sprite.setPosition(x, y);
  }

  public SpriteComponent(String imageName, float x, float y, float width, float height) {
    this.size = new Vector2(width, height);
    this.imageName = imageName;
    this.texture = new Texture("image\\" + imageName);
    this.sprite = new Sprite(texture);
    this.sprite.setSize(size.x, size.y);
    this.sprite.setPosition(x, y);
  }

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
