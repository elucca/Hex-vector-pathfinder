package Main;

import Data.AxialCoords;
import Data.CubeCoords;
import Data.Node;
import Utility.CoordTransform;
import Utility.VectorMath;

public class Main {

    public static void main(String[] args) {
        // Temporarily testing pathfinding by hardcoding it there
        Graph graph = new Graph(10, 10, 10, 1, 1);
        Pathfinder pathfinder = new Pathfinder();
        
        Node start = new Node(new AxialCoords(0, 0), new CubeCoords(0, 0, 0), null, 0);
        Node goal = new Node(new AxialCoords(5, 5), new CubeCoords(0, 1, 1), null, 0);
        
        Node found = pathfinder.Dijkstra(graph, start, goal);
        
        System.out.println("Path, end to start: ");
        Node node = found;
        while (node != null) {
            System.out.print(node);
            System.out.println(", velocity: " + VectorMath.magnitude(node.getVector()));
            node = node.getPrevious();
        }
    }
}
