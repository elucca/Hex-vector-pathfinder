package Pathfinder.Comparators;

import Data.Node;
import java.util.Comparator;

/**
 * Comparator for comparing the value of two nodes. Used for the priority queue Dijkstra's algorithm
 * uses for finding the neighboring node with the lowest total cost so far.
 */
public class DijkstraComparator implements Comparator<Node> {

    /**
     * Compares the values of two nodes by the cost on the path to them so far. (i.e., by the cost
     * function in use for pathfinding)
     *
     * @param node1
     * @param node2
     * @return 0 if the value of node1 and node2 are equal, 1 if the value of node1 is greater, -1
     * if the value of node2 is greater.
     */
    @Override
    public int compare(Node node1, Node node2) {
        if (node1 == null) {
            return -1;
        }

        if (node2 == null) {
            return 1;
        }

        int node1Cost = node1.getCostSoFar();
        int node2Cost = node2.getCostSoFar();

        if (node1Cost == node2Cost) {
            return 0;
        }

        if (node1Cost < node2Cost) {
            return -1;
        }

        return 1;
    }

}
