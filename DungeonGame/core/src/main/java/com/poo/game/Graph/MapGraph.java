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

import java.util.*;

import static com.badlogic.gdx.math.MathUtils.random;

public class MapGraph {

    // All the map nodes
    ArrayList<MapNode> Nodes = new ArrayList<>();

    public MapNode GetNode(int CellX, int CellY) {
        for (int i = 0; i < Nodes.size(); ++i) {
            if (Nodes.get(i).CellX == CellX && Nodes.get(i).CellY == CellY) {
                return Nodes.get(i);
            }
        }

        return null;
    }

    public MapNode GetFirstNode() {
        if (Nodes != null && !Nodes.isEmpty()) return Nodes.get(0);
        return null;
    }

    public void SetNodes(ArrayList<MapNode> nodes) {
        Nodes = nodes;
    }

    public void AddNode(MapNode node) {
        Nodes.add(node);
    }

    public List<MapNode> SearchForPath(MapNode StartNode, MapNode EndNode) {
        if (StartNode == null || EndNode == null) return null;

        if (StartNode.CellX == EndNode.CellX && StartNode.CellY == EndNode.CellY) return null;

        return FindAStarPath(StartNode, EndNode);
    }

    // A* Implementation

    /**
     * Calculates the Manhattan distance heuristic between two nodes
     *
     * @param from Starting node
     * @param to   Target node
     * @return Estimated distance
     */
    private static int heuristic(MapNode from, MapNode to) {
        return Math.abs(from.CellX - to.CellX) + Math.abs(from.CellY - to.CellY);
    }

    /**
     * Finds the path from start to goal using A* algorithm
     *
     * @param start Starting node
     * @param goal  Target node
     * @return List of nodes representing the path, or empty list if no path exists
     */
    public static List<MapNode> FindAStarPath(MapNode start, MapNode goal) {
        // Set of nodes already evaluated
        Set<MapNode> closedSet = new HashSet<>();

        // Map to reconstruct the path
        Map<MapNode, MapNode> cameFrom = new HashMap<>();

        // g-score: cost from start to current node
        Map<MapNode, Integer> gScore = new HashMap<>();
        gScore.put(start, 0);

        // f-score: estimated total cost from start to goal through current node
        Map<MapNode, Integer> fScore = new HashMap<>();
        fScore.put(start, heuristic(start, goal));

        // Priority queue for open nodes, ordered by f-score (lowest first)
        PriorityQueue<MapNode> openSet =
            new PriorityQueue<>(
                Comparator.comparingInt(n -> fScore.getOrDefault(n, Integer.MAX_VALUE)));

        openSet.add(start);

        while (!openSet.isEmpty()) {
            MapNode current = openSet.poll();

            if (current.equals(goal)) {
                return ReconstructPathAStar(cameFrom, current);
            }

            closedSet.add(current);

            for (MapNode neighbor : current.NeighbouringNodes) {
                if (closedSet.contains(neighbor)) {
                    continue; // Skip already evaluated nodes
                }

                // All edges have the same weight (1)
                int tentativeGScore = gScore.get(current) + 1;

                if (!openSet.contains(neighbor)) {
                    openSet.add(neighbor);
                } else if (tentativeGScore >= gScore.getOrDefault(neighbor, Integer.MAX_VALUE)) {
                    continue; // This is not a better path
                }

                // This path is the best so far, record it
                cameFrom.put(neighbor, current);
                gScore.put(neighbor, tentativeGScore);
                fScore.put(neighbor, tentativeGScore + heuristic(neighbor, goal));
            }
        }

        // No path found
        return Collections.emptyList();
    }

    public MapNode GetRandomNode() {
        int randomIndex = random.nextInt(Nodes.size());
        return Nodes.get(randomIndex);
    }
    private static List<MapNode> ReconstructPathAStar(
        Map<MapNode, MapNode> cameFrom, MapNode current) {
        List<MapNode> path = new ArrayList<>();
        path.add(current);

        while (cameFrom.containsKey(current)) {
            current = cameFrom.get(current);
            path.add(0, current);
        }

        return path;
    }
}
