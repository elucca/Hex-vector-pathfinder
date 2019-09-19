package Main;

import Data.AxialCoords;
import Data.CubeCoords;
import Data.Node;

public class Main {

    public static void main(String[] args) {
        // Temporarily testing pathfinding by hardcoding it there
        Graph graph = new Graph(10, 10, 5, 1, 1);
        Pathfinder pathfinder = new Pathfinder();
        
        Node start = new Node(new AxialCoords(0, 0), new CubeCoords(0, 0, 0), null, 0);
        Node goal = new Node(new AxialCoords(5, 5), new CubeCoords(0, 0, 0), null, 0);
        
        graph.findNeighbors(new Node(new AxialCoords(3, 3), new CubeCoords(0, -2, 2), null, 0));
        //System.out.println(pathfinder.Dijkstra(graph, start, goal));
    }
}
