package com.poo.game.Components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.poo.game.Components.Camera.CameraComponent;
import com.poo.game.Components.Render.SpriteRendererComponent;
import com.poo.game.DungeonGame;
import com.poo.game.Interfaces.IUpdatableComponent;

public class ManualMovementComponent extends AMovementComponent implements IUpdatableComponent {

    private CameraComponent Camera;

    public enum ENMoveType {
        LEFT,
        RIGHT,
        UP,
        DOWN
    }

    float Speed = 7.0f;

    @Override
    public void Start() {
        super.Start();
        Camera = AssignedEntity.DungeonScene.FindFirstEntityWithTag("Camera").GetFirstComponentOfType(CameraComponent.class);
    }

    @Override
    public void Update(float DeltaTime) {
        Vector2 MoveVector = new Vector2();
        MoveVector.set(0, 0);

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            MoveVector.x = -1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            MoveVector.x = 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            MoveVector.y = 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            MoveVector.y = -1;
        }

        //should move?
        if (MoveVector.x != 0 || MoveVector.y != 0) {

            move(MoveVector.x * DeltaTime * Speed, MoveVector.y * DeltaTime * Speed);
            //Update camera position
            Camera.UpdateCameraPosition(SpriteRenderer.GetSpritePosition());
        }
    }

    @Override
    public boolean CanUpdate() {
        return IsActive;
    }
}

