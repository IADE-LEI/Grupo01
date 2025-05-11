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

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

/** Component to work with Sprinte (ECS - Component) */
public class SpriteColorComponent extends SpriteComponent {

  public SpriteColorComponent(Color color, float x, float y, float width, float height) {
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
}
