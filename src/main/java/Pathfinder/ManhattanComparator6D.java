package Pathfinder;

import Data.CubeCoords;
import Data.Node;
import Utility.CoordTransform;
import java.util.Comparator;

public class ManhattanComparator6D implements Comparator<Node> {

    private final Node goal;
    private final int timeMul;
    private final int dVmul;

    public ManhattanComparator6D(Node goal, int timeMul, int dVmul) {
        this.goal = goal;
        this.timeMul = timeMul;
        this.dVmul = dVmul;
    }

    private int heuristic(Node node) {
        CubeCoords nodeCoords = CoordTransform.axialToCube(node.getCoords());
        CubeCoords nodeVector = CoordTransform.axialToCube(node.getVector());

        CubeCoords goalCoords = CoordTransform.axialToCube(goal.getCoords());
        CubeCoords goalVector = CoordTransform.axialToCube(goal.getVector());

        // The components of the six-dimensional vectors representing these states
        int a1 = nodeCoords.x;
        int b1 = nodeCoords.y;
        int c1 = nodeCoords.z;
        int d1 = nodeVector.x;
        int e1 = nodeVector.y;
        int f1 = nodeVector.z;

        int a2 = goalCoords.x;
        int b2 = goalCoords.y;
        int c2 = goalCoords.z;
        int d2 = goalVector.x;
        int e2 = goalVector.y;
        int f2 = goalVector.z;

        return timeMul * ((Math.abs(a1 - a2) + Math.abs(b1 - b2) + Math.abs(c1 - c2) + Math.abs(d1 - d2)
                + Math.abs(e1 - e2) + Math.abs(f1 - f2)) / 2);
    }

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
