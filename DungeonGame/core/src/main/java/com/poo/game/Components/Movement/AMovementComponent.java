package com.poo.game.Components.Movement;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.poo.game.BaseComponents.AEntityComponent;
import com.poo.game.Components.Render.SpriteRendererComponent;
import com.poo.game.DungeonGame;

public class AMovementComponent extends AEntityComponent {

    protected SpriteRendererComponent SpriteRenderer;

    @Override
    public void Start() {
        super.Start();
        SpriteRenderer = AssignedEntity.GetFirstComponentOfType(SpriteRendererComponent.class);
    }

    protected void move(float deltaX, float deltaY) {
        Sprite sprite = SpriteRenderer.SpriteToRender;

        // Ensure player doesn't get out of the map
        sprite.setX(MathUtils.clamp(sprite.getX(), 0, DungeonGame.worldWidth - sprite.getWidth()));
        sprite.setY(MathUtils.clamp(sprite.getY(), 0, DungeonGame.worldHeight - sprite.getHeight()));

        float newX = sprite.getX() + deltaX;
        float newY = sprite.getY() + deltaY;

        if (CanMoveTo(newX, sprite.getY())) {
            sprite.setX(newX);
        }

        if (CanMoveTo(sprite.getX(), newY)) {
            sprite.setY(newY);
        }
    }

    private boolean CanMoveTo(float newX, float newY) {
        Sprite sprite = SpriteRenderer.SpriteToRender;
        // Create temporary rectangle for proposed position
        Rectangle tempRect = new Rectangle(newX, newY, sprite.getWidth(), sprite.getHeight());

        // Check each tile the player would overlap
        int startX = (int) (newX);
        int startY = (int) (newY);
        int endX = (int) ((newX + sprite.getWidth()));
        int endY = (int) ((newY + sprite.getHeight()));

        for (int x = startX; x <= endX; x++) {
            for (int y = startY; y <= endY; y++) {
                // Can't pass the tile
                if ((AssignedEntity.DungeonScene.Map.getTile(x, y) == null)) {
                    Rectangle wallRect = new Rectangle(x, y, 1, 1);
                    if (tempRect.overlaps(wallRect))
                        return false;

                }
            }
        }

        return true;
    }
}
