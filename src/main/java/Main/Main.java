package Main;

import Pathfinder.Pathfinder;
import Pathfinder.Graph;
import Data.AxialCoords;
import Data.CubeCoords;
import Data.Node;
import Utility.CoordTransform;
import Utility.VectorMath;
import Pathfinder.Algorithm;

public class Main {

    public static void main(String[] args) {
        // Temporarily experimenting with pathfinding by hardcoding it there. Later, take user
        // input in some better form.
        Graph graph = new Graph(11, 11, 10, 1, 0);
        Pathfinder pathfinder = new Pathfinder(graph);

        Node start = new Node(new AxialCoords(2, 0), new CubeCoords(0, 0, 0), null, 0);
        Node goal = new Node(new AxialCoords(0, 4), new CubeCoords(0, 0, 0), null, 0);

        Node foundDijkstra = pathfinder.findPath(start, goal, Algorithm.DIJKSTRA);

        System.out.println("Dijkstra path, end to start: ");
        Node dijkstraNode = foundDijkstra;
        while (dijkstraNode != null) {
            System.out.print(dijkstraNode + ", vector as axial: " + CoordTransform.cubeToAxial(dijkstraNode.getVector()));
            System.out.println(", velocity: " + VectorMath.magnitude(dijkstraNode.getVector()));
            dijkstraNode = dijkstraNode.getPrevious();
        }
        
        System.out.println("");

        Node foundBFS = pathfinder.findPath(start, goal, Algorithm.BFS);

        System.out.println("BFS path, end to start: ");
        Node BFSnode = foundBFS;
        while (BFSnode != null) {
            System.out.print(BFSnode + ", vector as axial: " + CoordTransform.cubeToAxial(BFSnode.getVector()));
            System.out.println(", velocity: " + VectorMath.magnitude(BFSnode.getVector()));
            BFSnode = BFSnode.getPrevious();
        }
    }
}
