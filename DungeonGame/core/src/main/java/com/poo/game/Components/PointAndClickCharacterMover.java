/* ------------------------------------------------------------------------------------------------
 * Licenciatura de Engenharia Informática - IADE - 2024/2025
 * ------------------------------------------------------------------------------------------------
 * Projeto : Dungeon Game (Projeto Grupo 1)
 * Disciplica : Programação e Algoritmos (LEI1A2S)
 * Professor : Nelson Costa
 * Autores : Affonso Neto | António Neto | Paulo Jadaugy | Tiago Araújo | Tomás Pereira
 * ------------------------------------------------------------------------------------------------
 */
package com.poo.game.Components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.poo.game.BaseComponents.AEntityComponent;
import com.poo.game.Interfaces.IUpdatableComponent;
import com.poo.game.Graph.MapGraph;
import com.poo.game.Graph.MapNode;

import java.util.List;

public class PointAndClickCharacterMover extends AEntityComponent implements IUpdatableComponent {
    private SpriteRendererComponent SpriteRenderer;

    private CameraComponent Camera;

    private MapGraph MapGraph;

    private List<MapNode> CurrentPath = null;

    @Override
    public void Start() {
        SpriteRenderer = AssignedEntity.GetFirstComponentOfType(SpriteRendererComponent.class);

        MapGraph = AssignedEntity.DungeonScene.GetMapGraph();

        Camera = AssignedEntity.DungeonScene.FindFirstEntityWithTag("Camera").GetFirstComponentOfType(CameraComponent.class);
    }

    private boolean AllowDebugToConsole = false;
    float Speed = 10.0f;

    @Override
    public boolean CanUpdate() {
        return IsActive;
    }

    @Override
    public void Update(float DeltaTime) {
        Vector2 MovementDirection = new Vector2();

        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && !HasPath()) {
            CurrentPath = TryFindPath();

            if (CurrentPath == null)
                return;

            if (CurrentPath.size() == 0)
                CurrentPath = null;

            if (CurrentPath != null)
                DebugPathToConsole();
        }

        if (HasPath()) {
            while (CurrentPath.size() != 0 && CurrentPath.get(0) == null)
                CurrentPath.remove(CurrentPath.size() - 1);

            Vector2 NodeLocation = new Vector2(CurrentPath.get(0).CellX, CurrentPath.get(0).CellY);
            Vector2 SpriteLocation = SpriteRenderer.GetSpritePosition();

            if (Vector2.dst(SpriteLocation.x, SpriteLocation.y, NodeLocation.x, NodeLocation.y) < 0.1f) {
                CurrentPath.remove(0);

                if (CurrentPath.size() == 0) {
                    CurrentPath = null;
                    return;
                }

                NodeLocation = new Vector2(CurrentPath.get(0).CellX, CurrentPath.get(0).CellY);
            }

            MovementDirection = NodeLocation.sub(SpriteLocation).nor();
            //Update camera position
            Camera.UpdateCameraPosition(SpriteLocation);
        }

        SpriteRenderer.AddPositionDelta(MovementDirection.scl(Speed * DeltaTime));
    }

    public List<MapNode> TryFindPath() {
        Vector2 MouseAtScreenPoint = new Vector2(Gdx.input.getX(), Gdx.input.getY());
        Vector2 MouseAtWorldPoint = Camera.DeprojectScreenPositionToWorldPosition(MouseAtScreenPoint);

        return MapGraph.SearchForPath
            (
                MapGraph.GetNode
                    (
                        // Round To Avoid Weird Floating Point Erros
                        (int) Math.round(SpriteRenderer.GetSpritePosition().x),
                        (int) Math.round(SpriteRenderer.GetSpritePosition().y)
                    ),
                MapGraph.GetNode
                    (
                        // Round To Avoid Weird Floating Point Erros
                        (int) MouseAtWorldPoint.x,
                        (int) MouseAtWorldPoint.y
                    )
            );
    }


    public void DebugPathToConsole() {
        if (!AllowDebugToConsole)
            return;

        for (int i = 0; i < CurrentPath.size(); i++) {
            System.out.println(CurrentPath.get(i).CellX + " " + CurrentPath.get(i).CellY);
        }

        System.out.println("");
    }

    public boolean HasPath() {
        return CurrentPath != null;
    }

    public boolean HasValidMovementVector(Vector2 MovementVector) {
        return MovementVector.x != 0 && MovementVector.y != 0;
    }

}
