package Main;

import Pathfinder.Pathfinder;
import Pathfinder.Graph;
import Data.AxialCoords;
import Data.CubeCoords;
import Data.DijkstraComparator;
import Data.Node;
import Utility.CoordTransform;
import Utility.VectorMath;
import Pathfinder.Algorithm;
import Utility.MinHeap;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) {
        // Temporarily experimenting with pathfinding by hardcoding it there. Later, take user
        // input in some better form.
        Graph graph = new Graph(10, 10, 10, 1, 1);
        Pathfinder pathfinder = new Pathfinder(graph);

        Node start = new Node(new AxialCoords(2, 3), new CubeCoords(0, 0, 0), null, 0);
        Node goal = new Node(new AxialCoords(4, 2), new CubeCoords(1, -1, 0), null, 0);

        Node foundDijkstra = pathfinder.findPath(start, goal, Algorithm.DIJKSTRA);

        System.out.println("Dijkstra path, end to start: ");
        Node dijkstraNode = foundDijkstra;
        while (dijkstraNode != null) {
            System.out.print(dijkstraNode + ", vector as axial: " + CoordTransform.cubeToAxial(dijkstraNode.getVector()));
            System.out.println(", velocity: " + VectorMath.magnitude(dijkstraNode.getVector()));
            dijkstraNode = dijkstraNode.getPrevious();
        }
        
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

    }
}
