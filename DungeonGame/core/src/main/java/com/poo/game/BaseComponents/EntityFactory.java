/* ------------------------------------------------------------------------------------------------
 * Licenciatura de Engenharia Informática - IADE - 2024/2025
 * ------------------------------------------------------------------------------------------------
 * Projeto : Dungeon Game (Projeto Grupo 1)
 * Disciplica : Programação e Algoritmos (LEI1A2S)
 * Professor : Nelson Costa
 * Autores : Affonso Neto | António Neto | Paulo Jadaugy | Tiago Araújo | Tomás Pereira
 * ------------------------------------------------------------------------------------------------
 */
package com.poo.game.BaseComponents;

import com.poo.game.Components.CameraComponent;
import com.poo.game.Components.CameraManualMoverComponent;
import com.poo.game.Components.PointAndClickCharacterMover;
import com.poo.game.Components.SpriteRendererComponent;
import com.poo.game.Entities.Entity;
import com.poo.game.Utils.HashString;
import com.poo.game.Scene.DungeonScene;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class EntityFactory {
  public static Entity CreatePlayerObject(DungeonScene MapSystem) {
    List<Integer> EntityTags =
        new ArrayList<>(Collections.singletonList(HashString.GenerateHashFromString("Player")));
    Entity PlayerObject = new Entity(MapSystem, "Player", EntityTags);

    PlayerObject.AddComponent(new SpriteRendererComponent("image\\player.png"));
    PlayerObject.AddComponent(new PointAndClickCharacterMover());

    return PlayerObject;
  }

  public static Entity CreateCameraObject(DungeonScene MapSystem) {
    List<Integer> EntityTags =
        new ArrayList<>(Collections.singletonList(HashString.GenerateHashFromString("Camera")));
    Entity CameraEntity = new Entity(MapSystem, "Camera", EntityTags);

    CameraEntity.AddComponent(new CameraComponent());
    CameraEntity.AddComponent(new CameraManualMoverComponent());

    return CameraEntity;
  }
}
