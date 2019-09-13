package Main;

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
 * vertical r (row) axis tilted to the left in order to fit the hexes.
 * Hexes are pointy-topped.
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

    /**
     * Constructs a graph instance representing the state space of a spaceship on the hex grid. In 
     * order for the graph to have a finite size, map dimensions and velocity vector magnitude need 
     * an upper bound.
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
    }

    /**
     * Finds the neighbors of the given node and returns a list of them.
     * Todo: Assign edge weights per cost function multipliers.
     * 
     * @param node The node to find the neighbors of.
     * @return A list of nodes neighboring the given node.
     */
    public List<Node> findNeighbors(Node node) {
        // Todo: Break this up and make it more readable.
        CubeCoords[] directions = new CubeCoords[]{
            new CubeCoords(1, -1, 0),
            new CubeCoords(1, 0, -1),
            new CubeCoords(0, 1, -1),
            new CubeCoords(-1, 1, 0),
            new CubeCoords(-1, 0, 1),
            new CubeCoords(0, -1, 1)};

        List<Node> neighbors = new ArrayList<>();

        // The node the ship gets to with the current vector is always a neighbor,
        // if it exists.
        CubeCoords destination = VectorMath.Sum(CoordTransform.axialToCube(node.getCoords()),
                node.getVector());
        Node potentialNeighbor = new Node(CoordTransform.cubeToAxial(destination), node.getVector(), node);
        if (nodeExists(potentialNeighbor)) {
            neighbors.add(potentialNeighbor);
        }

        for (CubeCoords direction : directions) {
            potentialNeighbor = new Node(CoordTransform.cubeToAxial(VectorMath.Sum(destination, direction)),
                    VectorMath.Sum(node.getVector(), direction), node);
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
     * @param node The node to be validated.
     * @return Whether the node exists.
     */
    private boolean nodeExists(Node node) {
        // This assumes a rhombus-shaped map unlike the reference image in docs.
        // Later I want a rectangular map.
        // Check if velocity is within bounds
        if (VectorMath.Magnitude(node.getVector()) > maxVelocity) {
            return false;
        }

        // Check if hex exists on map
        if (node.getCoords().q + 1 > mapSizeQ || node.getCoords().r + 1 > mapSizeR) {
            return false;
        }

        return true;
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
