/* ------------------------------------------------------------------------------------------------
 * Licenciatura de Engenharia Informática - IADE - 2024/2025
 * ------------------------------------------------------------------------------------------------
 * Projeto : Dungeon Game (Projeto Grupo 1)
 * Disciplica : Programação e Algoritmos (LEI1A2S)
 * Professor : Nelson Costa
 * Autores : Affonso Neto | António Neto | Paulo Jadaugy | Tiago Araújo | Tomás Pereira
 * ------------------------------------------------------------------------------------------------
 */
package com.poo.game.Scene;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.poo.game.BaseComponents.EntityFactory;
import com.poo.game.Components.Camera.CameraComponent;
import com.poo.game.Components.Render.SpriteRendererComponent;
import com.poo.game.Entities.Entity;
import com.poo.game.Entities.Potion;
import com.poo.game.Interfaces.IPotionConsumedEvent;
import com.poo.game.Map.MapGraph;
import com.poo.game.Map.MapNode;
import com.poo.game.Map.BSPMapGenerator;
import com.poo.game.Map.MapData;
import com.poo.game.Map.Room;
import com.poo.game.System.HealthRenderSystem;

import java.util.ArrayList;

import static com.poo.game.DungeonGame.*;

public class DungeonScene implements IPotionConsumedEvent {

  SpriteBatch spriteBatch;

  ArrayList<Entity> SceneEntities;
  ArrayList<CameraComponent> Cameras;

  Texture GridLayoutSprite;
  public MapData Map;

  public void CreateWorld() {
    SceneEntities = new ArrayList<>();
    Cameras = new ArrayList<>();

    BSPMapGenerator generator = new BSPMapGenerator(worldWidth, worldHeight, 5, 10, 4);
    Map = generator.generate();

    // Debug The Grid
    GridLayoutSprite = (new Texture("image/GridLayout.png"));

    spriteBatch = new SpriteBatch();

    Entity camera = EntityFactory.CreateCameraObject(this);
    Entity player = EntityFactory.CreatePlayerObject(this);
    Entity exitDoor = EntityFactory.CreateExitDoorObject(this, player);

    SceneEntities.add(camera);
    SceneEntities.add(player);
    SceneEntities.add(exitDoor);
    ArrayList<Room> usedRooms = new ArrayList<>();

    for (int i = 0; i < MaxNrPotions; ++i) {
      Potion potion = (Potion) EntityFactory.CreatePotionObject(this, player);
      SpriteRendererComponent potionSprite =
          potion.GetFirstComponentOfType(SpriteRendererComponent.class);
      MapNode node = Map.MapGraph.GetRandomNodeInRoom();
      while (usedRooms.contains(node.GetRoom())) node = Map.MapGraph.GetRandomNodeInRoom();

      usedRooms.add(node.GetRoom());
      potionSprite.SpriteToRender.setPosition(node.GetCellX(), node.GetCellY());

      potion.addListener((entity) -> SceneEntities.remove(entity));

      SceneEntities.add(potion);
    }

    usedRooms = new ArrayList<>();

    for (int i = 0; i < MaxNrMonsters; ++i) {
      Entity Monster = EntityFactory.CreateMonsterObject(this, player);
      SpriteRendererComponent monsterSprite =
          Monster.GetFirstComponentOfType(SpriteRendererComponent.class);
      MapNode node = Map.MapGraph.GetRandomNodeInRoom();
      while (usedRooms.contains(node.GetRoom())) node = Map.MapGraph.GetRandomNodeInRoom();

      usedRooms.add(node.GetRoom());
      monsterSprite.SpriteToRender.setPosition(node.GetCellX(), node.GetCellY());
      SceneEntities.add(Monster);
    }

    CameraComponent cameraComponent = camera.GetFirstComponentOfType(CameraComponent.class);
    Cameras.add(cameraComponent);

    // Start each of the entities
    for (int i = 0; i < SceneEntities.size(); ++i) {
      SceneEntities.get(i).Start();
    }

    SpriteRendererComponent playerSprite =
        player.GetFirstComponentOfType(SpriteRendererComponent.class);

    // Set initial player position
    Vector2 startingPosition = Map.MapGraph.GetFirstNode().GetPosition();
    playerSprite.SetPosition(startingPosition);
    cameraComponent.SetCameraPosition(startingPosition);

    SpriteRendererComponent doorSprite =
        exitDoor.GetFirstComponentOfType(SpriteRendererComponent.class);
    Room exitRoom = Map.GetExitRoom();

    // Adds exit door position on that room
    doorSprite.SetPosition(GetExitDoorPosition(exitRoom));

    // Add path so player can go into the door
    MapNode node = new MapNode(exitRoom.getCenterX(), exitRoom.getTopY(), exitRoom);
    MapNode previousNode = Map.MapGraph.GetNode(exitRoom.getCenterX(), exitRoom.getTopY() - 1);
    node.AddTwoWayConnectionTo(previousNode);
    Map.MapGraph.AddNode(node);
  }

