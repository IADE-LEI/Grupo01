package com.poo.game.Components;

import com.badlogic.gdx.math.Vector2;
import com.poo.game.BaseComponents.AEntityComponent;
import com.poo.game.Components.Render.SpriteRendererComponent;
import com.poo.game.Entities.Entity;
import com.poo.game.Graph.MapNode;
import com.poo.game.Interfaces.IUpdatableComponent;

import java.util.List;

public class MovingToTargetComponent extends AEntityComponent implements IUpdatableComponent {

    private SpriteRendererComponent SpriteRenderer;

    private final Entity target;

    float Speed = 1.0f;

    private List<MapNode> CurrentPath;
    private MapNode targetNode;

    public MovingToTargetComponent(Entity target) {
        this.target = target;
    }

    @Override
    public void Start() {
        super.Start();

        SpriteRenderer = AssignedEntity.GetFirstComponentOfType(SpriteRendererComponent.class);
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

            float newX = SpriteRenderer.SpriteToRender.getX() + direction.x;
            float newY = SpriteRenderer.SpriteToRender.getY() + direction.y;

            if (AssignedEntity.DungeonScene.Map.CanMoveTo(SpriteRenderer, newX, newY))
                SpriteRenderer.AddPositionDelta(direction.scl(Speed * DeltaTime));

        }
    }

    @Override
    public boolean CanUpdate() {
        return IsActive;
    }

    public boolean HasPath() {
        return CurrentPath != null;
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

            if (AssignedEntity.DungeonScene.GetMapGraph().GetNode(startX, startY) == null) {
                return false;
            }

            if (startX == endX && startY == endY) {
                break;
            }

            int e2 = 2 * err;
            if (e2 > -dy) {
                err -= dy;
                startX += sx;
            }
            if (e2 < dx) {
                err += dx;
                startY += sy;
            }
        }
        return true;
    }
}

