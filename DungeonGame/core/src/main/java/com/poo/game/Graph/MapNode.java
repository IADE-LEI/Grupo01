/* ------------------------------------------------------------------------------------------------
 * Licenciatura de Engenharia Informática - IADE - 2024/2025
 * ------------------------------------------------------------------------------------------------
 * Projeto : Dungeon Game (Projeto Grupo 1)
 * Disciplica : Programação e Algoritmos (LEI1A2S)
 * Professor : Nelson Costa
 * Autores : Affonso Neto | António Neto | Paulo Jadaugy | Tiago Araújo | Tomás Pereira
 * ------------------------------------------------------------------------------------------------
 */
package com.poo.game.Graph;

import com.badlogic.gdx.math.Vector2;
import com.poo.game.Map.Room;

import java.util.ArrayList;

public class MapNode {
    public ArrayList<MapNode> NeighbouringNodes = new ArrayList<MapNode>();
    private int CellX;
    private int CellY;
    private Room Room;


    public MapNode(int cellX, int cellY, Room room) {
        CellX = cellX;
        CellY = cellY;
        Room = room;
    }

    public void AddOneWayConnectionTo(MapNode OtherNode) {
        if (!NeighbouringNodes.contains(OtherNode)) NeighbouringNodes.add(OtherNode);
    }

    public void AddTwoWayConnectionTo(MapNode OtherNode) {
        if (!NeighbouringNodes.contains(OtherNode)) {
            NeighbouringNodes.add(OtherNode);
            OtherNode.AddOneWayConnectionTo(this);
        }
    }

    public Vector2 GetPosition() {
        return new Vector2(CellX, CellY);
    }

    public int GetCellX() {
        return CellX;
    }

    public void SetCellX(int cellX) {
        CellX = cellX;
    }

    public int GetCellY() {
        return CellY;
    }

    public void SetCellY(int cellY) {
        CellY = cellY;
    }

    public Room GetRoom() {
        return Room;
    }
}
