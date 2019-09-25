package Pathfinder;

import Pathfinder.Graph;
import Data.DijkstraComparator;
import Data.Node;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * This class contains pathfinding algorithms for finding the shortest path on the supplied graph,
 * according to the graph's cost function.
 */
public class Pathfinder {

    private Graph graph;

    /**
     * Constructs a new pathfinder instance which finds path on the supplied Graph. The Graph
     * instance defines the structure of the graph, including edge weights/state change costs.
     *
     * @param graph The graph on which pathfinding is to be done.
     */
    public Pathfinder(Graph graph) {
        this.graph = graph;
    }

    /**
     * Return the shortest path from the start node to the goal node. The path is found using the
     * specified algorithm. After the algorithm finishes, each node contains a reference to the
     * previous node on the found path, so the path can be extracted from the returned goal node.
     *
     * Todo: Refactor the algorithms to use a single method with only the data structure varying
     * because that's the only actual difference.
     *
     * @param start The starting node.
     * @param goal The goal node.
     * @param algorithm The algorithm to be used for pathfinding, among those currently implemented.
     * @return The goal node, including where it was reached from, if it is reachable, null
     * otherwise.
     */
    public Node findPath(Node start, Node goal, Algorithm algorithm) {
        this.graph = graph;

        switch (algorithm) {
            case BFS:
                return BFS(graph, start, goal);
            case DIJKSTRA:
                return Dijkstra(graph, start, goal);
            default:
                return null;
        }
    }

    /**
     * An implementation of breadth-first search to find the shortes tpath on the given graph.
     *
     * @param graph The graph on which the algorithm runs.
     * @param start The starting node.
     * @param goal The goal node.
     * @return The goal node, including where it was reached from, if it is reachable, null
     * otherwise.
     */
    public Node BFS(Graph graph, Node start, Node goal) {
        Queue<Node> queue = new ArrayDeque<>();
        Set visited = new HashSet<>();

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

    /**
     * An implementation of Dijkstra's algorithm to find the shortest path on the given graph.
     *
     * @param graph The graph on which the algorithm runs.
     * @param start The starting node.
     * @param goal The goal node.
     * @return The goal node, including where it was reached from, if it is reachable, null
     * otherwise.
     */
    public Node Dijkstra(Graph graph, Node start, Node goal) {
        Queue<Node> queue = new PriorityQueue<>(new DijkstraComparator());
        Set visited = new HashSet<>();

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

    /**
     * Adds the neighbors of the given node to the collection, which is used by a pathfinding
     * algorithm to pick nodes to be processed. (For example, a stack for a priority queue.) The
     * neighbors that are added to the collection are determined by the Graph instance of this
     * pathfinder.
     *
     * @param collection The collection used by a pathfinding algorithm to select nodes to process.
     * @param node The node whose neighbors are to be added to the collection.
     */
    private void enqueueNeighbors(Collection<Node> collection, Node node) {
        collection.addAll(graph.findNeighbors(node));
    }

}
