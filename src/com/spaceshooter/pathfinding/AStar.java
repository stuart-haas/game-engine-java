package com.spaceshooter.pathfinding;

import com.spaceshooter.entity.Node;
import com.spaceshooter.map.Grid;
import com.spaceshooter.map.Layer;
import com.spaceshooter.math.Vector;

import java.util.ArrayList;
import java.util.List;

public class AStar {

    Grid grid;

    public AStar() {
        grid = Grid.getInstance();
    }

    public void search(Vector start, Vector target, Layer layer) {
        Heap open = new Heap (999);
        List closed = new ArrayList<Node>();

        Node startNode = grid.nodeFromWorldPoint(start, layer);
        Node targetNode = grid.nodeFromWorldPoint(target, layer);

        open.add(startNode);

        while(open.getCurrentItemCount() > 0) {
            Node currentNode = (Node) open.removeFirst();
            closed.add(currentNode);

            if(currentNode == targetNode) {
                trace(startNode, targetNode);
                return;
            }

            for(Node neighbor : grid.getNeighborsByNode(layer, currentNode)) {
                if (closed.contains(neighbor)) {
                    continue;
                }

                int newMovementCostToNeighbour = currentNode.gCost + heuristic(currentNode, neighbor);
                if (newMovementCostToNeighbour < neighbor.gCost || !open.contains(neighbor)) {
                    neighbor.gCost = newMovementCostToNeighbour;
                    neighbor.hCost = heuristic(neighbor, targetNode);
                    neighbor.parent = currentNode;

                    if (!open.contains(neighbor))
                        open.add(neighbor);
                }
            }
        }
    }

    void trace(Node startNode, Node targetNode) {
        List path = new ArrayList<Node>();
        Node currentNode = targetNode;

        while (currentNode != startNode) {
            path.add(currentNode);
            currentNode = currentNode.parent;
        }

        grid.path = path;
    }

    int heuristic(Node nodeA, Node nodeB) {
        int dstX = Math.abs(nodeA.gridX - nodeB.gridX);
        int dstY = Math.abs(nodeA.gridY - nodeB.gridY);

        if (dstX > dstY)
            return 14*dstY + 10* (dstX-dstY);
        return 14*dstX + 10 * (dstY-dstX);
    }
}
