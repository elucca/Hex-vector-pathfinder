package Pathfinder.Comparators;

import Data.CubeCoords;
import Data.Node;
import Utility.CoordTransform;
import Utility.VectorMath;
import java.util.Comparator;

public class VectorComparator implements Comparator<Node> {

    private final Node goal;
    private final int dVmul;

    public VectorComparator(Node goal, int dVmul) {
        this.goal = goal;
        this.dVmul = dVmul;
    }

    private double heuristic(Node node) {
        if (node.getCoords() == null) {
            System.out.println("?? " + node);
        }
        CubeCoords nodeVector = CoordTransform.axialToCube(node.getVector());
        CubeCoords goalVector = CoordTransform.axialToCube(goal.getVector());

        double dVMagnitude = VectorMath.magnitude(VectorMath.difference(goalVector, nodeVector));

        return dVmul * dVMagnitude;
    }

    @Override
    public int compare(Node node1, Node node2) {
        if (node1 == null) {
            return -1;
        }

        if (node2 == null) {
            return 1;
        }

        double node1Cost = heuristic(node1) + node1.getCostSoFar();
        double node2Cost = heuristic(node2) + node2.getCostSoFar();

        if (node1Cost == node2Cost) {
            return 0;
        }

        if (node1Cost < node2Cost) {
            return -1;
        }

        return 1;
    }
}
