/* ------------------------------------------------------------------------------------------------
 * Licenciatura de Engenharia Informática - IADE - 2024/2025
 * ------------------------------------------------------------------------------------------------
 * Projeto : Dungeon Game (Projeto Grupo 1)
 * Disciplica : Programação e Algoritmos (LEI1A2S)
 * Professor : Nelson Costa
 * Autores : Affonso Neto | António Neto | Paulo Jadaugy | Tiago Araújo | Tomás Pereira
 * ------------------------------------------------------------------------------------------------
 */
package com.poo.game.Map;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.poo.game.DungeonGame;

/** class for map manipulation (ECS - System) */
public class MapSystem {
  private MapData mapData;

  public enum ENMoveType {
    LEFT,
    RIGHT,
    UP,
    DOWN
  }

  public MapSystem(MapData mapData) {
    this.mapData = mapData;
  }

  public void update(DungeonGame game) {
    // clear screen and update batch
    //        ScreenUtils.clear(Color.BLACK);
    //        game.viewport.apply();
    //
    //        game.batch.setProjectionMatrix(game.viewport.getCamera().combined);
    //        game.batch.enableBlending();
    //        game.batch.begin();
    //
    //        // draw on SpritComponents of map tiles
    //        for (int x = 0; x < mapData.getWidth(); x++) {
    //            for (int y = 0; y < mapData.getHeight(); y++) {
    //                List<AEntityComponent> tileComponents = mapData.getTile(x, y).getComponents();
    //                if (tileComponents != null) {
    //                    for (int c = 0; c < tileComponents.size(); c++) {
    //                        if (tileComponents.get(c) instanceof SpriteComponent) {
    //                            ((SpriteComponent) tileComponents.get(c)).draw(game.batch);
    //                        }
    //                    }
    //                }
    //            }
    //        }
    //
    //        // draw on SpritComponents of player
    //        Array<Component> pc = mapData.getPlayer().getComponents();
    //        if (pc != null) {
    //            for (int c = 0; c < pc.size; c++) {
    //                if (pc.get(c) instanceof SpriteComponent) {
    //                    ((SpriteComponent) pc.get(c)).draw(game.batch);
    //                }
    //            }
    //        }
    //
    //        game.batch.end();
  }

  public void movePlayer(ENMoveType moveType, float speed) {
    switch (moveType) {
      case LEFT:
        move(-speed, 0);
        break;
      case RIGHT:
        move(speed, 0);
        break;
      case UP:
        move(0, speed);
        break;
      case DOWN:
        move(0, -speed);
        break;
    }
  }

  public void dispose() {
    mapData = null;
  }

  private void move(float deltaX, float deltaY) {
    // Ensure player doesn't get out of the map
    Sprite mySprite = getPlayerSprite();
    mySprite.setX(MathUtils.clamp(mySprite.getX(), 0, mapData.getWidth() - mySprite.getWidth()));
    mySprite.setY(MathUtils.clamp(mySprite.getY(), 0, mapData.getHeight() - mySprite.getHeight()));

    float newX = mySprite.getX() + deltaX;
    float newY = mySprite.getY() + deltaY;

    if (canMoveTo(newX, mySprite.getY())) {
      mySprite.setX(newX);
    }

    if (canMoveTo(mySprite.getX(), newY)) {
      mySprite.setY(newY);
    }
  }

  private boolean canMoveTo(float newX, float newY) {
    // Create temporary rectangle for proposed position
    Sprite mySprite = getPlayerSprite();
    Rectangle tempRect = new Rectangle(newX, newY, mySprite.getWidth(), mySprite.getHeight());

    // Check each tile the player would overlap
    int startX = (int) (newX);
    int startY = (int) (newY);
    int endX = (int) ((newX + mySprite.getWidth()));
    int endY = (int) ((newY + mySprite.getHeight()));

    for (int x = startX; x <= endX; x++) {
      for (int y = startY; y <= endY; y++) {
        // Can't pass the tile
        // TODO: review collision
        /*if (!(mapData.getTile(x, y) instanceof FloorEntity)) {
            Rectangle wallRect = new Rectangle(x, y, 1, 1);
            if (tempRect.overlaps(wallRect)) {
                return false;
            }
        }*/
      }
    }
    return true;
  }

  private Sprite getPlayerSprite() {
    // all components of player
    //        mapData.
    //        Array<Component> components = mapData.getPlayer().getComponents();
    //        for (int x = 0; x < components.size; x++) {
    //            if (components.get(x) instanceof SpriteComponent) {
    //                return ((SpriteComponent) components.get(x)).getSprite();
    //            }
    //        }
    return null;
  }
}
