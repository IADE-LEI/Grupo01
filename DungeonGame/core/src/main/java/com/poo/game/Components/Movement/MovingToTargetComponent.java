package com.poo.game.Components.Movement;

import com.badlogic.gdx.math.Vector2;
import com.poo.game.Components.Render.SpriteRendererComponent;
import com.poo.game.Entities.Entity;
import com.poo.game.Interfaces.IUpdatableComponent;

public class MovingToTargetComponent extends MovementComponent implements IUpdatableComponent {

    private final Entity target;

    float Speed = 1.0f;

    public MovingToTargetComponent(Entity target) {
        this.target = target;
    }


    @Override
    public void Update(float DeltaTime) {

        //Current target position
        SpriteRendererComponent targetSpriteRender = target.GetFirstComponentOfType(SpriteRendererComponent.class);

        //Check if the entity can see the target in a line
        if (CanSeeTarget(SpriteRenderer.GetSpritePosition(), targetSpriteRender.GetSpritePosition())) {
            Vector2 direction = new Vector2(targetSpriteRender.GetSpritePosition()).sub(SpriteRenderer.GetSpritePosition()).nor();

            // Determine which axis has greater distance
            if (Math.abs(direction.x) > Math.abs(direction.y)) {
                // Move horizontally (left or right)
                direction = new Vector2(Math.signum(direction.x), 0);
            } else {
                // Move vertically (up or down)
                direction = new Vector2(0, Math.signum(direction.y));
            }

            move(direction.x * DeltaTime * Speed, direction.y * DeltaTime * Speed);
        }
    }

    @Override
    public boolean CanUpdate() {
        return IsActive;
    }

    public boolean CanSeeTarget(Vector2 entityPos, Vector2 targetPos) {
        int startX = Math.round(entityPos.x);
        int startY = Math.round(entityPos.y);
        int endX = Math.round(targetPos.x);
        int endY = Math.round(targetPos.y);

        // Bresenham's line algorithm
        int dx = Math.abs(endX - startX);
        int dy = Math.abs(endY - startY);
        int sx = startX < endX ? 1 : -1;
        int sy = startY < endY ? 1 : -1;
        int err = dx - dy;

        while (true) {
            // Check current tile

            if (AssignedEntity.DungeonScene.Map.getTile(startX, startY) == null) {
                return false;
            }

            // If we've reached the end point
            if (startX == endX && startY == endY) {
                break;
            }

            // Calculate doubled error term
            int e2 = 2 * err;

            // If error is greater than negative dy, move in x direction
            if (e2 > -dy) {
                err -= dy;
                startX += sx;
            }

            // If error is less than dx, move in y direction
            if (e2 < dx) {
                err += dx;
                startY += sy;
            }
        }
        return true;
    }

}

