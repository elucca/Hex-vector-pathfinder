package Main;

import Pathfinder.Pathfinder;
import Pathfinder.Graph;
import Data.AxialCoords;
import Data.CubeCoords;
import Pathfinder.Comparators.DijkstraComparator;
import Data.Node;
import Utility.CoordTransform;
import Utility.VectorMath;
import Pathfinder.Algorithm;
import PerformanceTests.PerformanceTester;
import PerformanceTests.TestPath;
import Utility.MinHeap;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) {
        // Temporarily experimenting with pathfinding by hardcoding it there.

        Graph graph = new Graph(100, 100, 30, 1, 1);
        Pathfinder pathfinder = new Pathfinder(graph);

        Node start = new Node(new AxialCoords(2, 3), new AxialCoords(0, 0), null, 0);
        Node goal = new Node(new AxialCoords(24, 30), new AxialCoords(3, 0), null, 0);

        for (Algorithm algorithm : Algorithm.values()) {
            printPath(start, goal, pathfinder, algorithm);
        }

        // Performance testing
        System.out.println("Algorithm run times: ");
        TestPath[] testBattery = {new TestPath(start, goal)};
        PerformanceTester performanceTester = new PerformanceTester(graph, testBattery);
        performanceTester.test();
    }

    private static void printPath(Node start, Node goal, Pathfinder pathfinder, Algorithm algorithm) {
        Node found = pathfinder.findPath(start, goal, algorithm);
        System.out.println("Path found with " + algorithm + ", end to start:");
        Node current = found;

        while (current != null) {
            System.out.print(current);
            System.out.println(", velocity: " + VectorMath.hexLength(current.getVector()) + " hex/turn");
            current = current.getPrevious();
        }

        System.out.println("");
    }

    /*
        // Temp heap testing
        System.out.println("");
        System.out.println("---");
        System.out.println("");
        
        MinHeap heap = new MinHeap(100, new DijkstraComparator());
        heap.insert(new Node(null, null, null, 0));
        heap.insert(new Node(null, null, null, 10));
        heap.insert(new Node(null, null, null, 5));
        heap.insert(new Node(null, null, null, 39));
        heap.insert(new Node(null, null, null, 2));
        heap.insert(new Node(null, null, null, 1));
        heap.insert(new Node(null, null, null, -1));
        
        while (!heap.isEmpty()) {
            System.out.println(heap.delMin());
        }
     */
}