  public void RenderWorld() {
    // Draw your screen here. "delta" is the time since last render in seconds.

    // Prepare View Matrix To Draw And Sprite Batch
    spriteBatch.setProjectionMatrix(Cameras.get(0).RenderCamera().combined);
    spriteBatch.begin();

    // Should Be A Sprite Batch Per Layer Or Order Them

    // The Order You Draw This In Is Important!
    Map.RenderMap(spriteBatch);

    // spriteBatch.draw(new Texture("image\\player.png"), 0, 0, worldWidth, worldHeight); // draw
    // the background

    for (int i = 0; i < SceneEntities.size(); ++i) {
      SceneEntities.get(i).Render(spriteBatch);
    }

    // Debug Sprite
    for (int Y = 0; Y < worldHeight; ++Y) {
      for (int X = 0; X < worldWidth; ++X) {
        spriteBatch.draw(GridLayoutSprite, X, Y, 1, 1);
      }
    }
    Entity player = FindFirstEntityWithTag("Player");
    HealthRenderSystem.RenderHUD(player, spriteBatch);
    spriteBatch.end();
  }

  public void DestroyWorld() {}

  public Entity FindFirstEntityWithTag(String Tag) {
    int HashedTag = Tag.hashCode();
    for (int i = 0; i < SceneEntities.size(); ++i) {
      Entity CurrentEntity = SceneEntities.get(i);
      for (int TagIndex = 0; TagIndex < CurrentEntity.EntityTags.size(); ++TagIndex) {
        if (CurrentEntity.EntityTags.get(TagIndex) == HashedTag) {
          return CurrentEntity;
        }
      }
    }
    return null;
  }

  public void UpdateWorld(float DeltaTime) {
    for (int i = 0; i < SceneEntities.size(); ++i) {
      SceneEntities.get(i).Update(DeltaTime);
    }
  }

  public void HandleInteractions() {
    for (int i = 0; i < SceneEntities.size(); ++i) {
      SceneEntities.get(i).Interact();
    }
  }

  public MapGraph GetMapGraph() {
    return Map.MapGraph;
  }

  public CameraComponent GetDefaultCameraComponent() {
    if (!Cameras.isEmpty()) return Cameras.get(0);
    return null;
  }

  private Vector2 GetExitDoorPosition(Room room) {
    Vector2 exitDoorPosition = new Vector2();
    if (Map.getTile(room.getCenterX(), room.getTopY()) == null) {
      exitDoorPosition.x = room.getCenterX();
      exitDoorPosition.y = room.getTopY();
    } else if (Map.getTile(room.getEndX(), room.getCenterY()) == null) {
      exitDoorPosition.x = room.getEndX();
      exitDoorPosition.y = room.getCenterY();
    } else if (Map.getTile(room.getCenterX(), room.getY()) == null) {
      exitDoorPosition.x = room.getCenterX();
      exitDoorPosition.y = room.getY();
    } else if (Map.getTile(room.getX(), room.getCenterY()) == null) {
      exitDoorPosition.x = room.getX();
      exitDoorPosition.y = room.getCenterY();
    }

    return exitDoorPosition;
  }

  public void RemoveEntity(Entity entity) {
    SceneEntities.remove(entity);
  }

  @Override
  public void potionConsumed(Entity e) {}
}
