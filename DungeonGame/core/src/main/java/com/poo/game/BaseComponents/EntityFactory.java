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

import com.poo.game.Components.*;
import com.poo.game.Components.Camera.CameraComponent;
import com.poo.game.Components.Camera.CameraManualMoverComponent;
import com.poo.game.Components.Combat.DamageComponent;
import com.poo.game.Components.Combat.HealthComponent;
import com.poo.game.Components.Movement.ManualMovementComponent;
import com.poo.game.Components.Movement.MovingToTargetComponent;
import com.poo.game.Components.Render.SpriteRendererComponent;
import com.poo.game.Entities.Entity;
import com.poo.game.Entities.Potion;
import com.poo.game.Utils.HashString;
import com.poo.game.Scene.DungeonScene;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EntityFactory {
  public static Entity CreatePlayerObject(DungeonScene scene) {
    List<Integer> EntityTags =
        new ArrayList<>(Collections.singletonList(HashString.GenerateHashFromString("Player")));
    Entity PlayerObject = new Entity(scene, "Player", EntityTags);

    PlayerObject.AddComponent(new SpriteRendererComponent("image\\player.png", 0.8f, 0.8f));
    PlayerObject.AddComponent(new HealthComponent(100, 100, true));

    PlayerObject.AddComponent(new PointAndClickCharacterMover());
    PlayerObject.AddComponent(new ManualMovementComponent());
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

  public static Entity CreateExitDoorObject(DungeonScene scene, Entity player) {
    List<Integer> EntityTags =
        new ArrayList<>(Collections.singletonList(HashString.GenerateHashFromString("ExitDoor")));
    Entity DoorObject = new Entity(scene, "ExitDoor", EntityTags);

    DoorObject.AddComponent(new SpriteRendererComponent("image\\door.png"));
    DoorObject.AddComponent(new DoorComponent(player));

    return DoorObject;
  }

  public static Entity CreateMonsterObject(DungeonScene scene, Entity player) {
    List<Integer> EntityTags =
        new ArrayList<>(Collections.singletonList(HashString.GenerateHashFromString("Monster")));
    Entity MonsterObject = new Entity(scene, "Monster", EntityTags);

    MonsterObject.AddComponent(new SpriteRendererComponent("image\\monster.png"));
    // Health of the monster
    MonsterObject.AddComponent(new HealthComponent(20, 20, false));
    // Damage done by the monster
    MonsterObject.AddComponent(new DamageComponent(5, 1));
    // Can damage the player
    MonsterObject.AddComponent(new PlayerCollisionComponent(player));

    MonsterObject.AddComponent(new MovingToTargetComponent(player));

    return MonsterObject;
  }

  public static Entity CreatePotionObject(DungeonScene scene, Entity player) {
    List<Integer> EntityTags =
        new ArrayList<>(Collections.singletonList(HashString.GenerateHashFromString("Potion")));
    Potion potionObject = new Potion(scene, EntityTags);


    potionObject.AddComponent(new SpriteRendererComponent("image/potion.png"));
    potionObject.AddComponent(new PotionComponent(player));
    potionObject.AddComponent(new PlayerCollisionComponent(player));

    return potionObject;
  }
}
