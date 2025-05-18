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
import com.poo.game.BaseComponents.EntityFactory;
import com.poo.game.Components.CameraComponent;
import com.poo.game.Entities.Entity;
import com.poo.game.Map.BSPMapGenerator;
import com.poo.game.Map.MapData;

import java.util.ArrayList;

import static com.poo.game.DungeonGame.worldHeight;
import static com.poo.game.DungeonGame.worldWidth;

public class DungeonScene {

  SpriteBatch spriteBatch;

  ArrayList<Entity> SceneEntities = new ArrayList<>();
  ArrayList<CameraComponent> Cameras = new ArrayList<>();

  Texture GridLayoutSprite;
  public MapData Map;

  public void CreateWorld() {
    BSPMapGenerator generator = new BSPMapGenerator(worldWidth, worldHeight, 5, 10, 4);
    Map = generator.generate();

    // Debug The Grid
    GridLayoutSprite = (new Texture("image/GridLayout.png"));

    spriteBatch = new SpriteBatch();

    SceneEntities.add(EntityFactory.CreateCameraObject(this));
    SceneEntities.add(EntityFactory.CreatePlayerObject(this));

    Cameras.add(SceneEntities.get(0).GetFirstComponentOfType(CameraComponent.class));

    // Start each of the entities
    for (int i = 0; i < SceneEntities.size(); ++i) {
      SceneEntities.get(i).Start();
    }
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
    //        for (int Y = 0; Y < worldHeight; ++Y) {
    //            for (int X = 0; X < worldWidth; ++X) {
    //                spriteBatch.draw(GridLayoutSprite, X, Y, 1, 1);
    //            }
    //        }

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
}
