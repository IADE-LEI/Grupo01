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

import com.badlogic.gdx.graphics.Color;
import com.poo.game.BaseComponents.SpriteFactory;
import com.poo.game.Utils.Boundaries;

import java.util.ArrayList;
import java.util.List;

public class BSPMapGenerator {
    private final MapData mapData;
    private final int minRoomSize;
    private final int maxRoomSize;
    private final int splitDepth;
    private final Corridor corridor;

    private final

    // Helper Structure
    MapNode[][] MapNodeGrid;

    public BSPMapGenerator(int width, int height, int minRoomSize, int maxRoomSize, int splitDepth) {
        this.mapData = new MapData(width, height);
        this.minRoomSize = minRoomSize;
        this.maxRoomSize = maxRoomSize;
        this.splitDepth = splitDepth;
        this.MapNodeGrid = new MapNode[width][height];
        this.corridor = new Corridor(mapData, MapNodeGrid);
    }

    public MapData generate() {
        BSPNode root = new BSPNode(0, 0, mapData.getWidth(), mapData.getHeight(), minRoomSize);
        splitNode(root, splitDepth);
        createRooms(root);
        // Generate the nodes
        GeneratePathingFromMapLayout();

        //Generate exit door
        mapData.SetExitRoom(selectExitRoom(root));

        return mapData;
    }

    private void splitNode(BSPNode node, int depth) {
        // No more rooms to create
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
            node.createRoom(maxRoomSize, mapData.getWidth(), mapData.getHeight());
            carveRoom(node.getRoom());
        }
    }

    private void carveRoom(Room room) {
        if (room == null) return;

        for (int x = room.getX(); x < room.getX() + room.getWidth(); ++x) {
            for (int y = room.getY(); y < room.getY() + room.getHeight(); ++y) {
                mapData.setTile(x, y, SpriteFactory.CreateSpriteObject(Color.GRAY));
                MapNode NewMapNode = new MapNode(x,y,room);
                MapNodeGrid[x][y] = NewMapNode;
            }
        }
    }

    private void GeneratePathingFromMapLayout() {
        int DimensionX = mapData.getWidth();
        int DimensionY = mapData.getHeight();

        // 4 Way Connections Only
        for (int Y = 0; Y < mapData.getHeight(); ++Y) {
            for (int X = 0; X < mapData.getWidth(); ++X) {
                if (MapNodeGrid[X][Y] == null) continue;

                boolean HasLeftNeighbour =
                    Boundaries.IsValidIndex(X - 1, 0, DimensionX - 1) && (MapNodeGrid[X - 1][Y] != null);
                boolean HasTopNeighbour =
                    Boundaries.IsValidIndex(Y - 1, 0, DimensionY - 1) && (MapNodeGrid[X][Y - 1] != null);
                boolean HasRightNeighbour =
                    Boundaries.IsValidIndex(X + 1, 0, DimensionX - 1) && (MapNodeGrid[X + 1][Y] != null);
                boolean HasBottomNeighbour =
                    Boundaries.IsValidIndex(Y + 1, 0, DimensionY - 1) && (MapNodeGrid[X][Y + 1] != null);

                if (HasLeftNeighbour) MapNodeGrid[X][Y].AddTwoWayConnectionTo(MapNodeGrid[X - 1][Y]);
                if (HasTopNeighbour) MapNodeGrid[X][Y].AddTwoWayConnectionTo(MapNodeGrid[X][Y - 1]);
                if (HasRightNeighbour) MapNodeGrid[X][Y].AddTwoWayConnectionTo(MapNodeGrid[X + 1][Y]);
                if (HasBottomNeighbour) MapNodeGrid[X][Y].AddTwoWayConnectionTo(MapNodeGrid[X][Y + 1]);
                mapData.GetMapGraph().AddNode(MapNodeGrid[X][Y]);
            }
        }
    }

    private void collectAllRooms(BSPNode node, List<Room> rooms) {
        if (node == null) return;
        if (node.getRoom() != null) {
            rooms.add(node.getRoom());
        }
        collectAllRooms(node.getLeft(), rooms);
        collectAllRooms(node.getRight(), rooms);
    }

    private Room selectExitRoom(BSPNode root) {
        List<Room> allRooms = new ArrayList<>();
        collectAllRooms(root, allRooms);

        // Choose the room farthest from the start position
        if (!allRooms.isEmpty()) {
            return allRooms.get(allRooms.size() - 1);
        }
        return null;
    }
}
