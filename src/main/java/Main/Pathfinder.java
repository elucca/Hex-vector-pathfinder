package Main;

import Data.DijkstraComparator;
import Data.Node;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * This class contains pathfinding algorithms for finding the shortest path on the supplied graph,
 * according to the graph's cost function.
 */
public class Pathfinder {

    private DijkstraComparator comparator;
    private Graph graph;

    /**
     * An implementation of Dijkstra's algorithm to find the shortest path on the given graph. As
     * each node contains a reference to the previous node on the path, the path can be extracted
     * from the returned goal node.
     *
     * @param graph The graph on which the algorithm runs.
     * @return The goal node, if it is reachable, null otherwise.
     */
    public Node Dijkstra(Graph graph, Node start, Node goal) {
        this.comparator = new DijkstraComparator();
        this.graph = graph;

        PriorityQueue<Node> queue = new PriorityQueue<>(comparator);
        Set visited = new HashSet<Node>();

        queue.add(start);

        Node node = null;
        while (!queue.isEmpty()) {
            node = queue.poll();

            if (node != null && !visited.contains(node)) {
                visited.add(node);

                if (node.equals(goal)) {
                    return node;
                }

                enqueueNeighbors(queue, node);
            }
        }

        return null;
    }

    private void enqueueNeighbors(PriorityQueue<Node> queue, Node node) {
        queue.addAll(graph.findNeighbors(node));
    }
}
