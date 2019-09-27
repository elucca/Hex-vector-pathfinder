package PerformanceTests;

import Data.Node;

/**
 * A class used for performance testing by defining a start and goal node between which a path
 * should be found.
 */
public class TestPath {

    private final Node start;
    private final Node goal;

    public TestPath(Node start, Node goal) {
        this.start = start;
        this.goal = goal;
    }

    public Node getStart() {
        return start;
    }

    public Node getGoal() {
        return goal;
    }

}
