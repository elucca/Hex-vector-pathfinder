package Main;

import Data.AxialCoords;
import Data.CubeCoords;
import Data.Node;
import Utility.CoordTransform;
import Utility.VectorMath;

public class Main {

    public static void main(String[] args) {
        // Temporarily testing pathfinding by hardcoding it there
        Graph graph = new Graph(11, 11, 10, 1, 0);
        Pathfinder pathfinder = new Pathfinder();
        
        Node start = new Node(new AxialCoords(2, 0), new CubeCoords(0, 0, 0), null, 0);
        Node goal = new Node(new AxialCoords(0, 4), new CubeCoords(0, 0, 0), null, 0);
        
        Node found = pathfinder.Dijkstra(graph, start, goal);
        
        System.out.println("Path, end to start: ");
        Node node = found;
        while (node != null) {
            System.out.print(node + ", vector as axial: " + CoordTransform.cubeToAxial(node.getVector()));
            System.out.println(", velocity: " + VectorMath.magnitude(node.getVector()));
            node = node.getPrevious();
        }
    }
}
