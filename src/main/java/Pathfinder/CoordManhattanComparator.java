package Pathfinder;

import Data.CubeCoords;
import Data.Node;
import Utility.CoordTransform;
import java.util.Comparator;

/**
 * Comparator for comparing the value of two nodes used for the priority queue of the A* algorithm
 * with a heuristic based on the Manhattan distance between the start and goal hex coordinates.
 * 
 * This heuristic appears to be inadmissible and does not result in the algorithm returning the
 * shortest path.
 */
public class CoordManhattanComparator implements Comparator<Node> {

    private final Node goal;

    public CoordManhattanComparator(Node goal) {
        this.goal = goal;
    }

    private int heuristic(Node node) {
        CubeCoords nodeCoords = CoordTransform.axialToCube(node.getCoords());
        CubeCoords goalCoords = CoordTransform.axialToCube(goal.getCoords());

        return (Math.abs(goalCoords.x - nodeCoords.x)
                + Math.abs(goalCoords.y - nodeCoords.y)
                + Math.abs(goalCoords.z - nodeCoords.z)) / 2;
    }

    /**
     * Compares the values of two nodes by the sum of the cost on the path to them so far and a 
     * heuristic function based on the Manhattan distance from the node hex coordinates to the goal 
     * hex coordinates. (i.e., by the sum of the cost function and heuristic functions in use for
     * pathfinding)
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

        int node1Cost = heuristic(node1) + node1.getCostSoFar();
        int node2Cost = heuristic(node2) + node2.getCostSoFar();

        if (node1Cost == node2Cost) {
            return 0;
        }

        if (node1Cost < node2Cost) {
            return -1;
        }

        return 1;
    }

}
