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

import com.badlogic.gdx.math.MathUtils;

public class BSPNode {
  private final int x;
  private final int y;
  private final int width;
  private final int height;
  private final int minRoomSize;
  private BSPNode left, right;
  private Room room;

  public BSPNode(int x, int y, int width, int height, int minRoomSize) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.minRoomSize = minRoomSize;
  }

  public boolean split() {
    if (left != null || right != null) return false;

    boolean splitH = determineSplitDirection();

    int max = (splitH ? height : width) - minRoomSize;
    if (max <= minRoomSize) return false;

    int split = minRoomSize + (int) (Math.random() * (max - minRoomSize));

    if (splitH) {
      left = new BSPNode(x, y, width, split, minRoomSize);
      right = new BSPNode(x, y + split, width, height - split, minRoomSize);
    } else {
      left = new BSPNode(x, y, split, height, minRoomSize);
      right = new BSPNode(x + split, y, width - split, height, minRoomSize);
    }

    return true;
  }

  private boolean determineSplitDirection() {
    if (width > height && (float) (width / height) >= 1.25) {
      return false; // Split vertically
    } else if (height > width && (float) (height / width) >= 1.25) {
      return true; // Split horizontally
    }
    return Math.random() > 0.5;
  }

  public void createRoom(int maxRoomSize, int mapWidth, int mapHeight) {
    if (width < minRoomSize || height < minRoomSize) {
      return; // Node too small for a room
    }

    // Ensure room size is within bounds
    int roomWidth =
        MathUtils.clamp(
            minRoomSize + (int) (Math.random() * (maxRoomSize - minRoomSize + 1)),
            minRoomSize,
            width - 2 // Leave at least 1 tile border
            );

    int roomHeight =
        MathUtils.clamp(
            minRoomSize + (int) (Math.random() * (maxRoomSize - minRoomSize + 1)),
            minRoomSize,
            height - 2 // Leave at least 1 tile border
            );

    // Ensure room position stays within node and map bounds
    int maxX = Math.min(x + width - roomWidth, mapWidth - roomWidth - 1);
    int maxY = Math.min(y + height - roomHeight, mapHeight - roomHeight - 1);

    // If no valid position, return null
    if (x > maxX || y > maxY) {
      return;
    }

    int roomX = x + (int) (Math.random() * (maxX - x + 1));
    int roomY = y + (int) (Math.random() * (maxY - y + 1));

    room =
        new Room(
            MathUtils.clamp(roomX, 0, mapWidth - roomWidth - 1),
            MathUtils.clamp(roomY, 0, mapHeight - roomHeight - 1),
            roomWidth,
            roomHeight);
  }

  public Room getRoom() {
    if (room != null) return room;

    Room lRoom = left != null ? left.getRoom() : null;
    Room rRoom = right != null ? right.getRoom() : null;

    if (lRoom == null && rRoom == null) return null;
    if (rRoom == null) return lRoom;
    if (lRoom == null) return rRoom;

    return Math.random() < 0.5 ? lRoom : rRoom;
  }

  // Getters
  public BSPNode getLeft() {
    return left;
  }

  public BSPNode getRight() {
    return right;
  }
}
