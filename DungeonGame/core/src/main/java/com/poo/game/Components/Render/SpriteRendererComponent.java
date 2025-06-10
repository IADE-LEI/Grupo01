/* ------------------------------------------------------------------------------------------------
 * Licenciatura de Engenharia Informática - IADE - 2024/2025
 * ------------------------------------------------------------------------------------------------
 * Projeto : Dungeon Game (Projeto Grupo 1)
 * Disciplica : Programação e Algoritmos (LEI1A2S)
 * Professor : Nelson Costa
 * Autores : Affonso Neto | António Neto | Paulo Jadaugy | Tiago Araújo | Tomás Pereira
 * ------------------------------------------------------------------------------------------------
 */
package com.poo.game.Components.Render;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.poo.game.BaseComponents.EntityComponent;
import com.poo.game.Interfaces.IRenderableComponent;

public class SpriteRendererComponent extends EntityComponent implements IRenderableComponent {
    public Sprite SpriteToRender;
    private final float sizeX;
    private final float sizeY;

    public SpriteRendererComponent(String SpriteToRenderPath) {
        SpriteToRender = new Sprite(new Texture(SpriteToRenderPath));
        sizeX = 1;
        sizeY = 1;
        SpriteToRender.setSize(sizeX, sizeY);
    }

    public SpriteRendererComponent(String SpriteToRenderPath, float sizeX, float sizeY) {
        SpriteToRender = new Sprite(new Texture(SpriteToRenderPath));
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        SpriteToRender.setSize(sizeX, sizeY);

    }

    @Override
    public boolean CanRender() {
        return IsActive;
    }

    @Override
    public void Render(SpriteBatch Batch) {
        Batch.draw(SpriteToRender, SpriteToRender.getX(), SpriteToRender.getY(), sizeX, sizeY);
    }

    public Vector2 GetSpritePosition() {
        return new Vector2(SpriteToRender.getX(), SpriteToRender.getY());
    }

    public void AddPositionDelta(Vector2 delta) {
        SpriteToRender.translate(delta.x, delta.y);
    }

    public void SetPosition(Vector2 Position) {
        SpriteToRender.setPosition(Position.x, Position.y);
    }
}
