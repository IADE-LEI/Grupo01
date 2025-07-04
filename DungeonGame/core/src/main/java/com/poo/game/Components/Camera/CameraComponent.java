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

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.poo.game.BaseComponents.EntityComponent;

public class CameraComponent extends EntityComponent
{
    OrthographicCamera RenderCamera;

    // Smoothing factor (0.1 = 10% per frame)
    float cameraLerp = 0.1f;

    @Override
    public void Start()
    {
        RenderCamera = new OrthographicCamera(80, 45);
    }

    public OrthographicCamera RenderCamera()
    {
        return RenderCamera;
    }

    public Vector2 DeprojectScreenPositionToWorldPosition(Vector2 ScreenPoint)
    {
        Vector3 worldCoords = RenderCamera().unproject(new Vector3(ScreenPoint.x, ScreenPoint.y, 0));
        return new Vector2(worldCoords.x, worldCoords.y);
    }

    public void UpdateCameraPosition(Vector2 ScreenPoint){
        // Apply smoothing
        RenderCamera.position.lerp(new Vector3(ScreenPoint.x, ScreenPoint.y, 0), cameraLerp);
        RenderCamera.update();
    }

    public void SetCameraPosition(Vector2 ScreenPoint){
        // Apply smoothing
        RenderCamera.position.set(ScreenPoint.x, ScreenPoint.y, 0);
        RenderCamera.update();
    }

    public Vector2 ProjectWorldPositionToScreenPosition(Vector2 WorldPoint)
    {
        Vector3 ScreenPosition = RenderCamera().project(new Vector3(WorldPoint.x, WorldPoint.y, 0));
        return new Vector2(ScreenPosition.x, ScreenPosition.y);
    }
}
