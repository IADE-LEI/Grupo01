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

package com.poo.game.elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class MapElement {
    Vector2 position;
    Vector2 size;

    // Identifier of the element
    private final String textureName;
    private final String name;

    //Texture of the element in the map
    protected Texture texture;
    protected Sprite sprite;


    MapElement(String textureName, String name, float x, float y, float width, float height) {
        this.position = new Vector2(x, y);
        this.size = new Vector2(width, height);

        this.textureName = textureName;
        this.name = name;
        this.texture = new Texture("image\\" + textureName + ".png");

        this.sprite = new Sprite(texture);
        this.sprite.setSize(size.x, size.y);
        this.sprite.setPosition(position.x, position.y);

    }

    public String getTextureName() {
        return textureName;
    }

    public String getName() {
        return name;
    }

    public void setPosition(float posX, float posY) {
        this.position = new Vector2(posX, posY);
    }

    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
        //batch.draw(texture, position.x,position.y, size.x, size.y);
    }

    public Sprite getSprite() {
        return sprite;
    }

    public Vector2 getPosition() {
        return position;
    }

}
