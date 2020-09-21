package Pathfinder.Comparators;

import Data.CubeCoords;
import Data.Node;
import Utility.CoordTransform;
import Utility.VectorMath;
import java.util.Comparator;

public class TimeVectorComparator implements Comparator<Node> {

    private final Node goal;
    private final int dVmul;
    private final int timeMul;

    public TimeVectorComparator(Node goal, int dVmul, int timeMul) {
        this.goal = goal;
        this.dVmul = dVmul;
        this.timeMul = timeMul;
    }

    private double heuristic(Node node) {
        return dVmul * calcMinDV(node) + timeMul * calcMinTime(node);
    }

    private double calcMinDV(Node node) {
        CubeCoords nodeVector = CoordTransform.axialToCube(node.getVector());
        CubeCoords goalVector = CoordTransform.axialToCube(goal.getVector());

        double dVMagnitude = VectorMath.magnitude(VectorMath.difference(goalVector, nodeVector));
        
        return  dVMagnitude;
    }

    private double calcMinTime(Node node) {
        // Formula: t = 2((-v +- sqrt(v^2 + 2ad)) / 2a)
        // CHECK: Why is this 2ad and not 4ad?
        int currVel = VectorMath.hexLength(node.getVector());
        int goalVel = VectorMath.hexLength(goal.getVector());
        
        double v = Math.max(currVel, goalVel); // Velocity in hexes per turn
        double a = 1; // Acceleration in hexes per turn^2, currently always assumed to be 1
        double d = VectorMath.hexDistance(node.getCoords(), goal.getCoords());
        
        double t = 2*((-v + Math.sqrt(Math.pow(v, 2) + 2 * a * d)) / 2 * a);
        
        return t;
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
