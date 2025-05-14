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
import java.util.List;

public class EntityFactory {
    public static Entity CreatePlayerObject(DungeonScene MapSystem) {
        List<Integer> EntityTags = new ArrayList<Integer>(Arrays.asList(HashString.GenerateHashFromString("Player")));
        Entity PlayerObject = new Entity(MapSystem, "Player", EntityTags);

        PlayerObject.AddComponent(new SpriteRendererComponent("image\\player.png"));
        PlayerObject.AddComponent(new PointAndClickCharacterMover());

        return PlayerObject;
    }

    public static Entity CreateCameraObject(DungeonScene MapSystem) {
        List<Integer> EntityTags = new ArrayList<Integer>(Arrays.asList(HashString.GenerateHashFromString("Camera")));
        Entity CameraEntity = new Entity(MapSystem, "Camera", EntityTags);

        CameraEntity.AddComponent(new CameraComponent());
        CameraEntity.AddComponent(new CameraManualMoverComponent());

        return CameraEntity;
    }
}
