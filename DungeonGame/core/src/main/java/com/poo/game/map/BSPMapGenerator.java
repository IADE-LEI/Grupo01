/* ------------------------------------------------------------------------------------------------
 * Licenciatura de Engenharia Informática - IADE - 2024/2025
 * ------------------------------------------------------------------------------------------------
 * Projeto : Dungeon Game (Projeto Grupo 1)
 * Disciplica : Programação e Algoritmos (LEI1A2S)
 * Professor : Nelson Costa
 * Autores : Affonso Neto | António Neto | Paulo Jadaugy | Tiago Araújo | Tomás Pereira
 * ------------------------------------------------------------------------------------------------
 */
package com.poo.game.map;

import com.poo.game.entities.FloorEntity;

public class BSPMapGenerator {
  private final MapData mapData;
  private final int minRoomSize;
  private final int maxRoomSize;
  private final int splitDepth;
  private final Corridor corridor;

  public BSPMapGenerator(int width, int height, int minRoomSize, int maxRoomSize, int splitDepth) {
    this.mapData = new MapData(width, height);
    this.minRoomSize = minRoomSize;
    this.maxRoomSize = maxRoomSize;
    this.splitDepth = splitDepth;
    this.corridor = new Corridor(mapData);
  }

  public MapData generate() {
    BSPNode root = new BSPNode(0, 0, mapData.getWidth(), mapData.getHeight(), minRoomSize);
    splitNode(root, splitDepth);
    createRooms(root);
    return mapData;
  }

  private void splitNode(BSPNode node, int depth) {
    if (depth <= 0) return;

    if (node.split()) {
      splitNode(node.getLeft(), depth - 1);
      splitNode(node.getRight(), depth - 1);
    }
  }

  private void createRooms(BSPNode node) {
    if (node.getLeft() != null || node.getRight() != null) {
      if (node.getLeft() != null) createRooms(node.getLeft());
      if (node.getRight() != null) createRooms(node.getRight());

      if (node.getLeft() != null && node.getRight() != null) {
        corridor.connectRooms(node.getLeft().getRoom(), node.getRight().getRoom());
      }
    } else {
      node.createRoom(maxRoomSize);
      carveRoom(node.getRoom());
    }
  }

  private void carveRoom(Room room) {
    if (room == null) return;

    for (int x = room.getX(); x < room.getX() + room.getWidth(); x++) {
      for (int y = room.getY(); y < room.getY() + room.getHeight(); y++) {
        mapData.setTile(x, y, new FloorEntity(x, y, 1, 1));
      }
    }
  }
}
