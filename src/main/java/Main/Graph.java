package Main;

import Data.AxialCoords;
import Data.CubeCoords;
import Data.Node;
import Utility.CoordTransform;
import Utility.VectorMath;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the graph for pathfinding. The graph represents the state space of a a particular
 * spaceship on a hex grid, moving with Newtonian velocity vectors. In particular, graph nodes are
 * made up of each combination of hex coordinates and movement vectors, as limited by constructor
 * parameters.
 *
 * The coordinate system for the hex grid is axial, with the q (column) axis horizontal and the
 * vertical r (row) axis tilted to the left in order to fit the hexes. Hexes are pointy-topped.
 *
 * The graph itself is implicit, i.e. it is not stored in any data structure, but generated on the
 * fly.
 */
public class Graph {

    private final int mapSizeQ;
    private final int mapSizeR;
    private final int maxVelocity;

    private int timeMul;
    private int deltaVMul;

    private CubeCoords[] neighborDirections;

    /**
     * Constructs a graph instance representing the state space of a spaceship on the hex grid. In
     * order for the graph to have a finite size, map dimensions and velocity vector magnitude need
     * an upper bound. The map origin (hex (0,0)) is in the upper left corner.
     *
     * @param mapSizeQ The number of hex columns on the map.
     * @param mapSizeR The number of hex rows on the map.
     * @param maxVelocity The maximum allowable velocity (velocity vector magnitude) for the ship.
     * @param timeMul Multiplier weighing how much time spent (i.e. turns, or state transitions) is
     * weighed by the cost function assigning weighs to graph edges.
     * @param deltaVMul Multiplier weighing how much delta-v spend is weighed by the cost function.
     */
    public Graph(int mapSizeQ, int mapSizeR, int maxVelocity, int timeMul, int deltaVMul) {
        this.mapSizeQ = mapSizeQ;
        this.mapSizeR = mapSizeR;
        this.maxVelocity = maxVelocity;

        this.timeMul = timeMul;
        this.deltaVMul = deltaVMul;

        this.neighborDirections = new CubeCoords[]{
            new CubeCoords(1, -1, 0),
            new CubeCoords(1, 0, -1),
            new CubeCoords(0, 1, -1),
            new CubeCoords(-1, 1, 0),
            new CubeCoords(-1, 0, 1),
            new CubeCoords(0, -1, 1)};
    }

    /**
     * Finds the neighbors of the given node and returns a list of them. The neighbors include the
     * node the ship would travel to if it did not accelerate at all, as well as each node the ship
     * would travel to if it accelerated in any of the six possible directions. These neighbors
     * consist of the hex coordinate and the current vector modified by the same direction vector.
     * (those in the neighborDirections array)
     *
     * Todo: Allow larger vector changes up to some limits. Currently it's hardcoded to 0 (if vector
     * isn't changed) or 1 (if vector is changed.)
     * Todo: Think how to handle if the path
     * goes out of the map. This is the case when there are no neighbors. Todo: Clean this up and
     * maybe break it up to make it more readable.
     *
     * @param node The node to find the neighbors of.
     * @return A list of nodes neighboring the given node.
     */
    public List<Node> findNeighbors(Node node) {
        List<Node> neighbors = new ArrayList<>();

        // The node the ship gets to with the current vector is always a neighbor,
        // if it exists.
        CubeCoords destination = VectorMath.sum(CoordTransform.axialToCube(node.getCoords()), node.getVector());

        Node potentialNeighbor = new Node(CoordTransform.cubeToAxial(destination), node.getVector(), node, node.getCostSoFar() + neighborCost(0));
        if (nodeExists(potentialNeighbor)) {
            neighbors.add(potentialNeighbor);
        }

        for (CubeCoords direction : neighborDirections) {
            // All these coord transforms are a mess, refactor to overload the math methods to accept
            // either.
            CubeCoords newDestination = VectorMath.sum(destination, direction);

            potentialNeighbor = new Node(CoordTransform.cubeToAxial(newDestination), VectorMath.sum(node.getVector(), direction), node, node.getCostSoFar() + neighborCost(1));
            if (nodeExists(potentialNeighbor)) {
                neighbors.add(potentialNeighbor);
            }
        }

        return neighbors;
    }

    /**
     * Checks if a node generated while generating neighbors is actually valid, i.e. if it should
     * exist according to the graph's map size and maximum velocity parameters.
     *
     * Currently assumes a rhombus-shaped map. Todo: Change to assume a rectangular map instead.
     *
     * @param node The node to be validated.
     * @return Whether the node exists.
     */
    boolean nodeExists(Node node) {
        if (VectorMath.magnitude(node.getVector()) > maxVelocity) {
            return false;
        }

        if (node.getCoords().q + 1 > mapSizeQ || node.getCoords().r + 1 > mapSizeR
                || node.getCoords().q < 0 || node.getCoords().r < 0) {
            return false;
        }

        return true;
    }

    /**
     * Returns the cost that would be incurred on the path to a neighboring node, i.e. cost from the
     * start to the node currently being processed plus the edge weight to the neighbor. The cost is
     * based on the graph's cost multipliers for time and delta-v, and the amount of delta-v spent.
     *
     * @param dVSpent The amount of delta-v to be spent on this transition (magnitude of vector
     * change).
     * @return The incurred cost so far to the neighboring node.
     */
    private int neighborCost(int dVSpent) {
        return timeMul + deltaVMul * dVSpent;
    }

    public int getTimeMul() {
        return timeMul;
    }

    public void setTimeMul(int timeMul) {
        this.timeMul = timeMul;
    }

    public int getDeltaVMul() {
        return deltaVMul;
    }

    public void setDeltaVMul(int deltaVMul) {
        this.deltaVMul = deltaVMul;
    }

}
