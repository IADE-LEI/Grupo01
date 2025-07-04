/* ------------------------------------------------------------------------------------------------
 * Licenciatura de Engenharia Informática - IADE - 2024/2025
 * ------------------------------------------------------------------------------------------------
 * Projeto : Dungeon Game (Projeto Grupo 1)
 * Disciplica : Programação e Algoritmos (LEI1A2S)
 * Professor : Nelson Costa
 * Autores : Affonso Neto | António Neto | Paulo Jadaugy | Tiago Araújo | Tomás Pereira
 * ------------------------------------------------------------------------------------------------
 */
package com.poo.game.Components.Camera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.poo.game.BaseComponents.EntityComponent;
import com.poo.game.Interfaces.IUpdatableComponent;
import com.poo.game.DungeonGame;

public class CameraManualMoverComponent extends EntityComponent implements IUpdatableComponent
{
    CameraComponent CameraToMove;

    private final float MovementSpeed = 50.0f;
    private final float ZoomSpeed = 0.02f;

    @Override // Gotta tell java to override :p
    public void Start()
    {
        // Let's Find The Other Component
        CameraToMove = AssignedEntity.GetFirstComponentOfType(CameraComponent.class);

        //CameraToMove.RenderCamera.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);
        //CameraToMove.RenderCamera.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);
        CameraToMove.RenderCamera.zoom = MathUtils.clamp(CameraToMove.RenderCamera.zoom * .25f, 0.1f, 100/CameraToMove.RenderCamera.viewportWidth);
        CameraToMove.RenderCamera.update();
    }

    @Override
    public void Update(float DeltaTime)
    {
        Vector2 MovementDirection = new Vector2();

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            MovementDirection.x = -1;

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            MovementDirection.x =  1;

        if (Gdx.input.isKeyPressed(Input.Keys.UP))
            MovementDirection.y =  1;

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
            MovementDirection.y = -1;

        // Zoom Related Shenanigans
        if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_ADD))
            CameraToMove.RenderCamera.zoom += ZoomSpeed;

        if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_SUBTRACT))
            CameraToMove.RenderCamera.zoom -= ZoomSpeed;

        // Apply Zoom
        CameraToMove.RenderCamera.zoom = MathUtils.clamp(CameraToMove.RenderCamera.zoom, 0.1f, 100/CameraToMove.RenderCamera.viewportWidth);

        float effectiveViewportWidth = CameraToMove.RenderCamera.viewportWidth * CameraToMove.RenderCamera.zoom;
        float effectiveViewportHeight = CameraToMove.RenderCamera.viewportHeight * CameraToMove.RenderCamera.zoom;

        CameraToMove.RenderCamera.translate(MovementDirection.nor().scl(MovementSpeed * DeltaTime));

        CameraToMove.RenderCamera.position.x = MathUtils.clamp(CameraToMove.RenderCamera.position.x, effectiveViewportWidth / 2f, DungeonGame.worldWidth - effectiveViewportWidth / 2f);
        CameraToMove.RenderCamera.position.y = MathUtils.clamp(CameraToMove.RenderCamera.position.y, effectiveViewportHeight / 2f, DungeonGame.worldHeight - effectiveViewportHeight / 2f);

        CameraToMove.RenderCamera.update();
    }

    @Override
    public boolean CanUpdate()
    {
        return IsActive;
    }
}
