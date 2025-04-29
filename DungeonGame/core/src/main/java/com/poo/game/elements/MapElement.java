/**
 * ---------------------------------------------------------------------------- Licenciatura de
 * Engenharia Informática - IADE - 2024/2025
 * ---------------------------------------------------------------------------- Projeto : Dungeon
 * Game (Projeto Grupo 1) Disciplica : Programação e Algoritmos (LEI1A2S) Professor : Nelson Costa
 * Autores : Affonso Neto | António Neto | Paulo Jadaugy | Tomás Pereira
 * ----------------------------------------------------------------------------
 */
package com.poo.game.elements;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class MapElement {
  Vector2 size;

  // Identifier of the element
  private final String name;

  // Texture of the element in the map
  protected Texture texture;
  protected Sprite sprite;

  MapElement(String name, float x, float y, float width, float height) {
    this(null, name, x, y, width, height);
  }

  MapElement(Color color, String name, float x, float y, float width, float height) {
    this.size = new Vector2(width, height);
    this.name = name;
    if (color != null) {
      this.texture = createPlaceholderTexture(color);
    } else {
      this.texture = new Texture("image\\" + name + ".png");
    }
    this.sprite = new Sprite(texture);
    this.sprite.setSize(size.x, size.y);
    this.sprite.setPosition(x, y);
  }

  public String getName() {
    return name;
  }

  public void draw(SpriteBatch batch) {
    sprite.draw(batch);
  }

  public Sprite getSprite() {
    return sprite;
  }

  public Vector2 getPosition() {
    return new Vector2(sprite.getX(), sprite.getY());
  }

  private Texture createPlaceholderTexture(Color color) {
    Pixmap pixmap = new Pixmap(16, 16, Pixmap.Format.RGBA8888);
    pixmap.setColor(color);
    pixmap.fill();
    Texture texture = new Texture(pixmap);
    pixmap.dispose();
    return texture;
  }
}
