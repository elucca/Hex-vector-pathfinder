package Main;

import Data.CubeCoords;
import Data.Node;
import Utility.CoordTransform;
import Utility.VectorMath;
import java.util.ArrayList;
import java.util.List;

public class Graph {
    // Coordinate system is axial, with the Q axis tilted to the right. Hexes are
    // assumed to be oriented pointy-side up.

    private final int mapSizeQ;
    private final int mapSizeR;
    private final int maxVelocity;

    private int timeMul;
    private int deltaVMul;

    public Graph(int mapSizeQ, int mapSizeR, int maxVelocity) {
        this.mapSizeQ = mapSizeQ;
        this.mapSizeR = mapSizeR;
        this.maxVelocity = maxVelocity;
    }

    public List<Node> findNeighbors(Node node) {
        // Finds/creates neighbords of a given node.
        // Todo: Assign edge weights.
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
